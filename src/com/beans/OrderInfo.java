package com.beans;

import com.util.String_UtilDate_SqlDate;

import java.sql.Date;

public class OrderInfo {
    private Integer id;
    private String orderNo;
    private Integer memberId;
    private String postMethod;//邮递方式
    private Float postage;
    private String payMethod;//支付方式
    private Date orderDate;
    private String address;
    private String orderState;//订单状态
    private Float amount;
    private Date sendDate;
    private Date editDate;

    //非数据库字段
    private String memberNo;
    private String memberAddress;

    @Override
    public String toString() {
        return "OrderInfo{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", memberId=" + memberId +
                ", postMethod='" + postMethod + '\'' +
                ", postage=" + postage +
                ", payMethod='" + payMethod + '\'' +
                ", orderDate=" + orderDate +
                ", address='" + address + '\'' +
                ", orderState='" + orderState + '\'' +
                ", amount=" + amount +
                ", sendDate=" + sendDate +
                ", editDate=" + editDate +
                ", memberNo='" + memberNo + '\'' +
                ", memberAddress='" + memberAddress + '\'' +
                '}';
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getPostMethod() {
        return postMethod;
    }

    public void setPostMethod(String postMethod) {
        this.postMethod = postMethod;
    }

    public Float getPostage() {
        return postage;
    }

    public void setPostage(Float postage) {
        this.postage = postage;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }
}
