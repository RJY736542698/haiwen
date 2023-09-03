package com.beans;

import java.io.Serializable;

public class RoleInfo implements Serializable {
    private int id;  //角色id,自增,对应着用户表中的roleId
    private String roleName; //角色名称
    private String des;  //角色描述

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "RoleInfo{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
