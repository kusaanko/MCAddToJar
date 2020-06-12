package io.github.kusaanko.modmanager;

import javax.swing.*;
import java.awt.*;

import static io.github.kusaanko.Language.translate;

public class DeletingDialog extends JDialog {
    public DeletingDialog(Dialog parent) {
        super(parent);
        this.add(new JLabel(translate("deleting")));
        this.setTitle(translate("delete"));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
