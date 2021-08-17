package io.github.kusaanko;

import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;
import io.github.kusaanko.crashlog.CrashLogAnalyzer;
import io.github.kusaanko.modmanager.ModManager;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import static io.github.kusaanko.Language.*;

public class AddToJar extends JFrame {
    private DefaultListModel<String> model;
    private Profile profile;
    private JButton output;
    AddToJar(Path versionDir, String versionName, Profile profile, boolean json) {
        this.profile = profile;
        JPanel panel = new JPanel(new BorderLayout());
        output = new JButton(translate("output"));
        output.addActionListener(e -> new Thread(() -> {
            output.setEnabled(false);
            output.setText(translate("duringoutput"));
            new DownloadOriginal(AddToJar.this, profile.version) {
                @Override
                public void end() {

                    output:
                    try {
                        if (json) {
                            Path jsonFile = Util.getPath(versionDir, versionName + ".json");
                            if (!Files.isWritable(jsonFile)) {
                                JOptionPane.showMessageDialog(AddToJar.this, String.format(translate("couldnotoutput"), jsonFile.getFileName().toString()));
                                return;
                            }
                            Gson gson = new GsonBuilder().
                                    registerTypeAdapter(Double.class, (JsonSerializer<Double>) (src, typeOfSrc, context) -> {
                                        if (src % 1 == 0) {
                                            return new JsonPrimitive(src.longValue());
                                        }
                                        return new JsonPrimitive(src);
                                    }).create();
                            LinkedHashMap<Object, Object> jsonMap = gson.fromJson(Files.newBufferedReader(jsonFile), LinkedHashMap.class);
                            jsonMap.remove("downloads");
                            if (jsonMap.containsKey("assetIndex")) {
                                LinkedTreeMap<String, Object> map = Util.toMap(jsonMap.get("assetIndex"));
                                map.put("id", "pre-1.6");
                                map.put("sha1", "4759bad2824e419da9db32861fcdc3a274336532");
                                map.put("size", 73813);
                                map.put("totalSize", 49381897);
                                map.put("url", "https://launchermeta.mojang.com/v1/packages/4759bad2824e419da9db32861fcdc3a274336532/pre-1.6.json");
                            }
                            for (LinkedTreeMap<String, Object> map : (List<LinkedTreeMap<String, Object>>) jsonMap.get("libraries")) {
                                if (map.get("name").toString().startsWith("org.lwjgl.lwjgl:lwjgl:") && !map.get("name").toString().contains("nightly")) {
                                    map.put("name", "org.lwjgl.lwjgl:lwjgl:2.9.1");
                                    LinkedTreeMap<String, Object> map2 = Util.toMap(Util.toMap(map.get("downloads")).get("artifact"));
                                    map2.put("path", "org/lwjgl/lwjgl/lwjgl/2.9.1/lwjgl-2.9.1.jar");
                                    map2.put("sha1", "f58c5aabcef0e41718a564be9f8e412fff8db847");
                                    map2.put("size", 1014790);
                                    map2.put("url", "https://libraries.minecraft.net/org/lwjgl/lwjgl/lwjgl/2.9.1/lwjgl-2.9.1.jar");
                                }
                                if (map.get("name").toString().startsWith("org.lwjgl.lwjgl:lwjgl-platform:") && !map.get("name").toString().contains("nightly")) {
                                    map.put("name", "org.lwjgl.lwjgl:lwjgl-platform:2.9.1");
                                    LinkedTreeMap<String, Object> map2 = Util.toMap(Util.toMap(map.get("downloads")).get("classifiers"));
                                    LinkedTreeMap<String, Object> linux = Util.toMap(map2.get("natives-linux"));
                                    linux.put("path", "org/lwjgl/lwjgl/lwjgl-platform/2.9.1/lwjgl-platform-2.9.1-natives-linux.jar");
                                    linux.put("sha1", "aa9aae879af8eb378e22cfc64db56ec2ca9a44d1");
                                    linux.put("size", 571424);
                                    linux.put("url", "https://libraries.minecraft.net/org/lwjgl/lwjgl/lwjgl-platform/2.9.1/lwjgl-platform-2.9.1-natives-linux.jar");
                                    LinkedTreeMap<String, Object> mac = Util.toMap(map2.get("natives-osx"));
                                    mac.put("path", "org/lwjgl/lwjgl/lwjgl-platform/2.9.1/lwjgl-platform-2.9.1-natives-osx.jar");
                                    mac.put("sha1", "2d12c83fdfbc04ecabf02c7bc8cc54d034f0daac");
                                    mac.put("size", 527196);
                                    mac.put("url", "https://libraries.minecraft.net/org/lwjgl/lwjgl/lwjgl-platform/2.9.1/lwjgl-platform-2.9.1-natives-osx.jar");
                                    LinkedTreeMap<String, Object> windows = Util.toMap(map2.get("natives-windows"));
                                    windows.put("path", "org/lwjgl/lwjgl/lwjgl-platform/2.9.1/lwjgl-platform-2.9.1-natives-windows.jar");
                                    windows.put("sha1", "4c517eca808522457dd95ee8fc1fbcdbb602efbe");
                                    windows.put("size", 527196);
                                    windows.put("url", "https://libraries.minecraft.net/org/lwjgl/lwjgl/lwjgl-platform/2.9.1/lwjgl-platform-2.9.1-natives-windows.jar");
                                }
                            }

                            boolean containOldMC = false;
                            for (String key : profile.mcAddToJar.keySet()) {
                                if (key.endsWith("OldMCPatcher.zip")) {
                                    containOldMC = true;
                                }
                            }
                            if (containOldMC) {
                                jsonMap.put("mainClass", "net.minecraft.client.OldMCPatcher.Main");
                            } else {
                                jsonMap.put("mainClass", "net.minecraft.launchwrapper.Launch");
                            }
                            BufferedWriter bw = Files.newBufferedWriter(jsonFile);
                            bw.write(gson.toJson(jsonMap));
                            bw.close();
                        }
                        if (profile.mcAddToJar.size() == 0) break output;
                        Path mc = Util.getPath(versionDir, versionName + ".jar");
                        if (!Files.isWritable(mc)) {
                            JOptionPane.showMessageDialog(AddToJar.this, String.format(translate("couldnotoutput"), mc.getFileName().toString()));
                            return;
                        }
                        Path mc_original = Util.getPath("originals", profile.version + ".jar");
                        if (!Files.exists(mc_original)) {
                            JOptionPane.showMessageDialog(AddToJar.this, translate("mcvanillawasnotfound"));
                            break output;
                        }
                        ZipOutputStream append = new ZipOutputStream(Files.newOutputStream(mc));
                        ArrayList<String> copy = (ArrayList<String>) profile.mcAddToJarTurn.clone();
                        Collections.reverse(copy);
                        for (String fileName : copy) {
                            if (!Files.exists(Util.getPath(fileName))) {
                                JOptionPane.showMessageDialog(AddToJar.this, fileName + translate("isnotexist"));
                            }
                            try {
                                ZipFile mod = new ZipFile(fileName);
                                Enumeration<? extends ZipEntry> entries = mod.entries();
                                while (entries.hasMoreElements()) {
                                    ZipEntry entry = entries.nextElement();
                                    if (entry.getName().startsWith("META-INF")) {
                                        continue;
                                    }
                                    ArrayList<String> remove = profile.mcAddToJar.get(fileName);
                                    if (remove != null && remove.contains(entry.getName())) continue;
                                    ZipEntry ze = new ZipEntry(entry.getName());
                                    try {
                                        append.putNextEntry(ze);
                                        InputStream stream = mod.getInputStream(entry);
                                        byte[] bytes = new byte[8192];
                                        int len;
                                        while ((len = stream.read(bytes, 0, bytes.length)) > 0) {
                                            append.write(bytes, 0, len);
                                        }
                                        append.closeEntry();
                                    } catch (ZipException ignore) {
                                    }
                                }

                                mod.close();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                throw new Exception(fileName + "で問題が発生しました。\n" + ex.getLocalizedMessage());
                            }
                        }
                        {
                            ZipFile mod = new ZipFile(mc_original.toFile());
                            Enumeration<? extends ZipEntry> entries = mod.entries();
                            while (entries.hasMoreElements()) {
                                ZipEntry entry = entries.nextElement();
                                if (entry.getName().startsWith("META-INF")) {
                                    continue;
                                }

                                ZipEntry ze = new ZipEntry(entry.getName());
                                try {
                                    append.putNextEntry(ze);
                                    InputStream stream = mod.getInputStream(entry);
                                    byte[] bytes = new byte[8192];
                                    int len;
                                    while ((len = stream.read(bytes, 0, bytes.length)) > 0) {
                                        append.write(bytes, 0, len);
                                    }
                                    append.closeEntry();
                                } catch (ZipException ignore) {
                                }
                            }
                        }
                        append.close();
                    } catch (FileNotFoundException e1) {
                        JOptionPane.showMessageDialog(this, e1.getLocalizedMessage());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(this, "エラーが発生しました。\n" + e1.getLocalizedMessage(), "", JOptionPane.ERROR_MESSAGE);
                    }
                    output.setEnabled(true);
                    output.setText(translate("output"));
                }
            };
        }).start());
        JPanel controls = new JPanel(new GridLayout(0, 3));
        JButton up = new JButton("↑");
        JButton down = new JButton("↓");
        JButton edit = new JButton(translate("edit"));
        JButton delete = new JButton(translate("delete"));
        JButton forge = new JButton(translate("downloadforge"));
        JButton oldmc = new JButton("OldMCPatcher");
        JButton backupjar = new JButton(translate("backupjar"));
        JButton backupjson = new JButton(translate("backupjson"));
        JButton modmanager = new JButton(translate("modmanager"));
        JButton crashloganalysis = new JButton(translate("crashloganalysis"));
        JButton autosort = new JButton(translate("autosort"));
        controls.add(up);
        controls.add(down);
        controls.add(edit);
        controls.add(delete);
        controls.add(forge);
        controls.add(oldmc);
        controls.add(backupjar);
        controls.add(backupjson);
        if(profile.version.equals("1.2.5")) {
            controls.add(modmanager);
            controls.add(crashloganalysis);
            controls.add(autosort);
        }
        if(!json) backupjson.setEnabled(false);
        panel.add(controls, BorderLayout.NORTH);
        panel.add(output, BorderLayout.SOUTH);
        model = new DefaultListModel<>();
        JList<String> list = new JList<>(model);
        up.addActionListener(e -> {
            int index = list.getSelectedIndex();
            if(index==-1) return;
            if(index==0) return;
            String name = list.getSelectedValue();
            profile.mcAddToJarTurn.remove(index);
            profile.mcAddToJarTurn.add(index-1, name);
            update();
            list.setSelectedIndex(index-1);
        });
        down.addActionListener(e -> {
            int index = list.getSelectedIndex();
            if(index==-1) return;
            if(index==model.getSize()-1) return;
            String name = list.getSelectedValue();
            profile.mcAddToJarTurn.remove(index);
            profile.mcAddToJarTurn.add(index+1, name);
            update();
            list.setSelectedIndex(index+1);
        });
        edit.addActionListener(e -> {
            int index = list.getSelectedIndex();
            if(index==-1) return;
            Path file = Util.getPath(list.getSelectedValue());
            try {
                new AddToJarTurn(this, file.getFileName().toString(), Files.newInputStream(file), profile.mcAddToJar.get(list.getSelectedValue())) {
                    @Override
                    public void end(String fileName, ArrayList<String> remove) {
                        profile.mcAddToJar.put(fileName, remove);
                        AddToJar.this.update();
                        undo();
                        AddToJar.this.update();
                        this.dispose();
                    }
                };
            }catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getButton()==1&&e.getClickCount()==2) edit.doClick();
            }
        });
        delete.addActionListener(e -> {
            if(list.getSelectedIndex()==-1) return;
            String value = list.getSelectedValue();
            profile.mcAddToJarTurn.remove(value);
            profile.mcAddToJarTurn.remove(value);
            profile.mcAddToJar.remove(value);
            update();
        });
        forge.addActionListener(e -> {
            new DownloadForge(this, profile.version) {
                @Override
                public void end(Path output) {
                    profile.mcAddToJar.put(output.toAbsolutePath().toString(), new ArrayList<>());
                    profile.mcAddToJarTurn.remove(output.toAbsolutePath().toString());
                    profile.mcAddToJarTurn.add(output.toAbsolutePath().toString());
                    this.dispose();
                    if(profile.version.startsWith("1.5")) {
                        new FMLWarning(AddToJar.this);
                    }
                    AddToJar.this.update();
                    undo();
                }
            };
        });
        oldmc.addActionListener(e -> {
            String path = Util.getPath("OldMCPatcher.zip").toAbsolutePath().toString();
            if(Files.exists(Util.getPath("OldMCPatcher.zip"))) {
                profile.mcAddToJar.put(path, new ArrayList<>());
                profile.mcAddToJarTurn.remove(path);
                profile.mcAddToJarTurn.add(path);
                this.update();
            }else {
                JOptionPane.showMessageDialog(this,String.format(translate("oldmcpatcherdownload"), path));
            }
        });
        backupjar.addActionListener(e -> {
            new Thread(() -> {
                backupjar.setEnabled(false);
                backupjar.setText(translate("backingupjar"));
                Calendar calendar = Calendar.getInstance();
                try {
                    Util.compress(Util.getPath(versionDir,versionName+".jar"), Util.getPath(versionDir, versionName+"-"+calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DAY_OF_MONTH)+"-"+calendar.get(Calendar.HOUR_OF_DAY)+"-"+calendar.get(Calendar.MINUTE)+"-"+calendar.get(Calendar.SECOND)+".jar.zip"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                backupjar.setEnabled(true);
                backupjar.setText(translate("backupjar"));
            }).start();
        });
        backupjson.addActionListener(e -> {
            new Thread(() -> {
                backupjson.setEnabled(false);
                backupjson.setText(translate("backingupjson"));
                Calendar calendar = Calendar.getInstance();
                try {
                    Util.compress(Util.getPath(versionDir,versionName+".json"), Util.getPath(versionDir,versionName+"-"+calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.DAY_OF_MONTH)+"-"+calendar.get(Calendar.HOUR_OF_DAY)+"-"+calendar.get(Calendar.MINUTE)+"-"+calendar.get(Calendar.SECOND)+".json.zip"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                backupjson.setEnabled(true);
                backupjson.setText(translate("backupjson"));
            }).start();
        });
        modmanager.addActionListener(e -> {
            new ModManager(this, profile);
        });
        crashloganalysis.addActionListener(e -> {
            new CrashLogAnalyzer(this, profile.version);
        });
        autosort.addActionListener(e -> {
            profile.mcAddToJarTurn.sort((o1, o2) -> {
                if(o1.contains("OldMCPatcher")) {
                    return 1;
                }
                if(o2.contains("OldMCPatcher")) {
                    return -1;
                }
                if(o1.contains("MinecraftAPI")) {
                    return 1;
                }
                if(o2.contains("MinecraftAPI")) {
                    return -1;
                }
                if(o1.contains("ModLoaderMP")) {
                    return -1;
                }
                if(o2.contains("ModLoaderMP")) {
                    return 1;
                }
                if(o1.contains("forge")) {
                    return -1;
                }
                if(o2.contains("forge")) {
                    return 1;
                }
                if(o1.contains("OptiFine") && !o1.contains("Render") && !o1.contains("Localize")) {
                    return -1;
                }
                if(o2.contains("OptiFine") && !o2.contains("Render") && !o2.contains("Localize")) {
                    return 1;
                }
                if(o1.contains("OptiFine") && o1.contains("Render")) {
                    return -1;
                }
                if(o2.contains("OptiFine") && o2.contains("Render")) {
                    return 1;
                }
                if(o1.contains("OptiFine") && o1.contains("Localize")) {
                    return -1;
                }
                if(o2.contains("OptiFine") && o2.contains("Localize")) {
                    return 1;
                }
                if(o1.contains("TooManyItems")) {
                    return -1;
                }
                if(o2.contains("TooManyItems")) {
                    return 1;
                }
                if(o1.contains("SinglePlayer")) {
                    return -1;
                }
                if(o2.contains("SinglePlayer")) {
                    return 1;
                }
                return o1.compareTo(o2);
            });
            update();
        });
        JScrollPane pane = new JScrollPane(list);
        update();
        pane.setPreferredSize(new Dimension(500,300));
        list.setCellRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Path file = Util.getPath(value.toString());
                this.setText(file.getFileName().toString());
                if(isSelected) {
                    this.setBackground(new Color(0, 100, 255));
                }else {
                    this.setBackground(Color.WHITE);
                }
                return this;
            }
        });
        list.setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(TransferSupport support) {
                return support.isDrop();
            }

            @Override
            public boolean importData(TransferSupport support) {
                if (!canImport(support)) return false;
                Transferable t = support.getTransferable();
                try {
                    // ファイルを受け取る
                    List<File> files = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);

                    for (File file : files){
                        if(file.isFile()&&(file.getName().endsWith(".jar")||file.getName().endsWith(".zip"))) {
                            profile.mcAddToJar.put(file.getAbsolutePath(), new ArrayList<>());
                            profile.mcAddToJarTurn.remove(file.getAbsolutePath());
                            profile.mcAddToJarTurn.add(file.getAbsolutePath());
                        }
                    }
                    update();

                } catch (UnsupportedFlavorException | IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        new Thread(() -> {
            if(profile.profile_version==0 && Files.exists(Util.getPath(versionDir, versionName+"-original.jar"))) {
                if(JOptionPane.showConfirmDialog(AddToJar.this, String.format(translate("deleteoriginal"), versionName+"-original.jar"), "Confirm", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                    try {
                        Files.delete(Util.getPath(versionDir.toAbsolutePath(), versionName + "-original.jar"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        panel.add(pane, BorderLayout.CENTER);
        this.setTitle(versionName);
        this.add(panel, BorderLayout.CENTER);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        Runtime.getRuntime().addShutdownHook(new Thread(profile::save));
    }

    public void update() {
        model.removeAllElements();
        for(String name : profile.mcAddToJarTurn) {
            model.addElement(name);
        }
    }

    public void delete(Path path) {
        try {
            Files.delete(path);
        }catch (Exception ignore) {}
    }

    public void undo() {}

    public void output() {
        this.output.doClick();
        outputEnd();
    }

    public void outputEnd() {}
}
