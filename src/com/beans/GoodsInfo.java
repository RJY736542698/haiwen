package com.beans;

import java.util.Arrays;
import java.util.Date;

public class GoodsInfo {
    private int id;
    private String goodsName; //商品图片
    private String unit; //计量单位
    private float price; //价格
    private String des; //描述
    private String producter; //生产厂商
    private int bigCateId; //大分类id  ,指向分类表
    private int smallCateId; //小分类id, 指向分类表
    private Date editDate; //最后更新时间 数据库中用时间戳的格式,取值为 CURRENT_TIMESTAMP
    private byte[] pictureData;  //图片
    //下面两个类字段,在商品表中不存在,可以关联查询出来
    private String bigCateName;  //大分类名
    private String smallCateName; //小分类名

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getProducter() {
        return producter;
    }

    public void setProducter(String producter) {
        this.producter = producter;
    }

    public int getBigCateId() {
        return bigCateId;
    }

    public void setBigCateId(int bigCateId) {
        this.bigCateId = bigCateId;
    }

    public int getSmallCateId() {
        return smallCateId;
    }

    public void setSmallCateId(int smallCateId) {
        this.smallCateId = smallCateId;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public byte[] getPictureData() {
        return pictureData;
    }

    public void setPictureData(byte[] pictureData) {
        this.pictureData = pictureData;
    }

    public String getBigCateName() {
        return bigCateName;
    }

    public void setBigCateName(String bigCateName) {
        this.bigCateName = bigCateName;
    }

    public String getSmallCateName() {
        return smallCateName;
    }

    public void setSmallCateName(String smallCateName) {
        this.smallCateName = smallCateName;
    }

    @Override
    public String toString() {
        return "GoodsInfo{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", des='" + des + '\'' +
                ", producter='" + producter + '\'' +
                ", bigCateId=" + bigCateId +
                ", smallCateId=" + smallCateId +
                ", editDate=" + editDate +
                ", pictureData=" + Arrays.toString(pictureData) +
                ", bigCateName='" + bigCateName + '\'' +
                ", smallCateName='" + smallCateName + '\'' +
                '}';
    }
}
