package UI.Panel;

import UI.Dialog.CardDialog;
import UI.Main;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class HomePanel{

    public JPanel panel;
    private JPanel homepanel;
    private JButton homeIcon;
    private JButton add;
    private JList list1;
    public DefaultListModel<String> model = new DefaultListModel<>();
    public JPanel getPanel() {
        list1.setModel(model);
        add.addActionListener(event -> {
            Main.main.buttonHome.setIcon(resource.ImageSource.ICON_HOME_EN);
            Main.main.buttonList.setIcon(resource.ImageSource.ICON_LIST_UN);
            Main.main.buttonLike.setIcon(resource.ImageSource.ICON_LIKE_UN);
            Main.main.buttonUser.setIcon(resource.ImageSource.ICON_USER_UN);
            Main.main.containPanel.removeAll();
            Main.main.containPanel.add( Main.main.registerPanel.getPanel());
            Main.main.containPanel.updateUI();
            RegisterPanel.flag = true;
            ResultPanel.flag = false;
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                for (int i = 0; i < Main.cardManager.getcardArrayList().size(); i++) {
                    if (Main.cardManager.getcardArrayList().get(i).toString().equals(list1.getSelectedValue())) {
                        CardDialog.startDialog(i);
                        Main.cardManager.getcardArrayList().get(i).setOpenTimes( Main.cardManager.getcardArrayList().get(i).openTimes+1);
                    }
                }
            }
        });
        return panel;
    }
}
