package io.github.kusaanko;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownloadOriginal extends JDialog {

    private static HashMap<String, String> hash = new HashMap<>();

    public static void init() {
        hash.put("1.0",   "b679fea27f2284836202e9365e13a82552092e5d");
        hash.put("1.1",   "f690d4136b0026d452163538495b9b0e8513d718");
        hash.put("1.2.1", "c7662ac43dd04bfd677694a06972a2aaaf426505");
        hash.put("1.2.2", "1dadfc4de6898751f547f24f72c7271218e4e28f");
        hash.put("1.2.3", "5134e433afeba375c00bbdcd8aead1d3222813ee");
        hash.put("1.2.4", "ad6d1fe7455857269d4185cb8f24e62cc0241aaf");
        hash.put("1.2.5", "4a2fac7504182a97dcbcd7560c6392d7c8139928");
        hash.put("1.3.1", "33167e71e85ab8e6ddbe168bc67f6ec19d708d62");
        hash.put("1.3.2", "c2efd57c7001ddf505ca534e54abf3d006e48309");
        hash.put("1.4.2", "42d6744cfbbd2958f9e6688dd6e78d86d658d0d4");
        hash.put("1.4.4", "b9b2a9e9adf1bc834647febc93a4222b4fd6e403");
        hash.put("1.4.5", "7a8a963ababfec49406e1541d3a87198e50604e5");
        hash.put("1.4.6", "116758f41b32e8d1a71a4ad6236579acd724bca7");
        hash.put("1.4.7", "53ed4b9d5c358ecfff2d8b846b4427b888287028");
        hash.put("1.5.1", "047136381a552f34b1963c43304a1ad4dc0d2d8e");
        hash.put("1.5.2", "465378c9dc2f779ae1d6e8046ebc46fb53a57968");
    }

    public DownloadOriginal(JFrame parent, String version) {
        super(parent);
        try {
            if(Files.exists(Paths.get("originals", version+".jar"))&&Util.sha1sum(Paths.get("originals", version+".jar")).equals(hash.get(version))) {
                this.dispose();
                end();
                return;
            }
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        this.setModal(true);
        JPanel panel = new JPanel(new BorderLayout());
        JLabel loading = new JLabel();
        loading.setHorizontalAlignment(JLabel.CENTER);
        loading.setPreferredSize(new Dimension(500, 300));
        panel.add(loading, BorderLayout.CENTER);
        new Thread(() -> {
            loading.setText(Language.translate("downloadingvanilla"));
            String url = "https://launcher.mojang.com/mc/game/"+version+"/client/"+hash.get(version)+"/client.jar";
            try {
                File originalFile = new File("originals/"+version+".jar");
                originalFile.getParentFile().mkdirs();
                URL var1 = new URL(url);
                byte[] var5 = new byte[8192];
                DataInputStream inputStream = new DataInputStream(var1.openStream());
                DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(originalFile));

                int len;
                long downloaded = 0;
                while((len = inputStream.read(var5, 0, var5.length)) > 0) {
                    outputStream.write(var5, 0, len);
                    downloaded += len;
                    loading.setText("<html>"+Language.translate("downloadingvanilla")+"<br>"+downloaded/1024+"KiB");
                }
                inputStream.close();
                outputStream.close();

            }catch(IOException ex) {
                ex.printStackTrace();
            }
            this.dispose();
            end();
        }).start();
        panel.add(loading, BorderLayout.CENTER);
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setTitle(Language.translate("downloadminecraft"));
        this.setVisible(true);
    }

    public void end() {}
}
