package io.github.kusaanko.modmanager;

import io.github.kusaanko.AddToJarTurn;
import io.github.kusaanko.Profile;
import io.github.kusaanko.Util;
import io.github.kusaanko.modmanager.mod125.Mod125;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static io.github.kusaanko.Language.translate;

public class ConfigCopyingDialog extends JDialog {
    private JLabel statusLabel;
    private boolean overwrite;
    private ArrayList<String> writeList;
    private Runnable event;

    public ConfigCopyingDialog(JDialog parentDialog, Mod mod) {
        super(parentDialog);

        int result = JOptionPane.showOptionDialog(
                parentDialog,
                translate("selecttheinstallationmethod"),
                translate("confirm"),
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[]{translate("overwrite"), translate("onlyfilesthatdonotexist"), translate("selectfiles")},
                translate("onlyfilesthatdonotexist"));

        if(result == 0 || result == 2) {
            overwrite = true;
        }else {
            overwrite = false;
        }

        if(result == 2) {
            ArrayList<String> list = new ArrayList<>();
            try {
                ZipInputStream stream = new ZipInputStream(mod.getConfigInputStream());
                ZipEntry entry;
                while ((entry = stream.getNextEntry()) != null) {
                    list.add(entry.getName());
                }
                stream.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
            new AddToJarTurn(this, mod.getName(), mod.getConfigInputStream(), list) {
                @Override
                public void end(String fileName, ArrayList<String> remove) {
                    writeList = remove;
                    this.dispose();
                }
            };
        }

        statusLabel = new JLabel("Copying...");

        this.add(statusLabel);

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(400, 200);
        this.setTitle(translate("config"));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void run(Path placeFolder, Mod mod) {
        new Thread(() -> {
            try{
                ZipInputStream stream = new ZipInputStream(mod.getConfigInputStream());
                ZipEntry entry;
                while((entry = stream.getNextEntry()) != null) {
                    if(entry.getName().endsWith("/")) {
                        Files.createDirectories(Util.getPath(placeFolder, entry.getName()));
                    }else {
                        Path file = Util.getPath(placeFolder, entry.getName());
                        if(overwrite || !Files.exists(file)) {
                            if(writeList != null && writeList.contains(entry.getName())) {
                                continue;
                            }
                            if(entry.getName().endsWith(".delete")) {
                                Path delete = Util.getPath(placeFolder, entry.getName().substring(0, entry.getName().length() - ".delete".length()));
                                if(Files.exists(delete)) Files.delete(delete);
                            }else {
                                OutputStream outputStream = Files.newOutputStream(file);
                                byte[] buff = new byte[8192];
                                int len;
                                while ((len = stream.read(buff)) != -1) {
                                    outputStream.write(buff, 0, len);
                                }
                                outputStream.close();
                            }
                        }
                    }
                }
                stream.close();
                if(mod.getFileName() != null && Files.exists(Util.getPath(placeFolder, mod.getFileName()))) {
                    Files.delete(Util.getPath(placeFolder, mod.getFileName()));
                }
                if(!Files.exists(Util.getPath(placeFolder, mod.getDownloadFileName())))
                    Files.createFile(Util.getPath(placeFolder, mod.getDownloadFileName()));
                mod.setFilePath(Util.getPath(placeFolder, mod.getDownloadFileName()).toAbsolutePath().toString());
                mod.setFileName(mod.getDownloadFileName());
                mod.setFileVersion(mod.getVersion());
                ConfigCopyingDialog.this.dispose();
            }catch (Exception e) {
                statusLabel.setText(String.format(translate("anerrorhasoccurred"), e.getClass().toString()+": "+e.getLocalizedMessage()));
                e.printStackTrace();
                this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
            this.event.run();
        }).start();
    }

    public void registerEvent(Runnable event) {
        this.event = event;
    }
}
