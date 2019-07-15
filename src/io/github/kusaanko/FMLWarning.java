package io.github.kusaanko;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static io.github.kusaanko.Language.*;

public class FMLWarning extends JDialog {

    public FMLWarning(JFrame parent) {
        super(parent);
        this.setModal(true);
        this.setTitle(translate("warning"));
        JPanel main = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JLabel download = label("<a href=\"\">http://files.minecraftforge.net/fmllibs/fml_libs15.zip</a>");
        download.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("http://files.minecraftforge.net/fmllibs/fml_libs15.zip"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
        download.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(label(translate("fmlwarning1")));
        panel.add(label(translate("fmlwarning2")));
        panel.add(label(translate("fmlwarning3")));
        panel.add(download);
        panel.add(label(translate("fmlwarning4")));
        panel.add(label(translate("fmlwarning5")));
        JButton button = new JButton("OK");
        button.addActionListener(e -> {
            this.dispose();
        });
        main.add(panel, BorderLayout.CENTER);
        main.add(button, BorderLayout.SOUTH);
        this.add(main);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    private JLabel label(String name) {
        JLabel label = new JLabel("<html>"+name);
        Border margin = new EmptyBorder(5,2,5,10);
        label.setBorder(margin);
        return label;
    }
}
