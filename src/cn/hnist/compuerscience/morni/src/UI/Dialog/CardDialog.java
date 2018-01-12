package UI.Dialog;

import UI.Main;

import javax.swing.*;
import java.io.IOException;

public class CardDialog extends JDialog {
    int cardIndex;
    private JPanel contentPane;
    private JPanel panel;
    private JButton personalname;
    private JButton phone;
    private JButton address;
    private JButton eMai;
    private JButton tel;
    private JButton web;
    private JButton position;
    private JButton fax;
    private JButton cancel;
    private JTextField faxField;
    private JButton saveButton;
    private JButton companyName;
    private JTextField eMailField;
    private JTextField telField;
    private JTextField webField;
    private JTextField phoneField;
    private JTextField addressField;
    private JTextField positionField;
    private JTextField companyNameField;
    private JTextField personalNameField;
    private JButton fixButton;
    private JButton deButton;
    private JButton coButton;
    private JButton buttonOK;


    public CardDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        cancel.addActionListener(e -> {
            faxField.setEditable(false);
            eMailField.setEditable(false);
            telField.setEditable(false);
            webField.setEditable(false);
            phoneField.setEditable(false);
            addressField.setEditable(false);
            positionField.setEditable(false);
            companyNameField.setEditable(false);
            personalNameField.setEditable(false);
            saveButton.setVisible(false);
            fixButton.setVisible(true);
            fixButton.setVisible(true);
            deButton.setVisible(true);
            coButton.setVisible(true);
            cancel.setVisible(false);
        });
        fixButton.addActionListener(e -> {
            faxField.setEditable(true);
            eMailField.setEditable(true);
            telField.setEditable(true);
            webField.setEditable(true);
            phoneField.setEditable(true);
            addressField.setEditable(true);
            positionField.setEditable(true);
            companyNameField.setEditable(true);
            personalNameField.setEditable(true);
            saveButton.setVisible(true);
            fixButton.setVisible(false);
            deButton.setVisible(false);
            coButton.setVisible(false);
            cancel.setVisible(true);
        });
        deButton.addActionListener(e -> {
            Main.cardManager.deleteCard(cardIndex);
            dispose();
        });
        coButton.addActionListener(e -> {
            try {
                Main.cardManager.coCard(cardIndex);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            dispose();
        });
        saveButton.addActionListener(e -> {
            faxField.setEditable(false);
            eMailField.setEditable(false);
            telField.setEditable(false);
            webField.setEditable(false);
            phoneField.setEditable(false);
            addressField.setEditable(false);
            positionField.setEditable(false);
            companyNameField.setEditable(false);
            personalNameField.setEditable(false);
            saveButton.setVisible(false);
            fixButton.setVisible(true);
            fixButton.setVisible(true);
            deButton.setVisible(true);
            coButton.setVisible(true);
            cancel.setVisible(false);
            Main.cardManager.getcardArrayList().get(cardIndex).setAddress(addressField.getText());
            Main.cardManager.getcardArrayList().get(cardIndex).setPersonalName(personalNameField.getText());
            Main.cardManager.getcardArrayList().get(cardIndex).setCompanyName(companyNameField.getText());
            Main.cardManager.getcardArrayList().get(cardIndex).seteMail(eMailField.getText());
            Main.cardManager.getcardArrayList().get(cardIndex).setPhone(phoneField.getText());
            Main.cardManager.getcardArrayList().get(cardIndex).setFax(faxField.getText());
            Main.cardManager.getcardArrayList().get(cardIndex).setPosition(positionField.getText());
            Main.cardManager.getcardArrayList().get(cardIndex).setTel(telField.getText());
            Main.cardManager.getcardArrayList().get(cardIndex).setWeb(webField.getText());
            try {
                Main.cardManager.saveCard();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Main.main.all.model.removeAllElements();
            for (int i = 0; i < Main.cardManager.getcardArrayList().size(); i++) {

                Main.main.all.model.addElement(Main.cardManager.getcardArrayList().get(i).toString());
            }

        });
        faxField.setEditable(false);
        eMailField.setEditable(false);
        telField.setEditable(false);
        webField.setEditable(false);
        phoneField.setEditable(false);
        addressField.setEditable(false);
        positionField.setEditable(false);
        companyNameField.setEditable(false);
        personalNameField.setEditable(false);

    }

    public static void startDialog(int cardIndex) {
        CardDialog dialog = new CardDialog();
        dialog.cardIndex = cardIndex;
        dialog.eMailField.setText(Main.cardManager.getcardArrayList().get(cardIndex).geteMail());
        dialog.telField.setText(Main.cardManager.getcardArrayList().get(cardIndex).getTel());
        dialog.webField.setText(Main.cardManager.getcardArrayList().get(cardIndex).getWeb());
        dialog.phoneField.setText(Main.cardManager.getcardArrayList().get(cardIndex).getPhone());
        dialog.addressField.setText(Main.cardManager.getcardArrayList().get(cardIndex).getAddress());
        dialog.positionField.setText(Main.cardManager.getcardArrayList().get(cardIndex).getPosition());
        dialog.companyNameField.setText(Main.cardManager.getcardArrayList().get(cardIndex).getCompanyName());
        dialog.personalNameField.setText(Main.cardManager.getcardArrayList().get(cardIndex).getPersonalName());

        dialog.saveButton.setVisible(false);
        dialog.cancel.setVisible(false);
        dialog.pack();
        dialog.setLocation(Main.frame.getLocation().x - 6, Main.frame.getLocation().y + 120);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }
}
