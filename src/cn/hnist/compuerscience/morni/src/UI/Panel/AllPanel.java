package UI.Panel;

import UI.Dialog.CardDialog;
import UI.Main;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AllPanel {
    public JPanel panel;
    private JPanel allrpanel;
    private JButton allIcon;
    private JScrollPane allScrollPane;
    public JList<String> list;
    public DefaultListModel<String> model = new DefaultListModel<>();
    public JPanel getPanel() {
        list.setModel(model);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                for (int i = 0; i < Main.cardManager.getcardArrayList().size(); i++) {
                    if (Main.cardManager.getcardArrayList().get(i).toString().equals(list.getSelectedValue())) {
                        Main.cardManager.getcardArrayList().get(i).setOpenTimes( Main.cardManager.getcardArrayList().get(i).openTimes+1);
                        CardDialog.startDialog(i);
                    }
                }
            }
        });
        return panel;
    }
}
