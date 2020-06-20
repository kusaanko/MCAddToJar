package io.github.kusaanko;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.zip.ZipFile;

public class AddToJarTurn extends JDialog {
    String fileName;
    ArrayList<String> remove;
    DefaultMutableTreeNode root;

    public AddToJarTurn(JFrame parent, String fileName, ArrayList<String> remove) {
        super(parent);
        root = new DefaultMutableTreeNode(Util.getPath(fileName).getFileName().toString());
        this.setTitle(Util.getPath(fileName).getFileName().toString());
        this.setModal(true);
        HashMap<String, DefaultMutableTreeNode> folders = new HashMap<>();
        ArrayList<String> files = new ArrayList<>();
        try{
            ZipFile mod = new ZipFile(fileName);
            ArrayList<String> entries = new ArrayList<>();
            mod.stream().forEach(o -> entries.add(o.getName()));
            entries.sort(String::compareToIgnoreCase);
            for(String name : entries) {
                String[] a = name.split("/");
                String folname = "";
                for(int i = 0;i < a.length;i++) {
                    if(i==a.length-1) break;
                    folname+=a[i]+"/";
                    if(folders.get(folname)==null) {
                        DefaultMutableTreeNode folder = new DefaultMutableTreeNode(a[i]);
                        if(i==0) {
                            root.add(folder);
                        }else {
                            String b = folname.substring(0, folname.lastIndexOf("/"));
                            folders.get(b.substring(0, b.lastIndexOf("/")+1)).add(folder);
                        }
                        folders.put(folname, folder);
                    }
                }
                if(!name.endsWith("/")) files.add(a[a.length-1]+"?"+folname);
            }
            for(String a : files) {
                String folname = a.substring(a.lastIndexOf("?")+1);
                String file = a.substring(0, a.lastIndexOf("?"));
                DefaultMutableTreeNode item = new DefaultMutableTreeNode(a);
                item.setUserObject(new CheckBoxNode(Objects.toString(item.getUserObject(), ""), !remove.contains(folname+file)));
                if(folname.equals("")) {
                    root.add(item);
                }else {
                    folders.get(folname).add(item);
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        DefaultTreeModel model = new DefaultTreeModel(root);
        JTree tree = new JTree(model){
            @Override public void updateUI() {
                setCellRenderer(null);
                setCellEditor(null);
                super.updateUI();

                setCellRenderer(new CheckBoxNodeRenderer());
                setCellEditor(new CheckBoxNodeEditor());
            }
        };
        tree.setEditable(true);
        JScrollPane pane = new JScrollPane(tree);
        pane.setPreferredSize(new Dimension(500, 300));
        JButton ok = new JButton("OK");
        ok.addActionListener(e -> {
            end(fileName, a(root));
        });
        this.add(ok, BorderLayout.SOUTH);
        this.add(pane, BorderLayout.CENTER);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setVisible(true);
    }

    public void end(String fileName, ArrayList<String> remove) {}

    public ArrayList<String> a(DefaultMutableTreeNode root) {
        ArrayList<String> remove = new ArrayList<>();
        for(int i = 0;i < root.getChildCount();i++) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) root.getChildAt(i);
            if(node.getUserObject() instanceof CheckBoxNode) {
                CheckBoxNode checkBox = (CheckBoxNode) node.getUserObject();
                if(!checkBox.selected) {
                    String folder = checkBox.text.substring(checkBox.text.lastIndexOf("?")+1);
                    remove.add(folder+checkBox.text.substring(0,checkBox.text.lastIndexOf("?")));
                }
            }else {
                ArrayList<String> list = a(node);
                String[] a = list.toArray(new String[0]);
                Collections.addAll(remove, a);
            }
        }
        return remove;
    }
}

class CheckBoxNodeRenderer implements TreeCellRenderer {
    private final JCheckBox checkBox = new JCheckBox();
    private final DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
    @Override public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        if (leaf && value instanceof DefaultMutableTreeNode) {
            checkBox.setEnabled(tree.isEnabled());
            checkBox.setFont(tree.getFont());
            checkBox.setOpaque(false);
            checkBox.setFocusable(false);
            Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
            if (userObject instanceof CheckBoxNode) {
                CheckBoxNode node = (CheckBoxNode) userObject;
                checkBox.setText(node.text.substring(0, node.text.lastIndexOf("?")));
                checkBox.setSelected(node.selected);
            }
            return checkBox;
        }
        return renderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
    }
}

class CheckBoxNodeEditor extends AbstractCellEditor implements TreeCellEditor {
    private final JCheckBox checkBox = new JCheckBox() {
        protected transient ActionListener handler;
        @Override public void updateUI() {
            removeActionListener(handler);
            super.updateUI();
            setOpaque(false);
            setFocusable(false);
            handler = e -> stopCellEditing();
            addActionListener(handler);
        }
    };
    String folder;
    @Override public Component getTreeCellEditorComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row) {
        if (leaf && value instanceof DefaultMutableTreeNode) {
            Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
            if (userObject instanceof CheckBoxNode) {
                CheckBoxNode node = (CheckBoxNode) userObject;
                checkBox.setSelected(node.selected);
                checkBox.setText(node.text.substring(0, node.text.lastIndexOf("?")));
                folder = node.text.substring(node.text.lastIndexOf("?"));
            } else {
                checkBox.setSelected(false);
                checkBox.setText(value.toString());
            }
        }
        return checkBox;
    }
    @Override public Object getCellEditorValue() {
        return new CheckBoxNode(checkBox.getText()+folder, checkBox.isSelected());
    }
    @Override public boolean isCellEditable(EventObject e) {
        if (e instanceof MouseEvent && e.getSource() instanceof JTree) {
            MouseEvent me = (MouseEvent) e;
            JTree tree = (JTree) me.getComponent();
            TreePath path = tree.getPathForLocation(me.getX(), me.getY());
            Object o = path.getLastPathComponent();
            if (o instanceof TreeNode) {
                return ((TreeNode) o).isLeaf();
            }
        }
        return false;
    }
}
class CheckBoxNode {
    public final String text;
    public final boolean selected;
    protected CheckBoxNode(String text, boolean selected) {
        this.text = text;
        this.selected = selected;
    }
    @Override public String toString() {
        return text;
    }
}