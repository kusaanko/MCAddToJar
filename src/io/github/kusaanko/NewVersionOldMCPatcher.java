package io.github.kusaanko;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.github.kusaanko.Language.translate;

public class NewVersionOldMCPatcher extends JDialog {

    public NewVersionOldMCPatcher(JFrame parent, String prever, String newver, File profileFolder) {
        super(parent);
        this.setModal(true);
        this.setTitle(translate("newversion"));
        JPanel main = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JPanel textpanel = new JPanel(new GridLayout(0, 1));
        JPanel buttonpanel = new JPanel(new GridLayout(1, 0));
        textpanel.add(label(translate("oldmcpatcherupdated")));
        textpanel.add(label(translate("preversion")+":<font style=\"color: red;font-weight: bold;\">"+prever));
        textpanel.add(label(translate("currentversion")+":<font style=\"color: green;font-weight: bold;\">"+newver));
        textpanel.add(label("<font style=\"font-size: 20px;font-weight: bold;\">"+translate("changelog")));
        panel.add(textpanel);
        {
            String body = Util.executeGet("https://api.github.com/repos/kusaanko/OldMCPatcher/releases");
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
            if(profileFolder.listFiles().length>0&&JOptionPane.showConfirmDialog(MCAddToJar.frame, translate("reoutputallprofiles"), translate("confirm"), JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                for(File f : profileFolder.listFiles()) {
                    if(f.getName().endsWith(".profile")) {
                        Profile profile = Profile.load(f);
                        final boolean[] ended = {false};
                        String profileName = f.getName().substring(0, f.getName().lastIndexOf("."));
                        AddToJar addToJar = new AddToJar(new File(MCAddToJar.mcDir, "versions/"+profileName),
                                profileName, profile, true) {
                            @Override
                            public void outputEnd() {
                                ended[0] = true;
                                this.dispose();
                            }
                        };
                        addToJar.output();
                        while(!ended[0]) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        main.add(panel, BorderLayout.CENTER);
        buttonpanel.add(button);
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
