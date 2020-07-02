package io.github.kusaanko.modmanager;

import io.github.kusaanko.MCAddToJar;
import io.github.kusaanko.Profile;
import io.github.kusaanko.Util;
import io.github.kusaanko.modmanager.mod125.Mod125;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import static io.github.kusaanko.Language.translate;

public class ModManagerPanel extends JPanel {
    private final JPanel seriousPane;
    private final JPanel needUpdatingPane;
    private final JPanel installedPane;
    private final JPanel notinstalledPane;
    private final JPanel patchPane;

    private final JTabbedPane tabbedPane;

    private final HashMap<Class<? extends Mod>, JButton> classToButton;

    private final ArrayList<Mod> mods;

    private final JDialog parentDialog;
    private final Path gameDir;
    private final Profile profile;

    private boolean processing;
    private final JScrollPane scrollPane;
    private final JScrollPane scrollPane2;
    private final JScrollPane scrollPane3;
    private final JScrollPane scrollPane4;
    private final JScrollPane scrollPane5;

    public ModManagerPanel(JDialog parentDialog, Path gameDir, Profile profile) {
        super(new BorderLayout());

        this.classToButton = new HashMap<>();
        this.mods = new ArrayList<>();
        this.parentDialog = parentDialog;
        this.gameDir = gameDir;
        this.profile = profile;

        this.seriousPane = new JPanel(new GridLayout(0, 1));
        scrollPane = new JScrollPane(this.seriousPane);

        this.needUpdatingPane = new JPanel(new GridLayout(0, 1));
        scrollPane2 = new JScrollPane(this.needUpdatingPane);

        this.installedPane = new JPanel(new GridLayout(0, 1));
        scrollPane3 = new JScrollPane(this.installedPane);

        this.notinstalledPane = new JPanel(new GridLayout(0, 1));
        scrollPane4 = new JScrollPane(this.notinstalledPane);

        this.patchPane = new JPanel(new GridLayout(0, 1));
        scrollPane5 = new JScrollPane(this.patchPane);

        this.tabbedPane = new JTabbedPane();
        this.add(this.tabbedPane);
        this.tabbedPane.addTab(translate("serious"), scrollPane);
        this.tabbedPane.addTab(translate("needupdating"), scrollPane2);
        this.tabbedPane.addTab(translate("installed"), scrollPane3);
        this.tabbedPane.addTab(translate("notinstalled"), scrollPane4);
        this.tabbedPane.addTab(translate("patch"), scrollPane5);

        scrollPane.getVerticalScrollBar().setUnitIncrement(25);
        scrollPane2.getVerticalScrollBar().setUnitIncrement(25);
        scrollPane3.getVerticalScrollBar().setUnitIncrement(25);
        scrollPane4.getVerticalScrollBar().setUnitIncrement(25);
    }

    public void addMod(Mod mod) {
        this.mods.add(mod);
    }

    public synchronized void updatePanes() {
        int scrollPane1Pos = scrollPane.getVerticalScrollBar().getValue();
        int scrollPane2Pos = scrollPane2.getVerticalScrollBar().getValue();
        int scrollPane3Pos = scrollPane3.getVerticalScrollBar().getValue();
        int scrollPane4Pos = scrollPane4.getVerticalScrollBar().getValue();
        int scrollPane5Pos = scrollPane5.getVerticalScrollBar().getValue();
        updatePane("serious", seriousPane);
        updatePane("needupdating", needUpdatingPane);
        updatePane("installed", installedPane);
        updatePane("notinstalled", notinstalledPane);
        updatePane("patch", patchPane);
        this.tabbedPane.repaint();
        scrollPane.validate();
        scrollPane2.validate();
        scrollPane3.validate();
        scrollPane4.validate();
        scrollPane5.validate();
        scrollPane.getVerticalScrollBar().setValue(scrollPane1Pos);
        scrollPane2.getVerticalScrollBar().setValue(scrollPane2Pos);
        scrollPane3.getVerticalScrollBar().setValue(scrollPane3Pos);
        scrollPane4.getVerticalScrollBar().setValue(scrollPane4Pos);
        scrollPane5.getVerticalScrollBar().setValue(scrollPane5Pos);
    }

