package io.github.kusaanko;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static io.github.kusaanko.Language.language;
import static io.github.kusaanko.Language.translate;

public class NewVersionOldMCPatcher extends JDialog {

    public NewVersionOldMCPatcher(JFrame parent, String prever, String newver, Path profileFolder) {
        super(parent);
        this.setModal(true);
        this.setTitle(translate("newversion"));
        JPanel main = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        JPanel textpanel = new JPanel(new GridLayout(0, 1));
        JPanel buttonpanel = new JPanel(new GridLayout(1, 0));
        textpanel.add(label(translate("oldmcpatcherupdated")));
        textpanel.add(label(translate("preversion")+":<font style=\"color: red;font-weight: bold;\">"+prever));
        textpanel.add(label(translate("currentversion")+":<font style=\"color: green;font-weight: bold;\">"+newver));
        textpanel.add(label("<font style=\"font-size: 20px;font-weight: bold;\">"+translate("changelog")));
        panel.add(textpanel, BorderLayout.NORTH);
        {
            String link = "https://api.github.com/repos/kusaanko/OldMCPatcher/releases";
            if(language.equals("ja_JP")) {
                link = "https://raw.githubusercontent.com/kusaanko/OldMCPatcher/master/changelog_jp.json";
            }
            String body = Util.executeGet(link);

            java.util.List<Object> jsonList = new Gson().fromJson(body, List.class);
            String changelog = "";
            for(Object obj : jsonList) {
                LinkedTreeMap<String, Object> map = Util.toMap(obj);
                changelog += "<font style=\"font-weight: bold;font-size: 20px;\">" + map.get("name") + "</font>\n";
                changelog += map.get("body") + "\n\n";
            }
            JScrollPane pane = new JScrollPane(label(changelog.replace("\r", "").replace("\n", "<br>").replace("\\r", "").replace("\\n", "<br>")));
            pane.setPreferredSize(new Dimension(100, 100));
            pane.setSize(200, 100);
            pane.getVerticalScrollBar().setUnitIncrement(10);
            panel.add(pane, BorderLayout.CENTER);
        }
        JButton button = new JButton("<html><font style=\"font-size: 14px;\">OK");
        button.addActionListener(e -> {
            this.dispose();
            try {
                if(Files.list(profileFolder).count() > 0 && JOptionPane.showConfirmDialog(MCAddToJar.frame, translate("reoutputallprofiles"), translate("confirm"), JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                    for(Path f : Files.list(profileFolder).collect(Collectors.toList())) {
                        if(f.getFileName().toString().endsWith(".profile")) {
                            Profile profile = Profile.load(f);
                            final boolean[] ended = {false};
                            String profileName = f.getFileName().toString().substring(0, f.getFileName().toString().lastIndexOf("."));
                            if(Files.exists(Util.getPath(MCAddToJar.mcDir, "versions/"+profileName))) {
                                AddToJar addToJar = new AddToJar(Util.getPath(MCAddToJar.mcDir, "versions/" + profileName),
                                        profileName, profile, true) {
                                    @Override
                                    public void outputEnd() {
                                        ended[0] = true;
                                        this.dispose();
                                    }
                                };
                                addToJar.output();
                                while (!ended[0]) {
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        main.add(panel, BorderLayout.CENTER);
        buttonpanel.add(button);
        main.add(buttonpanel, BorderLayout.SOUTH);
        this.add(main);
        this.setSize(600, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private JLabel label(String name) {
        JLabel label = new JLabel("<html><font style=\"font-size: 14px;font-family: sanserif\">"+name);
        Border margin = new EmptyBorder(5,2,5,10);
        label.setBorder(margin);
        return label;
    }
}
