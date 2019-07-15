package io.github.kusaanko;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CopyVersion extends JDialog {

    public CopyVersion(JFrame parent, File profileFile) {
        super(parent);
        this.setModal(true);
        this.setSize(380,150);
        this.setTitle(Language.translate("copyversion"));
        JPanel panel = new JPanel(new GridLayout(3, 1));
        JLabel label = new JLabel(Language.translate("enteranewversionname"));
        JTextField field = new JTextField(profileFile.getName()+" - copy");
        JButton button = new JButton("OK");
        button.addActionListener(e -> {
            if(!new File(profileFile.getParentFile(), field.getText()).exists()) {
                ok(field.getText().replace("\"", ""));
            }else {
                JOptionPane.showMessageDialog(this, Language.translate("theversionalreadyexists"));
            }
        });
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);
        panel.add(field);
        panel.add(button);
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void ok(String name) {}
}
