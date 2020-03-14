package io.github.kusaanko;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.github.kusaanko.Language.*;

public class WhatVersion extends JDialog {
    private String[] versions = new String[]{
            translate("couldnotbedetectedversion"),
            "1.0",
            "1.1",
            "1.2.1","1.2.2","1.2.3","1.2.4","1.2.5",
            "1.3.1","1.3.2",
            "1.4.2","1.4.4","1.4.5","1.4.6","1.4.7",
            "1.5.1","1.5.2"};

    public WhatVersion(JFrame parent, File profileFile) {
        super(parent);
        this.setModal(true);
        this.setSize(380,150);
        this.setTitle(translate("selectversion"));
        JPanel panel = new JPanel(new GridLayout(3, 1));
        JLabel label = new JLabel(translate("selectminecraftversion"));
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(versions);
        JComboBox<String> box = new JComboBox<>(model);
        Pattern pattern = Pattern.compile("([0-9]*\\.?)*");
        {
            Matcher matcher = pattern.matcher(profileFile.getName().substring(0, profileFile.getName().lastIndexOf(".")));
            while(matcher.find()) {
                for (String version : versions) {
                    if (matcher.group(0).equals(version)) {
                        box.setSelectedItem(version);
                    }
                }
            }
        }
        if(box.getSelectedIndex()!=0) {
            model.removeElementAt(0);
        }
        JButton button = new JButton("OK");
        button.addActionListener(e -> {
            if(!box.getSelectedItem().equals(translate("couldnotbedetectedversion"))) ok((String) box.getSelectedItem());
        });
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);
        panel.add(box);
        panel.add(button);
        this.add(panel);
        this.setLocationRelativeTo(null);
    }

    public void ok(String version) {}
}
