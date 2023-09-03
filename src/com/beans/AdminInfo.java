package com.beans;

import java.util.Date;

public class AdminInfo {
    private int id;  //数据库中的自增ID
    private String adminName;  //用户有账号,要保证在表中账号是唯一的
    private String password;   //用户密码,要保证数据库表中密码必须加密
    private String note;  //备注信息,要注意,有可能是多行
    private String state;   //用户状态  0 删除, 1, 正常 ,2 锁
    private Date editDate;  //用户编辑时间
    private int roleId;   //角色id 它是一个外键, 指向角色表中的角色id
    private String roleName; //角色名称  ,非数据库字段,主要用于关联查询的时候,处理角色名称
    //获取note前25个字
    public String getNoteShow(){
        return note.length()>25?note.substring(0,24)+"...":note;
    }
    //状态转文字
    public String getStateToString(){
        if (state.equals("1")){
            return "正常";
        }else {
            return "已锁定";
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "AdminInfo{" +
                "id=" + id +
                ", adminName='" + adminName + '\'' +
                ", password='" + password + '\'' +
                ", note='" + note + '\'' +
                ", state='" + state + '\'' +
                ", editDate=" + editDate +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
