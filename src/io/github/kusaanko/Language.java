package io.github.kusaanko;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;
import java.util.stream.Collectors;

public class Language {
    public static HashMap<String, Properties> lang = new HashMap<>();
    public static HashMap<String, String> langName = new HashMap<>();
    public static String language;

    public static void init() {
        try {
            try {
                Files.createDirectory(Paths.get("lang"));
            }catch (FileAlreadyExistsException ignore) {}
            {
                InputStream in = Language.class.getResourceAsStream("lang/en_US.lang");
                FileOutputStream stream = new FileOutputStream(new File("lang/en_US.lang"));
                byte[] buff = new byte[8192];
                int len;
                while((len = in.read(buff))!=-1) {
                    stream.write(buff, 0, len);
                }
                in.close();
                stream.close();
            }
            {
                InputStream in = Language.class.getResourceAsStream("lang/ja_JP.lang");
                FileOutputStream stream = new FileOutputStream(new File("lang/ja_JP.lang"));
                byte[] buff = new byte[8192];
                int len;
                while((len = in.read(buff))!=-1) {
                    stream.write(buff, 0, len);
                }
                in.close();
                stream.close();
            }
            for(Path path : Files.list(Paths.get("lang/")).collect(Collectors.toList())) {
                Properties properties = new Properties();
                try {
                    properties.load(Files.newBufferedReader(path));
                    String name = path.getFileName().toString();
                    lang.put(name.substring(0, name.lastIndexOf(".")), properties);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        JMenuBar menuBar = MCAddToJar.frame.getJMenuBar();
        JMenu menu = new JMenu("Language");
        ButtonGroup group = new ButtonGroup();
        for(String key : lang.keySet()) {
            Properties properties = lang.get(key);
            String name = properties.getProperty("lang_name");
            JCheckBoxMenuItem item = new JCheckBoxMenuItem(name);
            menu.add(item);
            group.add(item);
            langName.put(name, key);
            item.addActionListener(e -> {
                Config.put("lang", key);
                language = key;
                JOptionPane.showMessageDialog(MCAddToJar.frame, translate("restartnow"));
                System.exit(0);
            });
        }
        language = Config.get("lang", lang.get(Locale.getDefault().toString())!=null?Locale.getDefault().toString():"en_US");
        Enumeration<AbstractButton> en = group.getElements();
        AbstractButton button;
        while(en.hasMoreElements()) {
            button = en.nextElement();
            if(button.getText().equals(translate("lang_name"))) {
                button.setSelected(true);
            }
        }
        menu.setText(translate("language"));
        menuBar.add(menu);
    }

    public static String translate(String key) {
        String value = lang.get(language).getProperty(key);
        if(value==null) {
            return lang.get("en_US").getProperty(key, key);
        }
        return value;
    }
}
