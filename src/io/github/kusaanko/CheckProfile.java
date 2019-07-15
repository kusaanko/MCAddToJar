package io.github.kusaanko;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckProfile extends JDialog {

    public CheckProfile(JFrame parent, File profileFile) {
        super(parent);
        this.setModal(true);
        this.setSize(380,150);
        this.setTitle("io.github.kusaanko.MCAddToJar");
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JPanel panel1 = new JPanel(new GridLayout(1, 0));
        JLabel label = new JLabel(String.format(Language.translate("whathappenedprofile"), profileFile.getName().substring(0, profileFile.getName().lastIndexOf("."))));
        JButton delete = new JButton(Language.translate("deleted"));
        JButton rename = new JButton(Language.translate("renamed"));
        delete.addActionListener(e -> {
            delete();
        });
        rename.addActionListener(e -> {
            rename();
        });
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel1.add(delete);
        panel1.add(rename);
        panel.add(label);
        panel.add(panel1);
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    public void delete() {}
    public void rename() {}
}
