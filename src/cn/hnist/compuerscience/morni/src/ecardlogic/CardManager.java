package ecardlogic;

import java.util.ArrayList;

/**
 * @author Morni
 * @creat class Card
 * @date 2017.12.12
 * @update 2017.12.13 撰写方法基本模块
 */
//CardManager是一个单例模式的类
public class CardManager {

    private static final CardManager manager = new CardManager();
    private ArrayList<Card> cardArrayList = new ArrayList<Card>();

    private CardManager() {
    }

    //返回值常量
    public static final int OK = 14162400;
    public static final int NAME_SAME_UPDATE = 14162401;
    public static final int NAME_SAME_ADDNEW = 14162402;
    public static final int ALL_SAME = 14162403;


    //单例模式get对象
    public static CardManager getManager() {
        return manager;
    }

    //获取名片的ArrayList
    public ArrayList<Card> getcardArrayList() {
        return cardArrayList;
    }

    //查询功能
    public int queryCard(String info) {
        /**
         *注意检索条件info，应逐个检索
         */
        return 0;
    }

    //添加功能
    public int addCard(Card card) {
        /**
         *考虑情况（补充请注明日期）：
         *1.若名字重复，其他数据有不同，则询问是否更新Array
         *@是             （返回NAME_SAME_UPDATE删除之前的信息，并添加这次的信息）
         *@取消           （什么都不做）
         *@添加到新的名片 （返回NAME_SAME_ADDNEW添加进去）。
         *2.若完全为一模一样的则不添加（要有提示）。
         */
        return OK;
    }

    //删除功能
    public int deleteCard(Card card) {
        /**
         *考虑情况：
         *1.不存在的名片
         *@警告提示
         *2.有重复姓名
         *@提示 标明一些基本信息
         *@选择删除（checkbox） 动态创建checkbox 返回选择对象并删除
         */
        return OK;
    }

    //修改功能
    public int fixCard() {
        /**
         *
         */
        return 0;
    }


}