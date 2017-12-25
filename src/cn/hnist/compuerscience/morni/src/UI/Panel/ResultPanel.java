package UI.Panel;
/**
 * @creatdate 2017.12.23
 */

import UI.Main;
import resource.ImageSource;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ResultPanel {
    private JPanel panel;
    private JList searchResult;
    private JButton reTurn;
    public static boolean flag = false;

    public JPanel init() {
        reTurn.addActionListener((ActionEvent event) -> {

            Main.main.buttonHome.setIcon(ImageSource.ICON_HOME_EN);
            Main.main.buttonList.setIcon(ImageSource.ICON_LIST_UN);
            Main.main.buttonLike.setIcon(ImageSource.ICON_LIKE_UN);
            Main.main.buttonUser.setIcon(ImageSource.ICON_USER_UN);
            Main.main.containPanel.removeAll();
            Main.main.containPanel.add(Main.main.homePanel);
            Main.main.containPanel.updateUI();
            flag =false;

        });
        return panel;
    }
}
