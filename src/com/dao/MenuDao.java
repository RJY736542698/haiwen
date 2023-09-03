package com.dao;

import com.beans.MenuInfo;
import com.jdbc.DBUtil;

import java.util.List;

public class MenuDao {
    //根据parentId查菜单
    public List<MenuInfo> getMenuList(Integer parentId){
        String sql="select * from menuInfo where parentId=?";
        List<MenuInfo>menuList=DBUtil.getList(sql, MenuInfo.class, parentId);

        for(MenuInfo m:menuList ) {
            if(m.getParentId()==0) {
                m.setSubMenuList(getMenuList(m.getId()));
            }
        }
        return menuList;
    }
    public List<MenuInfo> getMenuList(Integer parentId,Integer roleId){
        String sql="select * from menuInfo where parentId=? and id in (select menuId from roleMenu where roleId=? )";
        List<MenuInfo>menuList=DBUtil.getList(sql, MenuInfo.class, parentId,roleId);

        for(MenuInfo m:menuList ) {
            if(m.getParentId()==0) {
                m.setSubMenuList(getMenuList(m.getId(),roleId));
            }
        }
        return menuList;
    }

    public static void main(String[] args) {
        MenuDao menuDao=new MenuDao();
        List<MenuInfo> menuList = menuDao.getMenuList(0,1);
        for (MenuInfo menuInfo : menuList) {
            System.out.println(menuInfo);
        }
    }
}
