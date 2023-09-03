package com.beans;

import java.util.Date;

public class MemberInfo {
    private Integer id;
    private String memberNo;
    private String memberName;
    private String phone;
    private String email;
    private Date registerDate;
    private String idCard;
    private int loginCounts;
    private Date lastLoginDate;
    private String ip;
    private String memberLevel;
    private String pWdQue;
    private String pedAnswer;
    private String zipCode;
    private String address;
    private String password;

    @Override
    public String toString() {
        return "MemberInfo{" +
                "id=" + id +
                ", memberNo='" + memberNo + '\'' +
                ", memberName='" + memberName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", registerDate=" + registerDate +
                ", idCard='" + idCard + '\'' +
                ", loginCounts=" + loginCounts +
                ", lastLoginDate=" + lastLoginDate +
                ", ip='" + ip + '\'' +
                ", memberLevel='" + memberLevel + '\'' +
                ", pWdQue='" + pWdQue + '\'' +
                ", pedAnswer='" + pedAnswer + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String isCard) {
        this.idCard = isCard;
    }

    public int getLoginCounts() {
        return loginCounts;
    }

    public void setLoginCounts(int loginCounts) {
        this.loginCounts = loginCounts;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getpWdQue() {
        return pWdQue;
    }

    public void setpWdQue(String pWdQue) {
        this.pWdQue = pWdQue;
    }

    public String getPedAnswer() {
        return pedAnswer;
    }

    public void setPedAnswer(String pedAnswer) {
        this.pedAnswer = pedAnswer;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
