package com.enums;

public enum OrderState {
    ALL("全部","全部"),
    UNPAID("未付款","未付款"),
    PAID("已支付","已支付"),
    SHIPPED("已发货","已发货")
    ;

    private String value;
    private String msg;

    OrderState(String value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getmsg() {
        return msg;
    }

    public void setmsg(String msg) {
        this.msg = msg;
    }
}
