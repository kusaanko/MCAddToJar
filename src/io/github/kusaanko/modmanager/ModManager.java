package io.github.kusaanko.modmanager;

import com.google.gson.Gson;
import io.github.kusaanko.MCAddToJar;
import io.github.kusaanko.Profile;
import io.github.kusaanko.Util;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static io.github.kusaanko.Language.translate;

public class ModManager extends JDialog {
    private final DefaultListModel<String> profiles;
    private final HashMap<String, String> profileDirMap;

    public ModManager(JFrame parent, Profile profile) {
        super(parent);
        JPanel parentPane = new JPanel(new BorderLayout());
        this.profileDirMap = new HashMap<>();
        this.profiles = new DefaultListModel<>();
        if(!profile.version.equals("1.2.5")) {
            JPanel panel = new JPanel(new BorderLayout());
            JLabel scaning = new JLabel(translate("thisversionisnotsupported"));
            scaning.setFont(new Font("sanserif", Font.PLAIN, 20));
            scaning.setHorizontalAlignment(JLabel.CENTER);
            panel.add(scaning);
            parentPane.add(panel);
        }else {
            JPanel panel = new JPanel(new BorderLayout());
            JLabel scaning = new JLabel(translate("scanningprofile"));
            scaning.setFont(new Font("sanserif", Font.PLAIN, 20));
            scaning.setHorizontalAlignment(JLabel.CENTER);
            panel.add(scaning);
            parentPane.add(panel);
            new Thread(() -> {
                Path profileJson = Util.getPath(MCAddToJar.mcDir, "launcher_profiles.json");
                if(Files.exists(profileJson)) {
                    try {
                        InputStream stream = Files.newInputStream(profileJson);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] buff = new byte[512];
                        int len;
                        while((len = stream.read(buff)) != -1) {
                            baos.write(buff, 0, len);
                        }
                        stream.close();
                        Gson gson = new Gson();
                        Map profileJsonMap = gson.fromJson(new String(baos.toByteArray(), StandardCharsets.UTF_8), Map.class);
                        profileJsonMap = (Map) profileJsonMap.get("profiles");
                        for(Object object : profileJsonMap.keySet()) {
                            Map map = (Map) profileJsonMap.get(object);
                            if(profile.getVersionName().equals(map.get("lastVersionId"))) {
                                this.profiles.addElement((String) map.get("name"));
                                this.profileDirMap.put((String) map.get("name"), (String) map.get("gameDir"));
                            }
                        }
                        {
                            JPanel jPanel = new JPanel(new BorderLayout());
                            JButton ok = new JButton("OK");
                            JLabel select = new JLabel(translate("selectprofile"));
                            select.setFont(new Font("sanserif", Font.PLAIN, 20));
                            select.setHorizontalAlignment(JLabel.CENTER);
                            JList<String> profileList = new JList<>(profiles);
                            JScrollPane scrollPane = new JScrollPane(profileList);
                            Runnable open = () -> {
                                ModManager.this.dispose();
                                Path gameDir = Util.getPath(this.profileDirMap.get(profileList.getSelectedValue()));
                                if(profile.version.equals("1.2.5"))
                                    new ModManager125(parent, profile, gameDir);
                            };
                            profileList.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    if(e.getClickCount() > 1) {
                                        open.run();
                                    }
                                }
                            });
                            ok.addActionListener(e -> open.run());

                            jPanel.add(select, BorderLayout.NORTH);
                            jPanel.add(scrollPane, BorderLayout.CENTER);
                            jPanel.add(ok, BorderLayout.SOUTH);
                            parentPane.removeAll();
                            parentPane.add(jPanel);
                            parentPane.validate();
                            parentPane.repaint();

                            if(this.profileDirMap.size() == 1) {
                                profileList.setSelectedIndex(0);
                                open.run();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    JOptionPane.showMessageDialog(this, translate("profilecouldnotbedetected"));
                    this.dispose();
                }
            }).start();
        }
        this.add(parentPane);
        this.setModal(true);
        this.setTitle(translate("modmanager"));
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
