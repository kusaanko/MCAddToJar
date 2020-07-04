package io.github.kusaanko;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.github.kusaanko.Language.*;

public class NewVersionAvailable extends JDialog {

    public NewVersionAvailable(JFrame parent, String version) {
        super(parent);
        this.setModal(true);
        this.setTitle(translate("newversion"));
        JPanel main = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        JPanel textpanel = new JPanel(new GridLayout(0, 1));
        JPanel buttonpanel = new JPanel(new GridLayout(1, 0));
        textpanel.add(label(translate("newversionisavailable")));
        textpanel.add(label(translate("currentversion")+":<font style=\"color: red;font-weight: bold;\">"+MCAddToJar.version));
        textpanel.add(label(translate("newversion")+":<font style=\"color: green;font-weight: bold;\">"+version));
        textpanel.add(label("<font style=\"font-size: 20px;font-weight: bold;\">"+translate("changelog")));
        panel.add(textpanel, BorderLayout.NORTH);
        {
            String link = "https://api.github.com/repos/kusaanko/MCAddToJar/releases";
            if(language.equals("ja_JP")) {
                link = "https://raw.githubusercontent.com/kusaanko/MCAddToJar/master/changelog_jp.json";
            }
            String body = Util.executeGet(link);

            List<Object> jsonList = new Gson().fromJson(body, List.class);
            String changelog = "";
            for(Object obj : jsonList) {
                LinkedTreeMap<String, Object> map = Util.toMap(obj);
                changelog += "<font style=\"font-weight: bold;font-size: 20px;\">" + map.get("name") + "</font>\n";
                changelog += map.get("body") + "\n\n";
            }
            JScrollPane pane = new JScrollPane(label(changelog.replace("\r", "").replace("\n", "<br>").replace("\\r", "").replace("\\n", "<br>")));
            pane.getVerticalScrollBar().setUnitIncrement(25);
            pane.setPreferredSize(new Dimension(100, 200));
            pane.setSize(100, 200);
            panel.add(pane, BorderLayout.CENTER);
        }
        JButton button = new JButton("<html><font style=\"font-size: 14px;\">OK");
        button.addActionListener(e -> {
            this.dispose();
        });
        JButton update = new JButton("<html><font style=\"font-size: 14px;\">"+translate("updatenow"));
        update.addActionListener(e -> {
            if(!Files.exists(Util.getPath("MCAddToJar.jar"))) {
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
        this.setSize(600, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private JLabel label(String name) {
        JLabel label = new JLabel("<html><font style=\"font-size: 14px;font-family: sanserif;\">"+name);
        Border margin = new EmptyBorder(5,2,5,10);
        label.setBorder(margin);
        return label;
    }
}
