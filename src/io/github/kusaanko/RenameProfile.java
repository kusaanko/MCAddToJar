package io.github.kusaanko;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class RenameProfile extends JDialog {

    public RenameProfile(JFrame parent) {
        super(parent);
        this.setModal(true);
        this.setSize(380,150);
        this.setTitle("MCAddToJar");
        JPanel panel = new JPanel(new GridLayout(3, 1));
        JLabel label = new JLabel(Language.translate("pleaseselectaname"));
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        JComboBox<String> box = new JComboBox<>(model);
        try {
            for(Path f : Files.list(Util.getPath(MCAddToJar.mcDir, "versions")).collect(Collectors.toList())) {
                if(Files.isDirectory(f)) {
                    model.addElement(f.getFileName().toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JButton button = new JButton("OK");
        button.addActionListener(e -> {
            ok((String) box.getSelectedItem());
        });
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);
        panel.add(box);
        panel.add(button);
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    public void ok(String name) {}
}
