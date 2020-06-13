package io.github.kusaanko.modmanager;

import io.github.kusaanko.MCAddToJar;
import io.github.kusaanko.Profile;
import io.github.kusaanko.modmanager.mod125.Mod125;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import static io.github.kusaanko.Language.translate;

public class ModManagerPanel extends JPanel {
    private final JPanel seriousPane;
    private final JPanel needUpdatingPane;
    private final JPanel installedPane;
    private final JPanel notinstalledPane;

    private final JTabbedPane tabbedPane;

    private final HashMap<Class<? extends Mod>, JButton> classToButton;

    private final ArrayList<Mod> mods;

    private final JDialog parentDialog;
    private final File gameDir;
    private final Profile profile;

    private boolean processing;

    public ModManagerPanel(JDialog parentDialog, File gameDir, Profile profile) {
        super(new BorderLayout());

        this.classToButton = new HashMap<>();
        this.mods = new ArrayList<>();
        this.parentDialog = parentDialog;
        this.gameDir = gameDir;
        this.profile = profile;

        this.seriousPane = new JPanel(new GridLayout(0, 1));
        JScrollPane scrollPane = new JScrollPane(this.seriousPane);

        this.needUpdatingPane = new JPanel(new GridLayout(0, 1));
        JScrollPane scrollPane2 = new JScrollPane(this.needUpdatingPane);

        this.installedPane = new JPanel(new GridLayout(0, 1));
        JScrollPane scrollPane3 = new JScrollPane(this.installedPane);

        this.notinstalledPane = new JPanel(new GridLayout(0, 1));
        JScrollPane scrollPane4 = new JScrollPane(this.notinstalledPane);

        this.tabbedPane = new JTabbedPane();
        this.add(this.tabbedPane);
        this.tabbedPane.addTab(translate("serious"), scrollPane);
        this.tabbedPane.addTab(translate("needupdating"), scrollPane2);
        this.tabbedPane.addTab(translate("installed"), scrollPane3);
        this.tabbedPane.addTab(translate("notinstalled"), scrollPane4);

        scrollPane.getVerticalScrollBar().setUnitIncrement(25);
        scrollPane2.getVerticalScrollBar().setUnitIncrement(25);
        scrollPane3.getVerticalScrollBar().setUnitIncrement(25);
        scrollPane4.getVerticalScrollBar().setUnitIncrement(25);
    }

    public void addMod(Mod mod) {
        this.mods.add(mod);
        updatePanes();
    }

    public void updatePanes() {
        updatePane("serious", seriousPane);
        updatePane("needupdating", needUpdatingPane);
        updatePane("installed", installedPane);
        updatePane("notinstalled", notinstalledPane);
        this.tabbedPane.repaint();
    }