    public void updatePane(String mode, JPanel panel) {
        this.sort();
        panel.removeAll();
        JComponent[] components = new JComponent[]{genJLabel(translate("modname")), genJLabel(translate("author")), genJLabel("<html>" + translate("type") + "<br>" + translate("installationtype")),
                genJLabel("<html>" + translate("nowver") + "<br>" + translate("recommendver")),
                genJLabel(translate("action")), genJLabel(translate("requiremods"))};

        if(mode.equals("patch")) {
            this.addLine(panel, genJLabel("<html>" + translate("thepatchmayhavealready").replace("<", "")));
        }
        this.addLine(panel, components);
        for(Mod mod : mods) {
            if(mode.equals("patch")) {
                if(mod.getType() != Mod.TYPE.PATCH) {
                    continue;
                }
            }else {
                if(mod.getType() == Mod.TYPE.PATCH) {
                    continue;
                }
            }
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
                    break;
                case PATCH:
                    type = "patch";
                    ins_type = "";
            }

            JPanel buttonsPane = new JPanel(new GridLayout(0, 1));
            buttonsPane.setBorder(new EmptyBorder(2, 2, 2, 2));

            boolean notInstalled = false;
            boolean installed = false;
            boolean needUpdating = false;
            boolean patch = false;
            if((serious && !mode.equals("serious")) || (mod.getFileVersion().isEmpty() && mode.equals("serious")))
                serious = false;
            if(!mod.getFileVersion().isEmpty() && mod.compareVersion() && mode.equals("needupdating"))
                needUpdating = true;
            if(!mod.getFileVersion().isEmpty() && !mod.compareVersion() && mode.equals("installed"))
                installed = true;
            if(mod.getFileVersion().isEmpty() && mode.equals("notinstalled"))
                notInstalled = true;
            if(mode.equals("patch"))
                patch = true;

            components = new JComponent[]{genJLabel("<html>" + mod.getName()), genJLabel("<html>" + mod.getAuthor()),
                    genJLabel("<html>" + translate(type) + "<br>" + translate(ins_type)),
                    genJLabel("<html>" + (mode.equals("patch")?"":mod.getFileVersion().isEmpty()?"N/A":mod.getFileVersion()) + "<br>" + mod.getVersion()),
                    buttonsPane, genJLabel("<html>" + (notInstalled?requireMods.replace("style=\"color: red\"", ""):requireMods))};

            JButton delete = new JButton(translate("delete"));
            delete.addActionListener(e -> {
                if(JOptionPane.showConfirmDialog(this, translate("doyoureallywanttodelete"), translate("confirm"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    delete.setEnabled(false);
                    DeletingDialog dialog = new DeletingDialog(parentDialog);
                    if(mod.getInstallationType() != Mod.INSTALLATION_TYPE.IN_JAR) {
                        try {
                            Files.delete(Util.getPath(mod.getFilePath()));
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
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
                Path folder = Util.getPath(gameDir, mod.getInstallationFolder());
                if(mod.getInstallationType() == Mod.INSTALLATION_TYPE.IN_JAR) {
                    folder = Util.getPath("mods/1.2.5/");
                }
                DownloadingDialog dialog = new DownloadingDialog(parentDialog);
                dialog.registerEvent(outputFile -> {
                    if(mod.getType() != Mod.TYPE.PATCH) {
                        if (Files.exists(outputFile)) {
                            if (mod.getInstallationType() == Mod.INSTALLATION_TYPE.IN_JAR) {
                                profile.add(outputFile.toAbsolutePath().toString());
                                MCAddToJar.addToJar.update();
                            }
                            mods.remove(mod);
                            Mod m = Mod125.is125(outputFile.getFileName().toString().substring(0, outputFile.getFileName().toString().lastIndexOf(".")));
                            if (m != null) {
                                m.setFilePath(outputFile.toAbsolutePath().toString());
                                mods.add(m);
                            }
                            dialog.dispose();
                        }
                    }else {
                        dialog.dispose();
                    }
                    solve.doClick();
                    updatePanes();
                    processing = false;
                });
                boolean conflict = false;
                Mod conflictMod = null;
                if(mod.getConflictMods() != null) for(Class<? extends Mod> mc : mod.getConflictMods()) {
                    for (Mod m : mods) {
                        if (m.getClass() == mc && !m.getFileVersion().isEmpty()) {
                            conflict = true;
                            conflictMod = m;
                            break;
                        }
                    }
                }
                if(conflict) {
                    dialog.dispose();
                    JOptionPane.showMessageDialog(this, String.format(translate("cannotbeinstalledbecauseitconflicts"), conflictMod.getName()));
                    updatePanes();
                    processing = false;
                }else {
                    dialog.run(mod.getDownloadURL(), folder, profile, mod);
                }
            });
            classToButton.put(mod.getClass(), download);
            JButton redownload = new JButton(translate("redownload"));
            redownload.addActionListener(e -> {
                try {
                    Files.delete(Util.getPath(mod.getFilePath()));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                classToButton.get(mod.getClass()).doClick();
            });
            if(notInstalled) buttonsPane.add(download);
            if(serious) buttonsPane.add(solve);
            if(installed) buttonsPane.add(redownload);
            if(needUpdating) {
                redownload.setText(translate("update"));
                buttonsPane.add(redownload);
            }
            if(patch) {
                download.setText(translate("applythispatch"));
                buttonsPane.add(download);
            }
            if(!notInstalled && !patch) buttonsPane.add(delete);
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem modName = new JMenuItem(mod.getName());
            modName.setEnabled(false);
            popupMenu.add(modName);
            JMenuItem opendownload = new JMenuItem(translate("opendownloadpage"));
            popupMenu.add(opendownload);
            opendownload.addActionListener(e -> {
                try {
                    Desktop.getDesktop().browse(new URL(mod.getDownloadPageURL()).toURI());
                } catch (IOException | URISyntaxException ioException) {
                    ioException.printStackTrace();
                }
            });
            JMenuItem opendirectdownload = new JMenuItem(translate("opendirectdownloadpage"));
            popupMenu.add(opendirectdownload);
            opendirectdownload.addActionListener(e -> {
                try {
                    Desktop.getDesktop().browse(new URL(mod.getDownloadURL()).toURI());
                } catch (IOException | URISyntaxException ioException) {
                    ioException.printStackTrace();
                }
            });
            JMenuItem copydownload = new JMenuItem(translate("copydownloadpageurl"));
            popupMenu.add(copydownload);
            copydownload.addActionListener(e -> {
                StringSelection selection = new StringSelection(mod.getDownloadPageURL());
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
            });
            JMenuItem copydirectdownload = new JMenuItem(translate("copydirectdownloadpageurl"));
            popupMenu.add(copydirectdownload);
            copydirectdownload.addActionListener(e -> {
                StringSelection selection = new StringSelection(mod.getDownloadURL());
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
            });
            JMenuItem copymodname = new JMenuItem(translate("copymodname"));
            popupMenu.add(copymodname);
            copymodname.addActionListener(e -> {
                StringSelection selection = new StringSelection(mod.getName());
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
            });
            JMenuItem copyauthor = new JMenuItem(translate("copyauthor"));
            popupMenu.add(copyauthor);
            copyauthor.addActionListener(e -> {
                StringSelection selection = new StringSelection(mod.getAuthor());
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
            });
            JMenuItem copyversion = new JMenuItem(translate("copyversion"));
            popupMenu.add(copyversion);
            copyversion.addActionListener(e -> {
                StringSelection selection = new StringSelection(mod.getVersion());
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
            });

            for(JComponent component : components) {
                if(component instanceof JLabel) {
                    component.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if(SwingUtilities.isRightMouseButton(e)) {
                                popupMenu.show(e.getComponent(), e.getX(), e.getY());
                            }
                        }
                    });
                }
            }

            if(serious) {
                this.addLine(panel, components);
            }else if(notInstalled) {
                this.addLine(panel, components);
            }else if(installed) {
                this.addLine(panel, components);
            }else if(needUpdating) {
                this.addLine(panel, components);
            }else if(patch) {
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
