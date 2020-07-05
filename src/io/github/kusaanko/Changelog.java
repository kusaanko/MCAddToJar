package io.github.kusaanko;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.nio.file.Files;
import java.util.List;

import static io.github.kusaanko.Language.language;
import static io.github.kusaanko.Language.translate;

public class Changelog extends JDialog {

    public Changelog(JFrame parent, String link) {
        super(parent);
        this.setModal(true);
        this.setTitle(translate("changelog"));
        JPanel main = new JPanel(new BorderLayout());
        {
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
            main.add(pane, BorderLayout.CENTER);
        }
        JButton button = new JButton("<html><font style=\"font-size: 14px;\">OK");
        button.addActionListener(e -> {
            this.dispose();
        });
        main.add(button, BorderLayout.SOUTH);
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