    public void updatePane(String mode, JPanel panel) {
        this.sort();
        panel.removeAll();
        JComponent[] components = new JComponent[]{genJLabel(translate("modname")), genJLabel(translate("author")), genJLabel("<html>" + translate("type") + "<br>" + translate("installationtype")),
                genJLabel("<html>" + translate("nowver") + "<br>" + translate("recommendver")),
                genJLabel(translate("action")), genJLabel(translate("requiremods"))};

        this.addLine(panel, components);
        for(Mod mod : mods) {
            boolean serious = false;
            String requireMods = "";
            ArrayList<Class<? extends Mod>> needMods = new ArrayList<>();
            if (mod.getRequireModsInJar() != null) for (Class<? extends Mod> cl : mod.getRequireModsInJar()) {
                boolean no = true;
                for(Mod m : mods) {
                    if (m.getClass() == cl && !m.getFileVersion().isEmpty()) {
                        no = false;
                        break;
                    }
                }
                if(no) {
                    serious = true;
                    requireMods += "<font style=\"color: red\">" + Mod125.mods125.get(cl).getShortName() + "</font>,";
                    needMods.add(cl);
                }else {
                    requireMods += Mod125.mods125.get(cl).getShortName() + ",";
                }
            }
            if (mod.getRequireMods() != null) for (Class<? extends Mod> cl : mod.getRequireMods()) {
                boolean no = true;
                for(Mod m : mods) {
                    if (m.getClass() == cl && !m.getFileVersion().isEmpty()) {
                        no = false;
                        break;
                    }
                }
                if(no) {
                    serious = true;
                    requireMods += "<font style=\"color: red\">" + Mod125.mods125.get(cl).getShortName() + "</font>,";
                    needMods.add(cl);
                }else {
                    requireMods += Mod125.mods125.get(cl).getShortName() + ",";
                }
            }
            if (!requireMods.isEmpty()) {
                requireMods = requireMods.substring(0, requireMods.length() - 1);
            }
            String type = "";
            switch (mod.getType()) {
                case MOD:
                    type = "basemod";
                    break;
                case ADDON:
                    type = "addon";
                    break;
                case REQUIREMENTS:
                    type = "requirements";
                    break;
                case CONFIG:
                    type = "config";
            }
            String ins_type = "";
            switch (mod.getInstallationType()) {
                case IN_JAR:
                    ins_type = "injar";
                    break;
                case MODS_FOLDER:
                    ins_type = "modsfolder";
                    break;
                case OTHER_FOLDER:
                    ins_type = "otherfolder";
                    break;
            }

            JPanel buttonsPane = new JPanel(new GridLayout(0, 1));

            boolean notInstalled = false;
            boolean installed = false;
            boolean needUpdating = false;
            if((serious && !mode.equals("serious")) || (mod.getFileVersion().isEmpty() && mode.equals("serious")))
                serious = false;
            if(!mod.getFileVersion().isEmpty() && mod.compareVersion() && mode.equals("needupdating"))
                needUpdating = true;
            if(!mod.getFileVersion().isEmpty() && !mod.compareVersion() && mode.equals("installed"))
                installed = true;
            if(mod.getFileVersion().isEmpty() && mode.equals("notinstalled"))
                notInstalled = true;

            components = new JComponent[]{genJLabel(mod.getName()), genJLabel(mod.getAuthor()),
                    genJLabel("<html>" + translate(type) + "<br>" + translate(ins_type)),
                    genJLabel("<html>" + (mod.getFileVersion().isEmpty()?"N/A":mod.getFileVersion()) + "<br>" + mod.getVersion()),
                    buttonsPane, genJLabel("<html>" + (notInstalled?requireMods.replace("style=\"color: red\"", ""):requireMods))};

            JButton delete = new JButton(translate("delete"));
            delete.addActionListener(e -> {
                if(JOptionPane.showConfirmDialog(this, translate("doyoureallywanttodelete"), translate("confirm"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    delete.setEnabled(false);
                    DeletingDialog dialog = new DeletingDialog(parentDialog);
                    if(mod.getInstallationType() != Mod.INSTALLATION_TYPE.IN_JAR) {
                        new File(mod.getFilePath()).delete();
                    }else {
                        profile.remove(mod.getFilePath());
                        MCAddToJar.addToJar.update();
                    }
                    mods.remove(mod);
                    mod.setFilePath("");
                    mod.setFileVersion("");
                    mod.setFileName("");
                    mods.add(mod);
                    dialog.dispose();
                    updatePanes();
                }
            });
            JButton solve = new JButton(translate("solveaproblem"));
            solve.addActionListener(e -> {
                new Thread(() -> {
                    for(Class<? extends Mod> cl : needMods) {
                        while (processing) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            }
                        }
                        processing = true;
                        classToButton.get(cl).doClick();
                    }
                }).start();
            });
            JButton download = new JButton(translate("download"));
            download.addActionListener(e -> {
                download.setEnabled(false);
                File folder = new File(gameDir, "mods");
                if(mod.getInstallationType() == Mod.INSTALLATION_TYPE.IN_JAR) {
                    folder = new File("mods/1.2.5/");
                }
                DownloadingDialog dialog = new DownloadingDialog(parentDialog);
                dialog.registerEvent(outputFile -> {
                    if(outputFile.exists()) {
                        if (mod.getInstallationType() == Mod.INSTALLATION_TYPE.IN_JAR) {
                            profile.add(outputFile.getAbsolutePath());
                            MCAddToJar.addToJar.update();
                        }
                        mods.remove(mod);
                        Mod m = Mod125.is125(outputFile.getName().substring(0, outputFile.getName().lastIndexOf(".")));
                        if (m != null) {
                            m.setFilePath(outputFile.getAbsolutePath());
                            mods.add(m);
                        }
                        dialog.dispose();
                    }
                    updatePanes();
                    solve.doClick();
                    processing = false;
                });
                dialog.run(mod.getDownloadURL(), folder, profile, mod);
            });
            classToButton.put(mod.getClass(), download);
            JButton redownload = new JButton(translate("redownload"));
            redownload.addActionListener(e -> {
                new File(mod.getFilePath()).delete();
                classToButton.get(mod.getClass()).doClick();
            });
            if(notInstalled) buttonsPane.add(download);
            if(serious) buttonsPane.add(solve);
            if(installed) buttonsPane.add(redownload);
            if(needUpdating) {
                redownload.setText(translate("update"));
                buttonsPane.add(redownload);
            }
            if(!notInstalled) buttonsPane.add(delete);

            if(serious) {
                this.addLine(panel, components);
            }else if(notInstalled) {
                this.addLine(panel, components);
            }else if(installed) {
                this.addLine(panel, components);
            }else if(needUpdating) {
                this.addLine(panel, components);
            }
        }
    }

    private void sort() {
        this.mods.sort(Comparator.comparing(Mod::getName).thenComparing(Mod::getAuthor));
    }

    private JLabel genJLabel(String text) {
        JLabel label = new JLabel(text);
        label.setPreferredSize(new Dimension(label.getWidth() + 10, 50));
        return label;
    }

    private void addLine(JPanel parent, JComponent... c) {
        JPanel pane = new JPanel(new GridLayout(1, 0));
        for(JComponent com : c) {
            pane.add(com);
        }
        parent.add(pane);
    }
}
