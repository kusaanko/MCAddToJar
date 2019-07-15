package io.github.kusaanko;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import static io.github.kusaanko.Language.*;

public class RenameVersion extends JDialog {

    public RenameVersion(JFrame parent, File profileFile) {
        super(parent);
        this.setModal(true);
        this.setSize(380,150);
        this.setTitle(translate("renameversionname"));
        JPanel panel = new JPanel(new GridLayout(3, 1));
        JLabel label = new JLabel(translate("enteranewversionname"));
        JTextField field = new JTextField(profileFile.getName());
        JButton button = new JButton("OK");
        button.addActionListener(e -> {
            if(!new File(profileFile.getParentFile(), field.getText()).exists()) {
                ok(field.getText().replace("\"", ""));
            }else {
                JOptionPane.showMessageDialog(this, translate("theversionalreadyexists"));
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
