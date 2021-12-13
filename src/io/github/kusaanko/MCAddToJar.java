package io.github.kusaanko;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static io.github.kusaanko.Language.*;

public class MCAddToJar extends JFrame {
    public static Path mcDir;
    public static final String version = "2.3.0";
    public static final String repo = "https://github.com/kusaanko/MCAddToJar/releases";
    public static MCAddToJar frame;
    public static AddToJar addToJar;
    private static String oldMCPatcherVersion = "Unknown";

    public static void main(String[] args) {
        Config.load();
        Language.init();
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty(
                "com.apple.mrj.application.apple.menu.about.name",
                "MCAddToJar");
        if(args.length>0) {
            mcDir = Util.getPath(args[0]);
            if(!Files.exists(mcDir)) {
                mcDir = null;
                JOptionPane.showMessageDialog(null, args[0]+translate("isnotexist"));
                JOptionPane.showMessageDialog(null, translate("plssetdotmc"));
            }else {
                JOptionPane.showMessageDialog(null, translate("dotmcalert"));
                Config.put("minecraftDir", args[0]);
            }
        }else {
            String dir = Config.get("minecraftDir", "");
            if(!dir.isEmpty()) {
                mcDir = Util.getPath(dir);
                if(!Files.exists(mcDir)) {
                    mcDir = null;
                    JOptionPane.showMessageDialog(null, dir + translate("isnotexist"));
                }
            }
        }
        if(mcDir==null) {
            mcDir = Util.getWorkingDirectory("minecraft");
            if (!Files.exists(mcDir)) {
                JOptionPane.showMessageDialog(null, translate("dotmcdoesnotexist") + ": " + mcDir);
                String data;
                if((data = JOptionPane.showInputDialog(null, translate("enterdirectorypath")+"("+translate("relativepathallowed")+")"))!=null) {
                    Config.put("minecraftDir", data);
                    mcDir = Util.getPath(data);
                    if (!Files.exists(mcDir)) {
                        JOptionPane.showMessageDialog(null, translate("dotmcdoesnotexist") + ": " + mcDir);
                        return;
                    }
                }else {
                    return;
                }
            }
        }
        DownloadOriginal.init();
        Runtime.getRuntime().addShutdownHook(new Thread(Config::save));
        new MCAddToJar();
        Config.put("lastVersion", version);
    }

