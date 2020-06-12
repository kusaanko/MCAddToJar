package io.github.kusaanko.modmanager;

import io.github.kusaanko.Profile;
import io.github.kusaanko.modmanager.mod125.Mod125;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
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
                System.out.println(profileDir.getAbsolutePath());
                new File(profileDir, "mods").mkdirs();
                for(File file : Objects.requireNonNull(new File(profileDir, "mods").listFiles())) {
                    String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
                    Mod mod = Mod125.is125(fileName);
                    if(mod != null) {
                        mod.setFilePath(file.getAbsolutePath());
                        mods.add(mod);
                    }
                }
                for(String filePath : profile.mcAddToJarTurn) {
                    File file = new File(filePath);
                    String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
                    Mod mod = Mod125.is125(fileName);
                    if(mod != null) {
                        mods.add(mod);
                    }
                }
                ModManagerPanel panel1 = new ModManagerPanel(this, profileDir, profile);
                for(Mod m : Mod125.mods125.values()) {
                    boolean add = true;
                    for(Mod mod : mods) {
                        if(mod.getClass() == m.getClass()) {
                            add = false;
                            break;
                        }
                    }
                    if(add) {
                        mods.add(m.clone());
                    }
                }
                for(Mod mod : mods) {
                    panel1.addMod(mod);
                }
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
