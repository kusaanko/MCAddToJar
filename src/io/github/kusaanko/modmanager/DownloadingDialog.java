package io.github.kusaanko.modmanager;

import io.github.kusaanko.Profile;
import io.github.kusaanko.Util;
import io.github.kusaanko.modmanager.mod125.Mod125;

import javax.swing.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static io.github.kusaanko.Language.translate;

public class DownloadingDialog extends JDialog {

    private Consumer<Path> event;
    private JLabel statusLabel;

    public DownloadingDialog(JDialog parentDialog) {
        super(parentDialog);

        statusLabel = new JLabel("Connecting...");

        this.add(statusLabel);

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(400, 200);
        this.setTitle(translate("download"));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void run(String link, Path placeFolder, Profile profile, Mod mod) {
        new Thread(() -> {
            Path outputFile;
            try{
                String fileName = mod.getDownloadFileName();

                Path temporary = Util.getPath(mod.getDownloadFolder(), fileName);
                outputFile = Util.getPath(placeFolder, fileName);

                if(!Files.exists(temporary)) {
                    if(mod.needUserDownload()) {
                        this.dispose();
                        WaitingDownloadDialog dialog = new WaitingDownloadDialog(this, mod.getName(), mod.getVersion(), mod.getDownloadPageURL());
                        while(!dialog.isDownloaded()) {
                            Thread.sleep(100);
                        }
                        dialog.dispose();
                        if(dialog.getDownloadedFile() != null && Files.exists(dialog.getDownloadedFile())) {
                            Files.move(dialog.getDownloadedFile(), temporary);
                        }
                    }else {
                        String downloadURL = link;
                        URL url = new URL(downloadURL);
                        URLConnection connection = url.openConnection();
                        ((HttpURLConnection) connection).setInstanceFollowRedirects(true);
                        connection.addRequestProperty("Referer", mod.getReferer());
                        connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36");
                        ((HttpURLConnection) connection).setRequestMethod("GET");
                        connection.connect();

                        if (((HttpURLConnection) connection).getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP
                                || ((HttpURLConnection) connection).getResponseCode() == HttpURLConnection.HTTP_MOVED_PERM
                                || ((HttpURLConnection) connection).getResponseCode() == HttpURLConnection.HTTP_SEE_OTHER) {

                            downloadURL = connection.getHeaderField("Location");
                            String cookies = connection.getHeaderField("Set-Cookie");
                            System.out.println("Redirecting to " + downloadURL);
                            connection = new URL(downloadURL).openConnection();

                            if (cookies != null) {
                                connection.setRequestProperty("Cookie", cookies);
                            }
                        }
                        statusLabel.setText(String.format(translate("downloading"), fileName, ""));

                        Files.createDirectories(temporary.getParent());
                        InputStream inputStream = connection.getInputStream();
                        OutputStream outputStream = Files.newOutputStream(temporary);

                        byte[] buff = new byte[4096];
                        int len;
                        int downloaded = 0;
                        while ((len = inputStream.read(buff, 0, buff.length)) != -1) {
                            outputStream.write(buff, 0, len);
                            downloaded += len;
                            statusLabel.setText(String.format(translate("downloading"), fileName, downloaded / 1024 + "KiB / " + connection.getContentLength() / 1024 + "KiB"));
                        }
                        inputStream.close();
                        outputStream.close();
                    }
                }
                if (mod.getInstallationType() != Mod.INSTALLATION_TYPE.IN_JAR && mod.getType() != Mod.TYPE.PATCH) {
                    if(mod.getProcessType() == Mod.PROCESS_TYPE.PLAIN) {
                        Util.copy(temporary, outputFile);
                    }else {
                        ZipInputStream inputStream = new ZipInputStream(Files.newInputStream(temporary), Charset.forName(mod.getUnzipCharset()));
                        System.out.println(temporary.toString());

                        byte[] buff = new byte[8192];
                        int len;
                        int read = 0;
                        ZipEntry entry;
                        while((entry = inputStream.getNextEntry()) != null) {
                            for(String extract : mod.getUnzipFiles()) {
                                if (entry.getName().startsWith(extract)) {
                                    statusLabel.setText("Unzipping... " + entry.getName());
                                    Path out = Util.getPath(placeFolder, entry.getName());
                                    if (entry.isDirectory()) {
                                        Files.createDirectories(out);
                                    } else {
                                        read = 0;
                                        OutputStream outputStream = Files.newOutputStream(out);
                                        while ((len = inputStream.read(buff)) != -1) {
                                            outputStream.write(buff, 0, len);
                                            read += len;
                                            statusLabel.setText("Unzipping... " + entry.getName() + " " + (read / 1024) + "KiB");
                                        }
                                        outputStream.close();
                                    }
                                    break;
                                }
                            }
                        }

                        inputStream.close();
                    }
                } else {
                    if(mod.getType() == Mod.TYPE.PATCH) {
                        statusLabel.setText("Patching... ");
                        Mod targetMod = Mod125.mods125.get(mod.getPatchMod());
                        Path outZip;
                        if (targetMod.getInstallationType() == Mod.INSTALLATION_TYPE.MODS_FOLDER) {
                            outZip = Util.getPath(placeFolder, targetMod.getDownloadFileName());
                        } else {
                            throw new IllegalArgumentException("This is not supported type!!(" + targetMod.getInstallationType() + ")");
                        }
                        if(mod.getPatchType() == Mod.PATCH_TYPE.OVERWRITE_ZIP) {
                            Util.zipCopy(temporary, outZip);
                        }else {
                            Path tmpZip = Util.getPath(outZip.getParent(), "tmp" + System.currentTimeMillis() + ".tmp");
                            ZipInputStream inputStream = new ZipInputStream(Files.newInputStream(outZip), Charset.forName(mod.getUnzipCharset()));
                            ZipOutputStream outputStream = new ZipOutputStream(Files.newOutputStream(tmpZip), Charset.forName(mod.getUnzipCharset()));

                            byte[] buff = new byte[8192];
                            int len;
                            ZipEntry entry;
                            while((entry = inputStream.getNextEntry()) != null) {
                                for(String delete : mod.getPatchDeleteFiles()) {
                                    if (!entry.getName().startsWith(delete)) {
                                        outputStream.putNextEntry(new ZipEntry(entry.getName()));
                                        while ((len = inputStream.read(buff)) != -1) {
                                            outputStream.write(buff, 0, len);
                                        }
                                        break;
                                    }
                                }
                            }

                            inputStream.close();
                            outputStream.close();
                            Files.delete(outZip);
                            Files.move(tmpZip, outZip);
                        }
                    }
                    outputFile = temporary;
                }
                event.accept(outputFile);
            }catch (Exception e) {
                statusLabel.setText(String.format(translate("anerrorhasoccurred"), e.getLocalizedMessage()));
                e.printStackTrace();
                this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        }).start();
    }

    public void registerEvent(Consumer<Path> event) {
        this.event = event;
    }
}
