package io.github.kusaanko;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownloadForge extends JDialog {
    public DownloadForge(JFrame parent, String version) {
        super(parent);
        this.setModal(true);
        JPanel panel = new JPanel(new BorderLayout());
        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> list = new JList<>(model);
        JScrollPane pane = new JScrollPane(list);
        pane.setPreferredSize(new Dimension(500, 300));
        JLabel loading = new JLabel(Language.translate("loadingforgeversion"));
        loading.setHorizontalAlignment(JLabel.CENTER);
        loading.setPreferredSize(new Dimension(500, 300));
        new Thread(() -> {
            String body = Util.executeGet("https://files.minecraftforge.net/net/minecraftforge/forge/index_"+version+".html");
            if(body!=null) {
                Matcher matcher = Pattern.compile("([0-9]\\.[0-9]\\.[0-9]\\.[0-9]+)").matcher(body);
                HashMap<String, String> a = new HashMap<>();
                while(matcher.find()) {
                    a.put(matcher.group(1),"");
                }
                ArrayList<String> b = new ArrayList<>(a.keySet());
                b.sort((o1, o2) -> o2.compareToIgnoreCase(o1));
                for(String key : b) {
                    model.addElement(key);
                }
                panel.remove(0);
                panel.add(pane, BorderLayout.CENTER);
                validate();
                repaint();
            }
        }).start();
        JButton ok = new JButton("OK");
        ok.addActionListener(e -> {
            if(list.getSelectedIndex()!=-1) {
                new Thread(() -> {
                    panel.removeAll();
                    loading.setText(Language.translate("downloadingforge"));
                    panel.add(loading, BorderLayout.CENTER);
                    validate();
                    repaint();
                    try {
                        Files.createDirectories(Util.getPath("forge"));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    String kind = "universal";
                    if(version.startsWith("1.1")||version.startsWith("1.2")) {
                        kind = "client";
                    }
                    String url = "https://maven.minecraftforge.net/net/minecraftforge/forge/"+version+"-"+list.getSelectedValue()+"/forge-"+version+"-"+list.getSelectedValue()+"-"+kind+".zip";
                    Path output = Util.getPath("forge/forge-"+version+"-"+list.getSelectedValue()+"-"+kind+".zip");
                    try {
                        Files.createDirectories(output.getParent());
                        URL var1 = new URL(url);
                        byte[] var5 = new byte[8192];
                        DataInputStream inputStream = new DataInputStream(var1.openStream());
                        DataOutputStream outputStream = new DataOutputStream(Files.newOutputStream(output));

                        int len;
                        long downloaded = 0;
                        while((len = inputStream.read(var5, 0, var5.length)) > 0) {
                            outputStream.write(var5, 0, len);
                            downloaded += len;
                            loading.setText("<html>"+Language.translate("downloadingforge")+"<br>"+downloaded/1024+"KiB");
                        }
                        inputStream.close();
                        outputStream.close();

                    }catch(IOException ex) {
                        ex.printStackTrace();
                    }
                    end(output);
                }).start();
            }
        });
        panel.add(loading, BorderLayout.CENTER);
        panel.add(ok, BorderLayout.SOUTH);
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle(Language.translate("downloadforge"));
        this.setVisible(true);
    }

    public void end(Path path) {}
}
