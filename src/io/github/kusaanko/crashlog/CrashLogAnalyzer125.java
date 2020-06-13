package io.github.kusaanko.crashlog;

import javax.swing.*;
import java.awt.*;

import static io.github.kusaanko.Language.translate;

public class CrashLogAnalyzer125 extends JDialog {
    public CrashLogAnalyzer125(JFrame parent, String crashLog) {
        super(parent);

        String excep = "";
        String excepMessage = "N/A";
        String occurrencePoints = "";
        String possibleCause = "errornotregisteredyet";

        crashLog = crashLog.replace("\r", "");

        if(crashLog.contains("BEGIN ERROR REPORT")) {
            try {
                crashLog = crashLog.substring(crashLog.indexOf("BEGIN ERROR REPORT"));
                crashLog = crashLog.substring(crashLog.indexOf("LWJGL"));
                crashLog = crashLog.substring(0, crashLog.indexOf("--- END ERROR REPORT"));
                crashLog = crashLog.substring(crashLog.indexOf("\n\n") + 2);
                crashLog = crashLog.replace("\t", "");
                excep = crashLog.split("\n")[0];
                if(excep.contains(":")) {
                    excepMessage = excep.substring(excep.indexOf(":" + 2));
                    excep = excep.substring(0, excep.indexOf(":"));
                }
                occurrencePoints = crashLog.substring(crashLog.indexOf("\n") + 1);
                if(occurrencePoints.contains("cpw.mods.fml.common.Loader.modInit") || excep.contains("NoClassDefFoundError")) {
                    possibleCause = "therearenotenoughmods";
                }
            }catch (Exception ignore) {}
        }

        JPanel analyzePane = new JPanel(new BorderLayout());
        JPanel exceptionPane = new JPanel(new BorderLayout());
        JPanel exceptionMessagePane = new JPanel(new BorderLayout());
        JPanel occurrencePointsPane = new JPanel(new BorderLayout());

        Font font = new Font("sanserif", Font.PLAIN, 16);

        JLabel exceptionLabel = new JLabel(translate("exception") + ":" + excep);
        exceptionLabel.setFont(font);
        exceptionPane.add(exceptionLabel, BorderLayout.NORTH);

        JLabel exceptionMessageLabel = new JLabel(translate("exceptionmessage") + ":" + excepMessage);
        exceptionMessageLabel.setFont(font);
        exceptionMessagePane.add(exceptionMessageLabel, BorderLayout.SOUTH);
        exceptionMessagePane.add(exceptionPane, BorderLayout.NORTH);

        JLabel occurrencePointsLabel = new JLabel(translate("occurrencepoints"));
        occurrencePointsLabel.setFont(font);
        occurrencePointsPane.add(occurrencePointsLabel, BorderLayout.SOUTH);
        occurrencePointsPane.add(exceptionMessagePane, BorderLayout.NORTH);
        analyzePane.add(occurrencePointsPane, BorderLayout.NORTH);

        JLabel possibleCauseLabel = new JLabel(translate("possiblecause") + ":" + translate(possibleCause));
        possibleCauseLabel.setFont(font);
        analyzePane.add(possibleCauseLabel, BorderLayout.SOUTH);

        JTextArea occurrencePointsArea = new JTextArea();
        occurrencePointsArea.setText(occurrencePoints);
        JScrollPane scrollPane = new JScrollPane(occurrencePointsArea);
        analyzePane.add(scrollPane, BorderLayout.CENTER);

        this.add(analyzePane);

        this.setTitle(translate("crashloganalyzer") + " - 1.2.5");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        if(excep.isEmpty() || occurrencePoints.isEmpty()) {
            JOptionPane.showMessageDialog(this, translate("pleaseenterthefullcrashlog"));
            this.dispose();
        }
    }
}
