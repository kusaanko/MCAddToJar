package io.github.kusaanko;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.github.kusaanko.Language.*;

public class RenameVersion extends JDialog {

    public RenameVersion(JFrame parent, Path profileFile) {
        super(parent);
        this.setModal(true);
        this.setSize(380,150);
        this.setTitle(translate("renameversionname"));
        JPanel panel = new JPanel(new GridLayout(3, 1));
        JLabel label = new JLabel(translate("enteranewversionname"));
        JTextField field = new JTextField(profileFile.getFileName().toString());
        JButton button = new JButton("OK");
        button.addActionListener(e -> {
            if(!Files.exists(Util.getPath(profileFile.getParent(), field.getText()))) {
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
