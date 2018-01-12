package ecardlogic;

import UI.Dialog.addDialog;
import UI.Dialog.MessageDialog;
import UI.Main;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Morni
 * @creat class Card
 * @date 2017.12.12
 * @update 2017.12.13 撰写方法基本模块
 * @update 2017.12.18 新增like模块
 */
//CardManager是一个单例模式的类
public class CardManager {

    private static final CardManager manager = new CardManager();
    private ArrayList<Card> cardArrayList = new ArrayList<Card>();

    private ArrayList<Card> likeArrayList = new ArrayList<Card>();
    private ArrayList<Card>[] list = new ArrayList[2];

    private CardManager() {
    }

    //返回值常量info
    private static final int OK = 14162400;


    //  单例模式get对象
    public static CardManager getManager() {
        return manager;
    }

    //获取名片的ArrayList
    public ArrayList<Card> getcardArrayList() {
        return cardArrayList;
    }

    //收藏功能
    public void coCard(int cardIndex) throws IOException {
        boolean flag = true;
        for (int i = 0; i < likeArrayList.size(); i++) {
            if (likeArrayList.get(i).getPersonalName().equals(cardArrayList.get(cardIndex).getPersonalName())
              && likeArrayList.get(i).getPhone().equals(cardArrayList.get(cardIndex).getPhone())) {
                MessageDialog.startDialog("已经收藏");
                flag = false;
            }
        }
        if (flag) {
            likeArrayList.add(cardArrayList.get(cardIndex));
            saveCard();
            for (Card aCardArrayList : likeArrayList) {

                Main.main.like.model.addElement(aCardArrayList.toString());
            }
        }
    }

    //查询功能
    public int queryCard(String key, String info) {
        /**
         *注意检索条件info，应逐个检索
         */
        return OK;
    }

    public ArrayList<Card> getLikeArrayList() {
        return likeArrayList;
    }

    public void setLikeArrayList(ArrayList<Card> likeArrayList) {
        this.likeArrayList = likeArrayList;
    }

    //添加功能
    public void addCard(Card card) {
        /**
         *考虑情况（补充请注明日期）：
         *1.若名字重复，其他数据有不同，则询问是否更新Array
         *@是             （返回NAME_SAME_UPDATE删除之前的信息，并添加这次的信息）
         *@取消           （什么都不做）
         *@添加到新的名片 （返回NAME_SAME_ADDNEW添加进去）。
         *2.若完全为一模一样的则不添加（要有提示）。
         */
        boolean flag = true;
        for (int i = 0; i < cardArrayList.size(); i++) {
            if (cardArrayList.get(i).getPersonalName().equals(card.getPersonalName()) && cardArrayList.get(i).getPhone().equals(card.getPhone())) {
                MessageDialog.startDialog("信息重复！");//启动Dialog
                flag = false;
            } else if (cardArrayList.get(i).getPersonalName().equals(card.getPersonalName())) {
                addDialog.startDialog(cardArrayList.get(i).getPersonalName() + " " + cardArrayList.get(i).getCompanyName() + " 姓名相同，新建/更新 一个联系人", card, i);
                flag = false;
            }
        }
        if (flag)
            cardArrayList.add(card);
    }

    //删除功能
    public int deleteCard(int cardIndex) {

        for (int i = 0; i < likeArrayList.size(); i++) {
            if (likeArrayList.get(i).getPersonalName().equals(cardArrayList.get(cardIndex).getPersonalName())
                    && likeArrayList.get(i).getPhone().equals(cardArrayList.get(cardIndex).getPhone())) {
                likeArrayList.remove(i);
            }
        }
        if(Main.main.resultPanel.getPanel().isShowing()){
            Main.main.resultPanel.model.removeElement(cardArrayList.get(cardIndex).toString());
            Main.main.home.model.removeElement(cardArrayList.get(cardIndex).toString());
        }
        cardArrayList.remove(cardIndex);
        try {
            saveCard();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Main.main.all.model.removeAllElements();
        for (Card aCardArrayList : cardArrayList) {

            Main.main.all.model.addElement(aCardArrayList.toString());
        }


        return OK;
    }

    public void saveCard() throws IOException {
        //序列化保存
        list[0] = cardArrayList;
        list[1] = likeArrayList;
        File file = new File("d:/Card.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(list);
        objectOutputStream.close();
    }

    public void readCard() throws IOException, ClassNotFoundException {
        //反序列化读取
        File file = new File("d:/Card.txt");
        if (file.exists())
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                list = (ArrayList<Card>[]) objectInputStream.readObject();
                cardArrayList = list[0];
                likeArrayList = list[1];
                objectInputStream.close();
            }
        else {
            file.createNewFile();
        }
    }
}