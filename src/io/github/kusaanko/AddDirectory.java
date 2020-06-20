package io.github.kusaanko;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class AddDirectory extends JDialog {

    public AddDirectory(JFrame parent, Path profileFile) {
        super(parent);
        this.setModal(true);
        this.setSize(380,150);
        this.setTitle(Language.translate("adddirectory"));
        JPanel panel = new JPanel(new GridLayout(3, 1));
        JLabel label = new JLabel(Language.translate("enterdirectorypath"));
        JPanel fieldPane = new JPanel(new BorderLayout());
        JTextField field = new JTextField();
        JButton refe = new JButton("...");
        JButton button = new JButton("OK");
        button.addActionListener(e -> {
            if(!Files.exists(Util.getPath(profileFile.getParent(), field.getText()))) {
                ok(field.getText());
            }else {
                JOptionPane.showMessageDialog(this, Language.translate("theversionalreadyexists"));
            }
        });
        label.setHorizontalAlignment(SwingConstants.CENTER);
        fieldPane.add(field, BorderLayout.CENTER);
        field.add(refe, BorderLayout.WEST);
        panel.add(label);
        panel.add(fieldPane);
        panel.add(button);
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void ok(String name) {}
}
