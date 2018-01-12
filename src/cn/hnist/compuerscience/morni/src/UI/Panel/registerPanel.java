package UI.Panel;

import UI.Dialog.addDialog;
import UI.Main;
import ecardlogic.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class RegisterPanel {
    private JButton personalname;
    private JButton phone;
    private JButton address;
    private JButton eMai;
    private JButton tel;
    private JButton web;
    private JButton position;
    private JButton fax;
    private JButton cancel;
    private JTextField personalNameField;
    private JTextField companyNameField;
    private JTextField positionField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField faxField;
    private JTextField eMailField;
    private JTextField telField;
    private JTextField webField;
    private JButton summit;
    private JButton companyName;
    private JPanel panel;
    private JButton 添加名片Button;
    public static boolean flag = false;
    public JPanel getPanel() {
        cancel.addActionListener((ActionEvent event) -> {

            Main.main.buttonHome.setIcon(resource.ImageSource.ICON_HOME_EN);
            Main.main.buttonList.setIcon(resource.ImageSource.ICON_LIST_UN);
            Main.main.buttonLike.setIcon(resource.ImageSource.ICON_LIKE_UN);
            Main.main.buttonUser.setIcon(resource.ImageSource.ICON_USER_UN);
            Main.main.containPanel.removeAll();
            Main.main.containPanel.add(Main.main.homePanel);
            Main.main.containPanel.updateUI();
            flag = false;
        });
        summit.addActionListener((ActionEvent event) -> {

            if(panel.isShowing()) {
                if (personalNameField.getText().equals("")) {
                    personalNameField.setBackground(new Color(255, 175, 175));
                }
                if (phoneField.getText().equals("")) {
                    phoneField.setBackground(new Color(255, 175, 175));
                } else {
                    if (personalNameField.getText().length()==2)
                    {
                        personalNameField.setText(personalNameField.getText().charAt(0)+"    "+personalNameField.getText().charAt(1));
                    }
                    Main.main.cardManager.addCard(new Card(
                            companyNameField.getText(),
                            addressField.getText(),
                            personalNameField.getText(),
                            positionField.getText(),
                            telField.getText(),
                            phoneField.getText(),
                            faxField.getText(),
                            eMailField.getText(),
                            webField.getText(),
                            0));
                    try {
                        Main.main.cardManager.saveCard();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Main.main.buttonHome.setIcon(resource.ImageSource.ICON_HOME_EN);
                    Main.main.buttonList.setIcon(resource.ImageSource.ICON_LIST_UN);
                    Main.main.buttonLike.setIcon(resource.ImageSource.ICON_LIKE_UN);
                    Main.main.buttonUser.setIcon(resource.ImageSource.ICON_USER_UN);
                    Main.main.containPanel.removeAll();
                    Main.main.containPanel.add(Main.main.homePanel);
                    Main.main.containPanel.updateUI();
                    personalNameField.setText("");
                    personalNameField.setBackground(new Color(255, 255, 255));
                    companyNameField.setText("");
                    positionField.setText("");
                    addressField.setText("");
                    phoneField.setText("");
                    phoneField.setBackground(new Color(255, 255, 255));
                    faxField.setText("");
                    eMailField.setText("");
                    telField.setText("");
                    webField.setText("");
                }
            }
            flag = false;
        });
        return panel;
    }
}
