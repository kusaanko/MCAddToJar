package io.github.kusaanko;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.github.kusaanko.Language.*;

public class NewVersionAvailable extends JDialog {

    public NewVersionAvailable(JFrame parent, String version) {
        super(parent);
        this.setModal(true);
        this.setTitle(translate("newversion"));
        JPanel main = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JPanel textpanel = new JPanel(new GridLayout(0, 1));
        JPanel buttonpanel = new JPanel(new GridLayout(1, 0));
        textpanel.add(label(translate("newversionisavailable")));
        textpanel.add(label(translate("currentversion")+":<font style=\"color: red;font-weight: bold;\">"+MCAddToJar.version));
        textpanel.add(label(translate("newversion")+":<font style=\"color: green;font-weight: bold;\">"+version));
        textpanel.add(label("<font style=\"font-size: 20px;font-weight: bold;\">"+translate("changelog")));
        panel.add(textpanel);
        {
            String body = Util.executeGet("https://api.github.com/repos/kusaanko/MCAddToJar/releases");
            Matcher matcher = Pattern.compile("\"body\":\"([^\"]*)\"").matcher(body);
            if(matcher.find()) {
                body = matcher.group(1);
                JScrollPane pane = new JScrollPane(label("<font style=\"font-weight: bold;\">"+body.replace("\r", "").replace("\n", "<br>").replace("\\r", "").replace("\\n", "<br>")));
                pane.setPreferredSize(new Dimension(100, 100));
                pane.setSize(200, 100);
                panel.add(pane);
            }
        }
        JButton button = new JButton("<html><font style=\"font-size: 14px;\">OK");
        button.addActionListener(e -> {
            this.dispose();
        });
        JButton update = new JButton("<html><font style=\"font-size: 14px;\">"+translate("updatenow"));
        update.addActionListener(e -> {
            if(!new File("MCAddToJar.jar").exists()) {
                JOptionPane.showMessageDialog(this, translate("executablefilename"));
            }
            parent.dispose();
            Updater.main(new String[]{"https://github.com/kusaanko/MCAddToJar/releases/download/"+version+"/MCAddToJar.jar",
                                        "replace:MCAddToJar.jar",
                                        "MCAddToJar",
                                        "java -jar MCAddToJar.jar"});
        });
        main.add(panel, BorderLayout.CENTER);
        buttonpanel.add(button);
        buttonpanel.add(update);
        main.add(buttonpanel, BorderLayout.SOUTH);
        this.add(main);
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private JLabel label(String name) {
        JLabel label = new JLabel("<html><font style=\"font-size: 14px;\">"+name);
        Border margin = new EmptyBorder(5,2,5,10);
        label.setBorder(margin);
        return label;
    }
}
