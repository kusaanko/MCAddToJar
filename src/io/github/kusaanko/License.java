package io.github.kusaanko;

import javax.swing.*;

import java.awt.*;

import static io.github.kusaanko.Language.*;

public class License extends JDialog {
    public License(JFrame parent) {
        super(parent);
        JLabel label1 = new JLabel("MCAddToJar "+MCAddToJar.version);
        JLabel label2 = new JLabel("<html><a href=\"https://github.com/kusaanko/MCAddToJar\">https://github.com/kusaanko/MCAddToJar</a>");
        JLabel label3 = new JLabel("<html><font style=\"font-size: 10px;margin: 10px;\"><center>"+translate("apachelicense2.0"));
        JLabel label4 = new JLabel("<html><a href=\"http://www.apache.org/licenses/LICENSE-2.0\">http://www.apache.org/licenses/LICENSE-2.0");
        JPanel panel = new JPanel(new GridLayout(0, 1));
        label1.setHorizontalAlignment(JLabel.CENTER);
        label2.setHorizontalAlignment(JLabel.CENTER);
        label3.setHorizontalAlignment(JLabel.CENTER);
        label4.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        this.add(panel);
        this.pack();
        this.setTitle(translate("license"));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
