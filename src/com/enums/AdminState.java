package com.enums;

public enum AdminState {
    NORMAL(1,"正常"),
    DELETE(0,"删除"),
    LOCK(2,"锁定");

    private Integer value;
    private String msg;

    AdminState(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
