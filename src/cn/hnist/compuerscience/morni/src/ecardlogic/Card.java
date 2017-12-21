package ecardlogic;

import java.awt.*;

/**
 * @author Morni
 * @creat class CardManager
 * @date 2017.12.12
 * @update 2017.12.21 增加打开次数
 */
public class Card {
    //公司名称
    private String companyName;
    //公司地址
    private String address;
    //个人姓名
    private String personalName;
    //职位
    private String position;
    //座机
    private String tel;
    //手机
    private String phone;
    //传真
    private String fax;
    //E-mail
    private String eMail;
    //网页
    private String web;
    //背景颜色
    private Color backgroundColor;
    //打开次数
    private int openTimes;

    //构造方法
    public Card() {
        this.companyName = null;
        this.address = null;
        this.personalName = null;
        this.position = null;
        this.tel = null;
        this.phone = null;
        this.fax = null;
        this.eMail = null;
        this.web = null;
        this.backgroundColor = null;
        this.openTimes = 0;
    }

    public Card(String companyName, String address, String personalName, String position, String tel, String phone, String fax, String eMail, String web, Color backgroundColor, int openTimes) {
        this.companyName = companyName;
        this.address = address;
        this.personalName = personalName;
        this.position = position;
        this.tel = tel;
        this.phone = phone;
        this.fax = fax;
        this.eMail = eMail;
        this.web = web;
        this.backgroundColor = backgroundColor;
        this.openTimes = openTimes;
    }

    //getter and setter
    public int getOpenTimes() {
        return openTimes;
    }

    public void setOpenTimes(int openTimes) {
        this.openTimes = openTimes;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
