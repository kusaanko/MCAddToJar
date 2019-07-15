package io.github.kusaanko;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
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
            String rootPackageName = "io.github.kusaanko.lang".replace(".", File.separator);
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            Enumeration<URL> rootUrls = classLoader.getResources(rootPackageName);

            while (rootUrls.hasMoreElements()) {
                URL rootUrl = rootUrls.nextElement();
                Path rootPath = Paths.get(rootUrl.toURI());

                Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                        String pathName = path.toString();
                        if (pathName.endsWith(".lang")) {
                            int beginIndex = pathName.lastIndexOf(rootPackageName);
                            int endIndex = pathName.lastIndexOf(".lang");
                            String name = pathName.substring(beginIndex, endIndex).replace(File.separator, ".");
                            name = name.substring(name.lastIndexOf(".") + 1);
                            Properties properties = new Properties();
                            try {
                                properties.load(Files.newBufferedReader(path));
                                lang.put(name, properties);
                            }catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        return super.visitFile(path, attrs);
                    }
                });
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        try {
            try {
                Files.createDirectory(Paths.get("lang"));
            }catch (FileAlreadyExistsException ignore) {}
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
