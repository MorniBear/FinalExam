package UI.Dialog;

import UI.Main;
import ecardlogic.Card;

import javax.swing.*;
import java.awt.event.*;

public class addDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonUpdate;
    private JButton buttonNew;
    private JTextPane textPane;
    private Card carder;
    int old;

    public addDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonUpdate);

        buttonUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        Main.cardManager.getcardArrayList().get(old).setAddress(carder.getAddress());
        Main.cardManager.getcardArrayList().get(old).setPersonalName(carder.getPersonalName());
        Main.cardManager.getcardArrayList().get(old).setCompanyName(carder.getCompanyName());
        Main.cardManager.getcardArrayList().get(old).seteMail(carder.geteMail());
        Main.cardManager.getcardArrayList().get(old).setPhone(carder.getPhone());
        Main.cardManager.getcardArrayList().get(old).setFax(carder.getFax());
        Main.cardManager.getcardArrayList().get(old).setPosition(carder.getPosition());
        Main.cardManager.getcardArrayList().get(old).setTel(carder.getTel());
        Main.cardManager.getcardArrayList().get(old).setWeb(carder.getWeb());
        dispose();
    }

    private void onCancel() {
        Main.main.cardManager.getcardArrayList().add(carder);
        dispose();
    }

    public static void startDialog(String text, Card card, int old) {
        addDialog dialog = new addDialog();
        dialog.pack();
        dialog.setLocation(Main.frame.getLocation().x + 90, Main.frame.getLocation().y + 150);
        dialog.setResizable(false);
        dialog.textPane.setText(text);
        dialog.carder = card;
        dialog.old = old;
        dialog.setVisible(true);
    }
}
