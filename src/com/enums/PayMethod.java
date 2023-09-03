package com.enums;

public enum PayMethod {
    WSYH("网上银行转账","网上银行转账"),
    YJ("邮局汇款","邮局汇款")
    ;
    private String value;
    private String msg;

    PayMethod(String value, String msg) {
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
