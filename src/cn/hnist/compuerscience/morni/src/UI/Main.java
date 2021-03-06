package UI;
/**
 * @update 2017.12.16 基本UI框架
 * @update 2017.12.20 四大panel界面
 * @update 2017.12.21 鼠标拖动事件 搜索按钮监听
 * @UPDATA 2017.12.24 完善搜索按钮监听,添加增加按钮
 */
import UI.Dialog.CardDialog;
import UI.Panel.*;
import ecardlogic.CardManager;
import resource.ImageSource;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Main {

    //添加名片
     public RegisterPanel registerPanel = new RegisterPanel();
    //搜索结果
    public ResultPanel resultPanel = new ResultPanel();
    //鼠标坐标
    private int xOld = 0, yOld = 0;
    //四个Panel管理器
    public HomePanel home = new HomePanel();
    public AllPanel all = new AllPanel();
    public UserPanel user = new UserPanel();
    public LikePanel like =new LikePanel();
    //四个Panel
    public JPanel homePanel = home.getPanel();
    public JPanel allPanel = all.getPanel();
    public JPanel userPanel = user.panel;
    public JPanel likePanel = like.getPanel();
    //加载 CardManager类
    public static CardManager cardManager = CardManager.getManager();
    //控件
    private JPanel mainpanel;
    private JButton buttonWindowsMin;
    private JButton buttonWindewsClose;
    private JComboBox keyChoice;
    private JButton icon;
    private JButton iconName;
    private JButton buttonSearch;
    private JPanel toppanelL;
    private JPanel toppanelR;
    private JTextField searchField;
    private JPanel bottomPanel;
    private JPanel leftPanel;
    //左边列表按钮
    public JButton buttonHome;
    public JButton buttonList;
    public JButton buttonLike;
    public JButton buttonUser;
    private JPanel mainContainPanel;
    public JPanel topPanel;
    public JPanel containPanel;
    private JButton buttonAdd;
    //静态类
    public static Main main = new Main();
    public static JFrame frame = new JFrame("Main");

    //入口方法
    public static void main(String[] args) throws IOException {
        //防止线程问题
        EventQueue.invokeLater(() -> {
            try {
                dataInit();
                uiInit();
                buttonsActonListener();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    //初始化数据，用户信息
    private static void dataInit() throws IOException, ClassNotFoundException {
        cardManager.readCard();
        //初始化ALL面板
        for (int i = 0; i <cardManager.getcardArrayList().size() ; i++) {
            main.all.model.addElement(cardManager.getcardArrayList().get(i).toString());
        }
        //初始化Like面板
        for (int i = 0; i <cardManager.getLikeArrayList().size() ; i++) {
            main.all.model.addElement(cardManager.getLikeArrayList().get(i).toString());
        }
        for (int i = 0; i <cardManager.getcardArrayList().size() ; i++) {
            if(cardManager.getcardArrayList().get(i).openTimes>0)
                main.home.model.addElement(cardManager.getcardArrayList().get(i).toString());
        }

    }

    private static void uiInit() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        frame.setContentPane(main.mainpanel);
        //设定启动时在屏幕的位置
        frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 250, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 250);
        //设置windows客户区
        frame.setIconImage(frame.getToolkit().getImage("/cardicon5.png"));
        frame.setUndecorated(true);
        //设置窗口不可变大小
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        main.searchField.setBorder(null);
        main.buttonHome.setIcon(ImageSource.ICON_HOME_EN);
        main.containPanel.removeAll();
        main.containPanel.add(main.homePanel);
        main.containPanel.updateUI();
        frame.setVisible(true);
       // addDialog.startDialog();

    }

    //按钮监听初始化
    private static void buttonsActonListener() {
        //最小化关闭安按钮监听
        main.buttonWindowsMin.addActionListener(event ->
                frame.setExtendedState(WindowConstants.HIDE_ON_CLOSE));
        main.buttonWindewsClose.addActionListener(event ->
                System.exit(0));
        //----------------------------------------------------------

        //四个左列表按钮，设置只有一个能为按下状态，以及容器内容panel的改变
        main.buttonHome.addActionListener(event -> {
            //System.out.println(main.buttonHome.getIcon()!=ImageSource.ICON_HOME_EN);
            //主页选中
            if (main.buttonHome.getIcon() != ImageSource.ICON_HOME_EN) {//判断是否用重复加载Panel

                main.buttonHome.setIcon(ImageSource.ICON_HOME_EN);
                main.buttonList.setIcon(ImageSource.ICON_LIST_UN);
                main.buttonLike.setIcon(ImageSource.ICON_LIKE_UN);
                main.buttonUser.setIcon(ImageSource.ICON_USER_UN);
                if (!RegisterPanel.flag) {
                    main.containPanel.removeAll();
                    main.containPanel.add(main.homePanel);
                    main.containPanel.updateUI();
                } else {
                    main.containPanel.removeAll();
                    main.containPanel.add(main.registerPanel.getPanel());
                    main.containPanel.updateUI();
                }
                if (!ResultPanel.flag) {
                    main.containPanel.removeAll();
                    main.containPanel.add(main.homePanel);
                    main.containPanel.updateUI();
                } else {
                    /**
                     * 此处为了使界面更加人性化，搜索按钮和添加都放在了主页上
                     */
                    main.containPanel.removeAll();
                    main.containPanel.add(main.resultPanel.getPanel());
                    main.containPanel.updateUI();
                }
                main.home.model.removeAllElements();
                for (int i = 0; i <cardManager.getcardArrayList().size() ; i++) {
                    if(cardManager.getcardArrayList().get(i).openTimes>0)
                        main.home.model.addElement(cardManager.getcardArrayList().get(i).toString());
                }
            }
        });
        main.buttonList.addActionListener(event -> {
            //列表选中
            if (main.buttonList.getIcon() != ImageSource.ICON_LIST_EN) {

                main.buttonHome.setIcon(ImageSource.ICON_HOME_UN);
                main.buttonList.setIcon(ImageSource.ICON_LIST_EN);
                main.buttonLike.setIcon(ImageSource.ICON_LIKE_UN);
                main.buttonUser.setIcon(ImageSource.ICON_USER_UN);
                //重新初始化
                main.all.model.removeAllElements();
                for (int i = 0; i <cardManager.getcardArrayList().size() ; i++) {

                    main.all.model.addElement(cardManager.getcardArrayList().get(i).toString());
                }
                main.containPanel.removeAll();
                main.containPanel.add(main.allPanel);
                main.containPanel.updateUI();
            }
        });
        main.buttonLike.addActionListener(event -> {
            //收藏选中
            if (main.buttonLike.getIcon() != ImageSource.ICON_LIKE_EN) {
                main.buttonHome.setIcon(ImageSource.ICON_HOME_UN);
                main.buttonList.setIcon(ImageSource.ICON_LIST_UN);
                main.buttonLike.setIcon(ImageSource.ICON_LIKE_EN);
                main.buttonUser.setIcon(ImageSource.ICON_USER_UN);
                main.like.model.removeAllElements();
                for (int i = 0; i <cardManager.getLikeArrayList().size() ; i++) {

                    main.like.model.addElement(cardManager.getLikeArrayList().get(i).toString());
                }
                main.containPanel.removeAll();
                main.containPanel.add(main.likePanel);
                main.containPanel.updateUI();
            }
        });
        main.buttonUser.addActionListener(event -> {
            //User选中
            if (main.buttonUser.getIcon() != ImageSource.ICON_USER_EN) {
                main.buttonHome.setIcon(ImageSource.ICON_HOME_UN);
                main.buttonList.setIcon(ImageSource.ICON_LIST_UN);
                main.buttonLike.setIcon(ImageSource.ICON_LIKE_UN);
                main.buttonUser.setIcon(ImageSource.ICON_USER_EN);

                main.containPanel.removeAll();
                main.containPanel.add(main.userPanel);
                main.containPanel.updateUI();
            }
        });
        //----------------------------------------------------------


        //搜索输入框 点击时候 默认文字删除 离开时候恢复
        main.searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {

                if (main.searchField.getText().equals(""))
                    main.searchField.setText("请输入搜索内容");
            }
        });
        main.searchField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (main.searchField.getText().equals("请输入搜索内容"))
                    main.searchField.setText("");
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
                main.mainpanel.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (main.searchField.getText().equals(""))
                            main.searchField.setText("请输入搜索内容");
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (main.searchField.getText().equals(""))
                            main.searchField.setText("请输入搜索内容");
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });

            }

        });
        //----------------------------------------------------------


        //topPanel 可以鼠标拖动
        main.topPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //获得鼠标的位置
                main.xOld = e.getX();
                main.yOld = e.getY();
            }
        });
        main.topPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //鼠标移动时
                frame.setLocation(e.getXOnScreen() - main.xOld,
                        e.getYOnScreen() - main.yOld);
            }
        });
        main.iconName.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //获得鼠标的位置
                main.xOld = e.getX();
                main.yOld = e.getY();
            }
        });
        main.iconName.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //鼠标移动时
                frame.setLocation(e.getXOnScreen() - main.xOld,
                        e.getYOnScreen() - main.yOld);
            }
        });
        main.icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //获得鼠标的位置
                main.xOld = e.getX();
                main.yOld = e.getY();
            }
        });
        main.icon.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //鼠标移动时
                frame.setLocation(e.getXOnScreen() - main.xOld,
                        e.getYOnScreen() - main.yOld);
            }
        });
        //-----------------------------------------------------------


        //搜索按钮
        main.buttonSearch.addActionListener((ActionEvent event) -> {

            String key = main.keyChoice.getModel().getSelectedItem().toString();
            if (!(main.searchField.getText().equals("") || main.searchField.getText().equals("请输入搜索内容"))) {
                /**
                 * 此处为了使界面更加人性化，搜索按钮和添加都放在了主页上
                 */
                main.buttonHome.setIcon(ImageSource.ICON_HOME_EN);
                main.buttonList.setIcon(ImageSource.ICON_LIST_UN);
                main.buttonLike.setIcon(ImageSource.ICON_LIKE_UN);
                main.buttonUser.setIcon(ImageSource.ICON_USER_UN);
                main.containPanel.removeAll();
                main.containPanel.add(main.resultPanel.getPanel());
                main.containPanel.updateUI();
                String keyword = main.searchField.getText();
                main.resultPanel.searchResultManager(key, keyword, cardManager.getcardArrayList());
            }
            ResultPanel.flag = true;
            RegisterPanel.flag = false;
            //// TODO: 未完成
        });
        //回车搜索

        //添加按钮
        main.buttonAdd.addActionListener((ActionEvent event) -> {
            main.buttonHome.setIcon(ImageSource.ICON_HOME_EN);
            main.buttonList.setIcon(ImageSource.ICON_LIST_UN);
            main.buttonLike.setIcon(ImageSource.ICON_LIKE_UN);
            main.buttonUser.setIcon(ImageSource.ICON_USER_UN);
            main.containPanel.removeAll();
            main.containPanel.add(main.registerPanel.getPanel());
            main.containPanel.updateUI();
            RegisterPanel.flag = true;
            ResultPanel.flag = false;
        });
    }


}