    private Profile profile;
    private DefaultListModel<String> model;
    public MCAddToJar() {
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        frame = this;
        Language.initMenu();
        {
            JMenu help = new JMenu(translate("help"));
            JMenuItem menu = new JMenuItem(translate("license"));
            menu.addActionListener(e -> new License(this));
            help.add(menu);
            JMenuItem checkoldmcpatcherver = new JMenuItem(translate("checktheversionofOldMCPatcher"));
            checkoldmcpatcherver.addActionListener(ev -> {
                JOptionPane.showMessageDialog(this, "OldMCPatcher " + oldMCPatcherVersion);
            });
            JMenuItem see = new JMenuItem(translate("seeupdatehistory"));
            see.addActionListener(ev -> {
                String link = "https://api.github.com/repos/kusaanko/MCAddToJar/releases";
                if(language.equals("ja_JP")) {
                    link = "https://raw.githubusercontent.com/kusaanko/MCAddToJar/master/changelog_jp.json";
                }
                new Changelog(this, link);
            });
            JMenuItem seeoldmcpatcher = new JMenuItem(translate("seeupdatehistoryofoldmcpatcher"));
            seeoldmcpatcher.addActionListener(ev -> {
                String link = "https://api.github.com/repos/OldMCPatcher/MCAddToJar/releases";
                if(language.equals("ja_JP")) {
                    link = "https://raw.githubusercontent.com/kusaanko/OldMCPatcher/master/changelog_jp.json";
                }
                new Changelog(this, link);
            });
            help.add(checkoldmcpatcherver);
            help.add(see);
            help.add(seeoldmcpatcher);
            menuBar.add(help);
        }
        {
            JMenu settings = new JMenu(translate("settings"));
            JMenuItem menu = new JMenuItem(translate("setpathdotmc"));
            menu.addActionListener(e -> {
                String data;
                if((data = JOptionPane.showInputDialog(this, translate("enterdirectorypath")+"("+translate("relativepathallowed")+")"))!=null) {
                    Config.put("minecraftDir", data);
                    JOptionPane.showMessageDialog(this, translate("restartnow"));
                }
            });
            settings.add(menu);
            menuBar.add(settings);
        }
        {
            String[] versions = {"1.0", "1.1", "1.2.x", "1.3.x", "1.4.x", "1.5.x", "others"};
            ArrayList<JCheckBoxMenuItem> menuItems = new ArrayList<>();
            JMenu versionstoshow = new JMenu(translate("versionstoshow"));
            JMenuItem checkAll = new JMenuItem(translate("checkall"));
            JMenuItem uncheckAll = new JMenuItem(translate("uncheckall"));

            checkAll.addActionListener(e -> {
                menuItems.forEach(item -> item.setSelected(true));
                for(String version : versions) {
                    Config.put("show" + version, "true");
                }
                update();
            });
            uncheckAll.addActionListener(e -> {
                menuItems.forEach(item -> item.setSelected(false));
                for(String version : versions) {
                    Config.put("show" + version, "false");
                }
                update();
            });
            versionstoshow.add(checkAll);
            versionstoshow.add(uncheckAll);

            for(String version : versions) {
                JCheckBoxMenuItem ver = new JCheckBoxMenuItem(translate(version));
                menuItems.add(ver);
                versionstoshow.add(ver);
                ver.setSelected(Boolean.parseBoolean(Config.get("show" + version, "true")));
                ver.addActionListener(e -> {
                    Config.put("show" + version, String.valueOf(ver.isSelected()));
                    update();
                });
            }
            menuBar.add(versionstoshow);
        }
        new Thread(() -> {
            try {
                URL url = new URL(repo+"/latest");
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setInstanceFollowRedirects(false);
                connection.connect();
                if(connection.getResponseCode()==302) {
                    String newURL = connection.getHeaderField("Location");
                    String latest = newURL.substring(newURL.lastIndexOf("/")+1);
                    String[] curver = version.split("\\.");
                    String[] ver = latest.split("\\.");
                    boolean newVersionAvailable = false;
                    for (int i = 0; i < Math.max(curver.length, ver.length); i++) {
                        String cv = i < curver.length ? curver[i] : "0";
                        String v = i < ver.length ? ver[i] : "0";
                        try {
                            if (Integer.parseInt(cv) < Integer.parseInt(v)) {
                                newVersionAvailable = true;
                                break;
                            }
                            if (Integer.parseInt(cv) > Integer.parseInt(v)) {
                                break;
                            }
                        } catch (NumberFormatException ignore) {
                        }
                    }
                    if(newVersionAvailable) {
                        new NewVersionAvailable(this, latest);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        if(!Files.exists(Util.getPath(mcDir, "versions/"))) {
            if(!Files.exists(Util.getPath(mcDir, "bin/minecraft.jar"))) {
                JOptionPane.showMessageDialog(null, translate("couldnotfindminecraftjarfile"));
                return;
            }else {
                Path profileFile = Util.getPath("profiles","minecraft.profile");
                try {
                    Files.createDirectories(profileFile.getParent());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                profile = Profile.load(profileFile);
                if(profile==null) {
                    WhatVersion whatVersion = new WhatVersion(MCAddToJar.this, profileFile) {
                        @Override
                        public void ok(String version) {
                            profile = new Profile(new HashMap<>(), new ArrayList<>(), profileFile, version);
                            MCAddToJar.this.dispose();
                            this.dispose();
                            new AddToJar(Util.getPath(mcDir, "bin/"), "minecraft", profile, false);
                        }
                    };
                    whatVersion.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    whatVersion.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    whatVersion.setVisible(true);
                }else {
                    MCAddToJar.this.dispose();
                    new AddToJar(Util.getPath(mcDir, "bin/"), "minecraft", profile, false);
                }
                return;
            }
        }
        new Thread(() -> {
            String sums = Util.executeGet("https://raw.githubusercontent.com/kusaanko/OldMCPatcher/master/sum.json");
            HashMap<String, String> sum = new HashMap<>();
            boolean a = false;
            String newSum = null;
            String newVer = null;
            for(String line : sums.split("\n")) {
                if(!line.contains(":")) continue;
                line = line.replaceAll("[\"\\s,]","");
                String key = line.substring(0, line.indexOf(":"));
                String value = line.substring(line.indexOf(":")+1);
                sum.put(key, value);
                if(!a) {
                    a = true;
                    newSum = value;
                    newVer = key;
                }
            }
            try {
                String fileSum;
                boolean isUpdate = false;
                if(Files.exists(Util.getPath("OldMCPatcher.zip"))) {
                    fileSum = Util.sha256sum(Util.getPath("OldMCPatcher.zip"));
                    isUpdate = true;
                }else {
                    fileSum = "";
                }
                String version = null;
                for(String key : sum.keySet()) {
                    if(sum.get(key).equals(fileSum)) {
                        version = key;
                    }
                }
                oldMCPatcherVersion = version!=null?version:"Unknown";
                if(!fileSum.equals(newSum)) {
                    Util.downloadFile("https://github.com/kusaanko/OldMCPatcher/releases/download/"+newVer+"/OldMCPatcher-"+newVer+".zip", "OldMCPatcher.zip");
                    Path profileFolder = Util.getPath("profiles/");
                    if(!Files.exists(profileFolder)) {
                        try {
                            Files.createDirectories(profileFolder);
                        }catch (Exception e) {
                            e.printStackTrace();
                            throw new IOException("Couldn't make the profile directory!");
                        }
                    }
                    if(isUpdate) {
                        new NewVersionOldMCPatcher(MCAddToJar.frame, version!=null?version:"Unknown", newVer, profileFolder);
                    }else {
                        JOptionPane.showMessageDialog(this, (String.format(translate("oldmcpatcherdownloaded"), newVer)));
                    }
                }
            } catch (NoSuchAlgorithmException | IOException e) {
                e.printStackTrace();
            }
        }).start();
        this.setSize(380,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        model = new DefaultListModel<>();
        JList<String> list = new JList(model) {
            @Override
            public Object getSelectedValue() {
                return super.getSelectedValue().toString().replaceAll("\\(Modded\\)$", "");
            }
        };
        JScrollPane pane = new JScrollPane(list);
        JPanel panel = new JPanel(new BorderLayout());
        JPanel controls = new JPanel(new GridLayout(1, 0));
        JButton copy = new JButton(translate("copy"));
        JButton rename = new JButton(translate("rename"));
        copy.addActionListener(e -> {
            if(list.getSelectedIndex()==-1) return;
            Path profileFile = Util.getPath(mcDir,"versions/"+list.getSelectedValue());
            new CopyVersion(this, profileFile) {
                @Override
                public void ok(String name) {
                    this.dispose();
                    try {
                        Path newFile = Util.getPath(mcDir, "versions/"+name);
                        Files.createDirectories(newFile);
                        Util.copy(Util.getPath(profileFile, profileFile.getFileName().toString()+".jar"), Util.getPath(newFile, name+".jar"));
                        Util.copy(Util.getPath(profileFile, profileFile.getFileName().toString()+".json"), Util.getPath(newFile, name+".json"));
                        if(Files.exists(Util.getPath(profileFile,profileFile.getFileName().toString()+"-original.jar"))) Util.copy(Util.getPath(profileFile,profileFile.getFileName().toString()+"-original.jar"), Util.getPath(newFile, name+"-original.jar"));
                        if(Files.exists(Util.getPath("profiles/"+profileFile.getFileName().toString()+".profile"))) Util.copy(Util.getPath("profiles/"+profileFile.getFileName().toString()+".profile"), Util.getPath("profiles/"+name+".profile"));
                        BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(Util.getPath(newFile, name+".json")), StandardCharsets.UTF_8));

                        String line;
                        String text = "";
                        while((line = br.readLine())!=null) {
                            text+=line+"\n";
                        }
                        text = text.substring(0, text.length()-1);

                        text = text.replace("\"id\": \""+profileFile.getFileName().toString()+"\"","\"id\": \""+name+"\"");

                        br.close();

                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Util.getPath(newFile, name+".json")),"UTF-8"));
                        bw.write(text);
                        bw.close();

                        MCAddToJar.this.update();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            };
        });
        rename.addActionListener(e -> {
            if(list.getSelectedIndex()==-1) return;
            Path profileFile = Util.getPath(mcDir,"versions/"+list.getSelectedValue());
            new RenameVersion(this, profileFile) {
                @Override
                public void ok(String name) {
                    this.dispose();
                    try {
                        Path newFile = Util.getPath(mcDir, "versions/"+name);
                        Files.move(profileFile, newFile);
                        Files.move(Util.getPath("profiles/"+profileFile.getFileName().toString()+".profile"), Util.getPath("profiles/"+name+".profile"));
                        Files.move(Util.getPath(newFile,profileFile.getFileName().toString()+".jar"), Util.getPath(newFile, name+".jar"));
                        if(profile.profile_version==0) Files.move(Util.getPath(newFile,profileFile.getFileName().toString()+"-original.jar"), Util.getPath(newFile, name+"-original.jar"));
                        Files.move(Util.getPath(newFile,profileFile.getFileName().toString()+".json"), Util.getPath(newFile, name+".json"));
                        BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(Util.getPath(newFile, name+".json")), StandardCharsets.UTF_8));

                        String line;
                        String text = "";
                        while((line = br.readLine())!=null) {
                            text+=line+"\n";
                        }
                        text = text.substring(0, text.length()-1);

                        text = text.replace("\"id\": \""+profileFile.getFileName().toString()+"\"","\"id\": \""+name+"\"");

                        br.close();

                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Util.getPath(newFile, name+".json")),"UTF-8"));
                        bw.write(text);
                        bw.close();

                        MCAddToJar.this.update();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            };
        });
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2) {
                    Path profileFile = Util.getPath("profiles",list.getSelectedValue()+".profile");
                    try {
                        Files.createDirectories(profileFile.getParent());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    profile = Profile.load(profileFile);
                    if(profile==null) {
                        new WhatVersion(MCAddToJar.this, profileFile) {
                            @Override
                            public void ok(String version) {
                                profile = new Profile(new HashMap<>(), new ArrayList<>(), profileFile, version);
                                MCAddToJar.this.dispose();
                                this.dispose();
                                addToJar = new AddToJar(Util.getPath(mcDir, "versions/"+list.getSelectedValue()), list.getSelectedValue(), profile, true);
                            }
                        }.setVisible(true);
                    }else {
                        MCAddToJar.this.dispose();
                        addToJar = new AddToJar(Util.getPath(mcDir, "versions/"+list.getSelectedValue()), list.getSelectedValue(), profile, true);
                    }
                }
            }
        });
        controls.add(copy);
        controls.add(rename);
        this.update();
        panel.add(controls, BorderLayout.NORTH);
        panel.add(pane, BorderLayout.CENTER);
        this.add(panel, BorderLayout.CENTER);
        this.setTitle("MCAddToJar - "+version);
        this.setVisible(true);
    }

    private void update() {
        model.removeAllElements();
        try {
            Files.createDirectories(Util.getPath("profiles"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        java.util.List<Path> profiles = null;
        try {
            profiles = Files.list(Util.getPath("profiles")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] versions = new String[]{
                "1.0",
                "1.1",
                "1.2.1","1.2.2","1.2.3","1.2.4","1.2.5",
                "1.3.1","1.3.2",
                "1.4.2","1.4.4","1.4.5","1.4.6","1.4.7",
                "1.5.1","1.5.2"};
        Pattern pattern = Pattern.compile("([0-9]*\\.?)*");
        try {
            for(Path f : Files.list(Util.getPath(mcDir, "versions")).collect(Collectors.toList())) {
                if(Files.isDirectory(f)) {
                    String mcVersion = "";
                    Matcher matcher = pattern.matcher(f.getFileName().toString());
                    while(matcher.find()) {
                        for (String version : versions) {
                            if (matcher.group(0).equals(version)) {
                                mcVersion = version;
                            }
                        }
                    }
                    if(Files.exists(Util.getPath("profiles",f.getFileName().toString()+".profile"))) {
                        profiles.remove(Util.getPath("profiles",f.getFileName().toString()+".profile"));
                        try {
                            mcVersion = Objects.requireNonNull(Profile.load(Util.getPath("profiles", f.getFileName().toString() + ".profile"))).version;
                        }catch (NullPointerException ignore) {}
                    }
                    if(     mcVersion.isEmpty() && !Boolean.parseBoolean(Config.get("showothers", "true")) ||
                            mcVersion.startsWith("1.0") && !Boolean.parseBoolean(Config.get("show1.0", "true")) ||
                            mcVersion.startsWith("1.1") && !Boolean.parseBoolean(Config.get("show1.1", "true")) ||
                            mcVersion.startsWith("1.2") && !Boolean.parseBoolean(Config.get("show1.2.x", "true")) ||
                            mcVersion.startsWith("1.3") && !Boolean.parseBoolean(Config.get("show1.3.x", "true")) ||
                            mcVersion.startsWith("1.4") && !Boolean.parseBoolean(Config.get("show1.4.x", "true")) ||
                            mcVersion.startsWith("1.5") && !Boolean.parseBoolean(Config.get("show1.5.x", "true"))) {
                        continue;
                    }
                    if(Files.exists(Util.getPath("profiles",f.getFileName().toString()+".profile"))) {
                        model.add(0, f.getFileName().toString()+"(Modded)");
                    }else {
                        model.addElement(f.getFileName().toString());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        java.util.List<Path> finalProfiles = profiles;
        new Thread() {
            @Override
            public void run() {
                super.run();
                for(Path f : finalProfiles) {
                    new CheckProfile(MCAddToJar.this, f) {
                        @Override
                        public void delete() {
                            try {
                                Files.delete(f);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            this.dispose();
                            resuume();
                        }
                        @Override
                        public void rename() {
                            this.dispose();
                            new RenameProfile(MCAddToJar.this) {
                                @Override
                                public void ok(String name) {
                                    try {
                                        Files.move(f, Util.getPath("profiles/",name+".profile"));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    MCAddToJar.this.update();
                                    this.dispose();
                                    resuume();
                                }
                            };
                        }
                    };
                    stoop();
                }
            }

            private synchronized void stoop() {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            private synchronized void resuume() {
                this.notify();
            }
        }.start();
    }
}
