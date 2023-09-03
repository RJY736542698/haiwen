package com.beans;

import java.util.List;

public class MenuInfo {
    //菜单  父级菜单和子菜单  父级菜单parentID=0 子级菜单perentID=对应的父级ID
    private Integer id; //菜单ID
    private String menuName; //菜单名称
    private String target;  // 弹出位置
    private String url; //地址
    private Integer parentId; //父级ID
    private String icon;//图标
    private List<MenuInfo> subMenuList;


    public List<MenuInfo> getSubMenuList() {
        return subMenuList;
    }

    public void setSubMenuList(List<MenuInfo> subMenuList) {
        this.subMenuList = subMenuList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "MenuInfo{" +
                "id=" + id +
                ", menuName='" + menuName + '\'' +
                ", target='" + target + '\'' +
                ", url='" + url + '\'' +
                ", parentId=" + parentId +
                ", icon='" + icon + '\'' +
                '}';
    }
}
