package io.github.kusaanko.modmanager;

import io.github.kusaanko.Profile;
import io.github.kusaanko.modmanager.mod125.Mod125;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static io.github.kusaanko.Language.translate;

public class ModManager125 extends JDialog {
    private ArrayList<Mod> mods = new ArrayList<>();

    public ModManager125(JFrame parent, Profile profile, File profileDir) {
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
                new File(profileDir, "mods").mkdirs();
                ArrayList<File> files = new ArrayList<>(Arrays.asList(Objects.requireNonNull(new File(profileDir, "mods").listFiles())));
                for(String filePath : profile.mcAddToJarTurn) {
                    File file = new File(filePath);
                    files.add(file);
                }
                for (Mod mod : Mod125.mods125.values()) {
                    boolean added = false;
                    for(File file : files) {
                        if (file.isFile() && (file.getName().endsWith(".zip") || file.getName().endsWith(".jar"))) {
                            String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
                            String ver = mod.is(fileName);
                            if (ver != null) {
                                Mod m = mod.clone();
                                m.setFileName(fileName);
                                m.setFilePath(file.getAbsolutePath());
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
