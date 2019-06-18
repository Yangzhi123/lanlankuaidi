package com.example.lanlankuaidi;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobGeoPoint;

public class Order extends BmobObject {

    private String name;//帖子标题
    private String number;// 帖子内容
    private User author;//帖子的发布者，这里体现的是一对一的关系，该帖子属于某个用户
    private String money;
    private BmobGeoPoint gpsAdd;
    private String remarks;
    private String origin;
    private String zhongd;
    private String weight;
    private User name1;

    public User getName1() {
        return name1;
    }

    public void setName1(User name1) {
        this.name1 = name1;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getZhongd() {
        return zhongd;
    }

    public void setZhongd(String zhongd) {
        this.zhongd = zhongd;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    private Boolean state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public BmobGeoPoint getGpsAdd() {
        return gpsAdd;
    }

    public void setGpsAdd(BmobGeoPoint gpsAdd) {
        this.gpsAdd = gpsAdd;
    }
}
