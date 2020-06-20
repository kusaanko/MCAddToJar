package io.github.kusaanko.modmanager;

import io.github.kusaanko.Profile;
import io.github.kusaanko.Util;
import io.github.kusaanko.modmanager.mod125.Mod125;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static io.github.kusaanko.Language.translate;

public class ModManager125 extends JDialog {
    private ArrayList<Mod> mods = new ArrayList<>();

    public ModManager125(JFrame parent, Profile profile, Path profileDir) {
        super(parent);
        JPanel parentPane = new JPanel(new BorderLayout());
        {
            JPanel panel = new JPanel(new BorderLayout());
            JLabel scaning = new JLabel(translate("scanningmods"));
            scaning.setFont(new Font("sanserif", Font.PLAIN, 20));
            scaning.setHorizontalAlignment(JLabel.CENTER);
            panel.add(scaning);
            parentPane.add(panel);
            new Thread(() -> {
                try {
                    Files.createDirectories(Util.getPath(profileDir, "mods"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                java.util.List<Path> files = null;
                try {
                    files = Files.list(Util.getPath(profileDir, "mods")).collect(Collectors.toList());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for(String filePath : profile.mcAddToJarTurn) {
                    files.add(Util.getPath(filePath));
                }
                for (Mod mod : Mod125.mods125.values()) {
                    boolean added = false;
                    for(Path file : files) {
                        if (Files.isRegularFile(file) && (file.getFileName().toString().endsWith(".zip") || file.getFileName().toString().endsWith(".jar"))) {
                            String fileName = file.getFileName().toString().substring(0, file.getFileName().toString().lastIndexOf("."));
                            String ver = mod.is(fileName);
                            if (ver != null) {
                                Mod m = mod.clone();
                                m.setFileName(fileName);
                                m.setFilePath(file.toAbsolutePath().toString());
                                m.setFileVersion(ver);
                                mods.add(m);
                                added = true;
                                break;
                            }
                        }
                    }
                    if(!added) {
                        mods.add(mod.clone());
                    }
                }
                ModManagerPanel panel1 = new ModManagerPanel(this, profileDir, profile);
                for(Mod mod : mods) {
                    panel1.addMod(mod);
                }
                panel1.updatePanes();
                parentPane.removeAll();
                parentPane.add(panel1);
                parentPane.validate();
                parentPane.repaint();
            }).start();
        }
        this.add(parentPane);
        this.setModal(true);
        this.setTitle(translate("modmanager"));
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
