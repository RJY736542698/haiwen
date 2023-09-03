package com.enums;

public enum postMethod {
    SM("平邮","平邮"),
    FM("快递","快递")
    ;
    private String value;
    private String msg;

    postMethod(String value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
