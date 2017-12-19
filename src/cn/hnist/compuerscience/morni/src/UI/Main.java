package UI;
/**
 *
 */

import ecardlogic.CardManager;
import resource.ImageSource;

import javax.swing.*;

import java.awt.*;
public class Main {
    //加载 CardManager类
    private CardManager cardManager = CardManager.getManager();
    //控件
    private  JPanel mainpanel;
    private  JButton buttonWindowsMin;
    private  JButton buttonWindewsClose;
    private  JComboBox keyChoice;
    private  JButton icon;
    private  JButton iconName;
    private  JButton buttonSearch;
    private  JPanel toppanelL;
    private  JPanel toppanelR;
    private  JTextField searchField;
    private JPanel bottomPanel;
    private JPanel leftPanel;
    //左边列表按钮
    private JButton buttonHome;
    private JButton buttonList;
    private JButton buttonLike;
    private JButton buttonUser;
    private JPanel mainContainPanel;
    private JPanel topPanel;
    private JPanel containPanel;
    //静态类
    private static Main main= new Main();
    //入口方法
    public static void main(String[] args) {


        JFrame frame = new JFrame("Main");
        uiInit(frame);
        buttonsActonListener(frame);

}
    static void uiInit(JFrame frame){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        frame.setContentPane(main.mainpanel);
        //设定启动时在屏幕的位置
        frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-400,Toolkit.getDefaultToolkit().getScreenSize().height/2-250);
        //设置windows客户区
        frame.setUndecorated(true);
        //设置窗口不可变大小
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        main.searchField.setBorder(null);
        main.buttonHome.setIcon(ImageSource.ICON_HOME_EN);
    }
    //按钮监听初始化
    static void  buttonsActonListener(JFrame frame){
        //最小化
        main.buttonWindowsMin.addActionListener(event->frame.setExtendedState(WindowConstants.HIDE_ON_CLOSE));
        //关闭
        main.buttonWindewsClose.addActionListener(event->System.exit(0));

        //四个左列表按钮，设置只有一个能为按下状态
        main.buttonHome.addActionListener(event->{
            main.buttonHome.setIcon(ImageSource.ICON_HOME_EN);
            main.buttonList.setIcon(ImageSource.ICON_LIST_UN);
            main.buttonLike.setIcon(ImageSource.ICON_LIKE_UN);
            main.buttonUser.setIcon(ImageSource.ICON_USER_UN);
        });
        main.buttonList.addActionListener(event->{
            main.buttonHome.setIcon(ImageSource.ICON_HOME_UN);
            main.buttonList.setIcon(ImageSource.ICON_LIST_EN);
            main.buttonLike.setIcon(ImageSource.ICON_LIKE_UN);
            main.buttonUser.setIcon(ImageSource.ICON_USER_UN);
        });
        main.buttonLike.addActionListener(event->{
            main.buttonHome.setIcon(ImageSource.ICON_HOME_UN);
            main.buttonList.setIcon(ImageSource.ICON_LIST_UN);
            main.buttonLike.setIcon(ImageSource.ICON_LIKE_EN);
            main.buttonUser.setIcon(ImageSource.ICON_USER_UN);
        });
        main.buttonUser.addActionListener(event->{
            main.buttonHome.setIcon(ImageSource.ICON_HOME_UN);
            main.buttonList.setIcon(ImageSource.ICON_LIST_UN);
            main.buttonLike.setIcon(ImageSource.ICON_LIKE_UN);
            main.buttonUser.setIcon(ImageSource.ICON_USER_EN);
        });

    }


}
