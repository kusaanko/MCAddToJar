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
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import static io.github.kusaanko.Language.*;

public class MCAddToJar extends JFrame {
    public static File mcDir;
    public static final String version = "1.1.12";
    public static final String repo = "https://github.com/kusaanko/MCAddToJar/releases";
    public static MCAddToJar frame;

    public static void main(String[] args) {
        Config.load();
        Language.init();
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e) {
            e.printStackTrace();
        }
        if(args.length>0) {
            mcDir = new File(args[0]);
            if(!mcDir.exists()) {
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
                mcDir = new File(dir);
                if(!mcDir.exists()) {
                    mcDir = null;
                    JOptionPane.showMessageDialog(null, dir + translate("isnotexist"));
                }
            }
        }
        if(mcDir==null) {
            mcDir = Util.getWorkingDirectory("minecraft");
            if (!mcDir.exists()) {
                JOptionPane.showMessageDialog(null, translate("dotmcdoesnotexist") + ": " + mcDir);
                String data;
                if((data = JOptionPane.showInputDialog(null, translate("enterdirectorypath")+"("+translate("relativepathallowed")+")"))!=null) {
                    Config.put("minecraftDir", data);
                    mcDir = new File(data);
                    if (!mcDir.exists()) {
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
            JMenuItem see = new JMenuItem(translate("seeupdatehistory"));
            see.addActionListener(ev -> {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/kusaanko/MCAddToJar/releases"));
                } catch (URISyntaxException | IOException e) {
                    e.printStackTrace();
                }
            });
            help.add(see);
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
            JMenu versionstoshow = new JMenu(translate("versionstoshow"));
            String[] versions = {"1.0", "1.1", "1.2.x", "1.3.x", "1.4.x", "1.5.x", "others"};
            for(String version : versions) {
                JCheckBoxMenuItem ver = new JCheckBoxMenuItem(translate(version));
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
                    try {
                        if (Integer.parseInt(version.replace(".", "")) < Integer.parseInt(latest.replace(".", "")))
                            new NewVersionAvailable(this, latest);
                    }catch (NumberFormatException ignore) {}
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        if(!new File(mcDir, "versions/").exists()) {
            if(!new File(mcDir, "bin/minecraft.jar").exists()) {
                JOptionPane.showMessageDialog(null, translate("couldnotfindminecraftjarfile"));
                return;
            }else {
                File profileFile = new File("profiles","minecraft.profile");
                profileFile.getParentFile().mkdirs();
                profile = Profile.load(profileFile);
                if(profile==null) {
                    WhatVersion whatVersion = new WhatVersion(MCAddToJar.this, profileFile) {
                        @Override
                        public void ok(String version) {
                            profile = new Profile(new HashMap<>(), new ArrayList<>(), profileFile, version);
                            MCAddToJar.this.dispose();
                            this.dispose();
                            new AddToJar(new File(mcDir, "bin/"), "minecraft", profile, false);
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
                    new AddToJar(new File(mcDir, "bin/"), "minecraft", profile, false);
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
                if(new File("OldMCPatcher.zip").exists()) {
                    fileSum = Util.sha256sum(new File("OldMCPatcher.zip").toPath());
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
                if(!fileSum.equals(newSum)) {
                    Util.downloadFile("https://github.com/kusaanko/OldMCPatcher/releases/download/"+newVer+"/OldMCPatcher-"+newVer+".zip", "OldMCPatcher.zip");
                    File profileFolder = new File("profiles/");
                    if(!profileFolder.exists()) {
                        if(!profileFolder.mkdirs()) {
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
            File profileFile = new File(mcDir,"versions/"+list.getSelectedValue());
            new CopyVersion(this, profileFile) {
                @Override
                public void ok(String name) {
                    this.dispose();
                    try {
                        File newFile = new File(mcDir, "versions/"+name);
                        newFile.mkdirs();
                        Util.copy(new File(profileFile,profileFile.getName()+".jar"), new File(newFile, name+".jar"));
                        Util.copy(new File(profileFile,profileFile.getName()+".json"), new File(newFile, name+".json"));
                        if(new File(profileFile,profileFile.getName()+"-original.jar").exists()) Util.copy(new File(profileFile,profileFile.getName()+"-original.jar"), new File(newFile, name+"-original.jar"));
                        if(new File("profiles/"+profileFile.getName()+".profile").exists()) Util.copy(new File("profiles/"+profileFile.getName()+".profile"), new File("profiles/"+name+".profile"));
                        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(newFile, name+".json")),"UTF-8"));

                        String line;
                        String text = "";
                        while((line = br.readLine())!=null) {
                            text+=line+"\n";
                        }
                        text = text.substring(0, text.length()-1);

                        text = text.replace("\"id\": \""+profileFile.getName()+"\"","\"id\": \""+name+"\"");

                        br.close();

                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(newFile, name+".json")),"UTF-8"));
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
            File profileFile = new File(mcDir,"versions/"+list.getSelectedValue());
            new RenameVersion(this, profileFile) {
                @Override
                public void ok(String name) {
                    this.dispose();
                    try {
                        File newFile = new File(mcDir, "versions/"+name);
                        profileFile.renameTo(newFile);
                        new File("profiles/"+profileFile.getName()+".profile").renameTo(new File("profiles/"+name+".profile"));
                        new File(newFile,profileFile.getName()+".jar").renameTo(new File(newFile, name+".jar"));
                        if(profile.profile_version==0) new File(newFile,profileFile.getName()+"-original.jar").renameTo(new File(newFile, name+"-original.jar"));
                        new File(newFile,profileFile.getName()+".json").renameTo(new File(newFile, name+".json"));
                        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(newFile, name+".json")),"UTF-8"));

                        String line;
                        String text = "";
                        while((line = br.readLine())!=null) {
                            text+=line+"\n";
                        }
                        text = text.substring(0, text.length()-1);

                        text = text.replace("\"id\": \""+profileFile.getName()+"\"","\"id\": \""+name+"\"");

                        br.close();

                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(newFile, name+".json")),"UTF-8"));
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
                    File profileFile = new File("profiles",list.getSelectedValue()+".profile");
                    profileFile.getParentFile().mkdirs();
                    profile = Profile.load(profileFile);
                    if(profile==null) {
                        new WhatVersion(MCAddToJar.this, profileFile) {
                            @Override
                            public void ok(String version) {
                                profile = new Profile(new HashMap<>(), new ArrayList<>(), profileFile, version);
                                MCAddToJar.this.dispose();
                                this.dispose();
                                new AddToJar(new File(mcDir, "versions/"+list.getSelectedValue()), list.getSelectedValue(), profile, true);
                            }
                        }.setVisible(true);
                    }else {
                        MCAddToJar.this.dispose();
                        new AddToJar(new File(mcDir, "versions/"+list.getSelectedValue()), list.getSelectedValue(), profile, true);
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
        new File("profiles").mkdirs();
        ArrayList<File> profiles = new ArrayList<>(Arrays.asList(Objects.requireNonNull(new File("profiles").listFiles())));
        for(File f : Objects.requireNonNull(new File(mcDir, "versions").listFiles())) {
            if(f.isDirectory()) {
                if(new File("profiles",f.getName()+".profile").exists()) {
                    model.add(0, f.getName()+"(Modded)");
                    profiles.remove(new File("profiles",f.getName()+".profile"));
                }else {
                    model.addElement(f.getName());
                }
            }
        }
        new Thread() {
            @Override
            public void run() {
                super.run();
                for(File f : profiles) {
                    new CheckProfile(MCAddToJar.this, f) {
                        @Override
                        public void delete() {
                            f.delete();
                            this.dispose();
                            resuume();
                        }
                        @Override
                        public void rename() {
                            this.dispose();
                            new RenameProfile(MCAddToJar.this) {
                                @Override
                                public void ok(String name) {
                                    f.renameTo(new File("profiles/",name+".profile"));
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
