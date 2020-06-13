package io.github.kusaanko.crashlog;

import javax.swing.*;

import java.awt.*;

import static io.github.kusaanko.Language.translate;

public class CrashLogAnalyzer extends JDialog {
    public CrashLogAnalyzer(JFrame parent, String version) {
        super(parent);
        JPanel analyzePane = new JPanel(new BorderLayout());

        JLabel pasteLabel = new JLabel(translate("pastecrashlog"));
        analyzePane.add(pasteLabel, BorderLayout.NORTH);

        JTextArea logArea = new JTextArea();
        JScrollPane logScrollPane = new JScrollPane(logArea);
        analyzePane.add(logScrollPane, BorderLayout.CENTER);

        JButton analyze = new JButton(translate("analyze"));
        analyzePane.add(analyze, BorderLayout.SOUTH);

        analyze.addActionListener(e -> {
            if(version.equals("1.2.5")) {
                new CrashLogAnalyzer125(parent, logArea.getText());
                this.dispose();
            }else {
                JOptionPane.showMessageDialog(this, "Not available in this version.");
                this.dispose();
            }
        });

        this.add(analyzePane);

        this.setTitle(translate("crashloganalyzer") + " - " + version);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
