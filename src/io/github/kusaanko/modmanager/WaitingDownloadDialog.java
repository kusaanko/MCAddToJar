package io.github.kusaanko.modmanager;

import javax.swing.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import static io.github.kusaanko.Language.translate;

public class WaitingDownloadDialog extends JDialog {
    private boolean isDownloaded;
    private File downloadedFile;

    public WaitingDownloadDialog(JDialog parent, String modName, String version, String downloadLink) {
        super(parent);

        this.setLayout(new BorderLayout());

        JLabel label = new JLabel("<html><center style=\"font-size: 16;width: 100%\">" + translate("draganddropthedownloadedfile").replace("<", "") + "<br>MOD:" + modName + "<br>Ver:" + version + "<br>Client");
        this.add(label, BorderLayout.CENTER);

        label.setTransferHandler(new FileDragAndDrop(this));

        JButton openLink = new JButton(translate("opendownloadlink"));
        openLink.setFont(new Font("sanserif", Font.PLAIN, 14));
        openLink.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URL(downloadLink).toURI());
            } catch (IOException | URISyntaxException ioException) {
                ioException.printStackTrace();
            }
        });
        this.add(openLink, BorderLayout.SOUTH);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                WaitingDownloadDialog.this.isDownloaded = true;
            }
        });

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setTitle(translate("waitfordownload"));
        this.setVisible(true);
    }

    public void setDownloaded(boolean downloaded) {
        isDownloaded = downloaded;
    }

    public boolean isDownloaded() {
        return isDownloaded;
    }

    public File getDownloadedFile() {
        return downloadedFile;
    }

    void setDownloadedFile(File file) {
        this.downloadedFile = file;
    }

    static class FileDragAndDrop extends TransferHandler {
        private final WaitingDownloadDialog dialog;

        public FileDragAndDrop(WaitingDownloadDialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public boolean canImport(TransferSupport support) {
            if (!support.isDrop()) {
                return false;
            }

            return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
        }

        @Override
        public boolean importData(TransferSupport support) {
            Transferable t = support.getTransferable();
            try {
                java.util.List<File> files = (java.util.List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);
                dialog.setDownloadedFile(files.get(0));
                dialog.setDownloaded(true);
            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }
}