package io.github.kusaanko.modmanager;

import io.github.kusaanko.MCAddToJar;
import io.github.kusaanko.Profile;
import io.github.kusaanko.Util;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static io.github.kusaanko.Language.translate;

public class DownloadingDialog extends JDialog {

    private Consumer<File> event;
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

    public void run(String link, File placeFolder, Profile profile, Mod mod) {
        new Thread(() -> {
            File outputFile;
            try{
                String fileName = mod.getDownloadFileName();

                File temporary = new File(mod.getDownloadFolder(), fileName);
                outputFile = new File(placeFolder, fileName);

                if(!temporary.exists()) {
                    if(mod.needUserDownload()) {
                        this.dispose();
                        WaitingDownloadDialog dialog = new WaitingDownloadDialog(this, mod.getName(), mod.getVersion(), mod.getDownloadPageURL());
                        while(!dialog.isDownloaded()) {
                            Thread.sleep(100);
                        }
                        dialog.dispose();
                        if(dialog.getDownloadedFile() != null && dialog.getDownloadedFile().exists()) {
                            dialog.getDownloadedFile().renameTo(temporary);
                        }
                    }else {
                        String downloadURL = link;
                        URL url = new URL(downloadURL);
                        URLConnection connection = url.openConnection();
                        ((HttpURLConnection) connection).setInstanceFollowRedirects(true);
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

                        if (((HttpURLConnection) connection).getResponseCode() == 200) {
                            statusLabel.setText(String.format(translate("downloading"), fileName, ""));

                            temporary.getParentFile().mkdirs();
                            InputStream inputStream = url.openStream();
                            OutputStream outputStream = new FileOutputStream(temporary);

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
                        } else {
                            statusLabel.setText(String.format(translate("anerrorhasoccurredhttp"), ((HttpURLConnection) connection).getResponseCode()));
                        }
                    }
                }
                if (mod.getInstallationType() != Mod.INSTALLATION_TYPE.IN_JAR) {
                    Util.copy(temporary, outputFile);
                } else {
                    outputFile = temporary;
                }
                event.accept(outputFile);
            }catch (Exception e) {
                statusLabel.setText(String.format(translate("anerrorhasoccurred"), e.getLocalizedMessage()));
                e.printStackTrace();
            }
        }).start();
    }

    public void registerEvent(Consumer<File> event) {
        this.event = event;
    }
}
