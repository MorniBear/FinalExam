package UI.Panel;
/**
 * @creatdate 2017.12.23
 */

import UI.Dialog.CardDialog;
import UI.Main;
import ecardlogic.Card;
import resource.ImageSource;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ResultPanel {
    private JPanel panel;
    private JList list;
    private JButton reTurn;
    public static boolean flag = false;
    public DefaultListModel<String> model = new DefaultListModel<>();

    public JPanel getPanel() {
        list.setModel(model);
        reTurn.addActionListener((ActionEvent event) -> {

            Main.main.buttonHome.setIcon(ImageSource.ICON_HOME_EN);
            Main.main.buttonList.setIcon(ImageSource.ICON_LIST_UN);
            Main.main.buttonLike.setIcon(ImageSource.ICON_LIKE_UN);
            Main.main.buttonUser.setIcon(ImageSource.ICON_USER_UN);
            Main.main.containPanel.removeAll();
            Main.main.containPanel.add(Main.main.homePanel);
            Main.main.containPanel.updateUI();
            flag = false;
        });
        list.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println("111");
                for (int i = 0; i < Main.cardManager.getcardArrayList().size(); i++) {
                    if (Main.cardManager.getcardArrayList().get(i).toString().equals(list.getSelectedValue())) {
                        CardDialog.startDialog(i);
                        Main.cardManager.getcardArrayList().get(i).openTimes++;
                    }
                }
            }
        });
        return panel;
    }

    public void searchResultManager(String info, String key, ArrayList<Card> cardArrayList) {
        model.removeAllElements();
        if (info.equals("全部")) {
            for (int i = 0; i < cardArrayList.size(); i++) {
                if (cardArrayList.get(i).getPersonalName().contains(key) || cardArrayList.get(i).getPhone().contains(key) || cardArrayList.get(i).getCompanyName().contains(key)) {
                    model.addElement(cardArrayList.get(i).toString());
                }

            }
        }
        if (info.equals("姓名")) {
            for (int i = 0; i < cardArrayList.size(); i++) {
                if (cardArrayList.get(i).getPersonalName().contains(key))
                    model.addElement(cardArrayList.get(i).toString());
            }
        }
        if (info.equals("手机")) {
            for (int i = 0; i < cardArrayList.size(); i++) {
                if (cardArrayList.get(i).getPhone().contains(key))
                    model.addElement(cardArrayList.get(i).toString());
            }
        }
        if (info.equals("公司")) {
            for (int i = 0; i < cardArrayList.size(); i++) {
                if (cardArrayList.get(i).getCompanyName().contains(key))
                    model.addElement(cardArrayList.get(i).toString());
            }
        }
    }
}
