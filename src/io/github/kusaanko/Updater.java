package io.github.kusaanko;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
 * Updater
 *
 * Copyright (c) 2019 Kusaanko.
 * Released under the Apache License 2.0 license.
 * see http://www.apache.org/licenses/LICENSE-2.0
 *
 */

public class Updater extends JDialog {
    public static void main(String[] args) {
        if(args.length>=4) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
                e.printStackTrace();
            }
            new Updater(args);
        }else {
            System.err.println("Args: <Download URL> <Type> <Title> <Exec Command>");
        }
    }

    public Updater(String[] args) {
        JProgressBar progressBar = new JProgressBar();
        JLabel status = new JLabel("Download Now...");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(status, BorderLayout.SOUTH);
        panel.add(progressBar, BorderLayout.CENTER);
        new Thread(() -> {
            if(args[1].startsWith("zip:")) {
                try {
                    URL url = new URL(args[0]);
                    status.setText("Connecting to "+url.getHost());
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    if(conn.getResponseCode()==200) {
                        Path zip = Paths.get("download.zip");
                        OutputStream stream = Files.newOutputStream(zip);
                        byte[] buff = new byte[8192];
                        int len;
                        int downloaded = 0;
                        progressBar.setMaximum(conn.getContentLength());
                        status.setText("Downloading to download.zip...");
                        while((len = conn.getInputStream().read(buff, 0, buff.length))!=-1) {
                            stream.write(buff, 0, len);
                            progressBar.setValue(downloaded);
                            downloaded += len;
                        }
                        stream.close();
                        conn.getInputStream().close();
                        status.setText("Extracting download.zip...");
                        Path target = Paths.get("");
                        Files.createDirectories(target);

                        try (ZipInputStream in = new ZipInputStream(Files.newInputStream(zip))) {
                            ZipEntry e;
                            while ((e = in.getNextEntry()) != null) {
                                Path dst = Paths.get(target.toString(), e.getName());
                                if(!dst.getFileName().toString().contains(".")) try {
                                    Files.createDirectories(dst);
                                    continue;
                                }catch (FileAlreadyExistsException ignore) {}
                                stream = Files.newOutputStream(dst);
                                buff = new byte[8192];
                                while((len = in.read(buff, 0, buff.length))!=-1) {
                                    stream.write(buff, 0, len);
                                }
                                stream.close();
                            }
                        }
                        status.setText("Deleting download.zip...");
                        Files.delete(zip);
                    }else {
                        status.setText("Error: HTTP Status "+conn.getResponseCode());
                        status.setForeground(Color.RED);
                        Updater.this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if(args[1].startsWith("replace:")) {
                try {
                    URL url = new URL(args[0]);
                    status.setText("Connecting to "+url.getHost());
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    String name = args[1].substring(8);
                    Path file = Paths.get(name);
                    if(conn.getResponseCode()==200) {
                        OutputStream stream = Files.newOutputStream(file);
                        byte[] buff = new byte[8192];
                        int len;
                        int downloaded = 0;
                        progressBar.setMaximum(conn.getContentLength());
                        status.setText("Downloading to "+file+"...");
                        while((len = conn.getInputStream().read(buff, 0, buff.length))!=-1) {
                            stream.write(buff, 0, len);
                            progressBar.setValue(downloaded);
                            downloaded += len;
                        }
                        stream.close();
                        conn.getInputStream().close();
                    }else {
                        status.setText("Error: HTTP Status "+conn.getResponseCode());
                        status.setForeground(Color.RED);
                        Updater.this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        this.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) {
                                System.exit(-1);
                            }
                        });
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            status.setText("Starting...");
            try {
                Runtime.getRuntime().exec(args[3], null, new File("./"));
                System.exit(0);
            }catch (Exception e) {
                status.setText(e.getClass()+": "+e.getLocalizedMessage());
                status.setForeground(Color.RED);
                Updater.this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                this.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(-1);
                    }
                });
                e.printStackTrace();
            }
        }).start();
        this.add(panel);
        this.setSize(300, 100);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Updater - "+args[2]);
        this.setResizable(false);
        this.setVisible(true);
    }
}
