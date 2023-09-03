package com.dao;

import com.beans.AdminInfo;
import com.beans.RoleInfo;
import com.jdbc.DBUtil;
import com.util.PageInfo;

import java.util.ArrayList;
import java.util.List;

public class RoleDao {
    //获取全部角色
    public List<RoleInfo> getRoleList(PageInfo pageInfo){
        String sql="select * from roleInfo limit ?,?";
        return DBUtil.getList(sql,RoleInfo.class,pageInfo.getBeginRow(),pageInfo.getPageSize());
    }
    //获取全部角色
    public List<RoleInfo> getRoleList(){
        String sql="select * from roleInfo ";
        return DBUtil.getList(sql,RoleInfo.class);
    }
    //根据id获得角色信息
    public RoleInfo getRoleInfoById(int roleId) {
        String sql="select * from roleInfo where id=?";
        return DBUtil.getSingleObj(sql,RoleInfo.class,roleId);
    }
    //根据角色ID得到菜单ID 得到的菜单ID 拼成一个字符串 为了进行数据回显 能看到该角色当前有哪些权限
    public String getMenuIdStr(int roleId) {
        List<Integer> list =DBUtil.getColumnList("select menuId from roleMenu where roleId=?" , roleId);
        StringBuilder buider=new StringBuilder();
        for(int i=0;i<list.size();i++) {
            if(i<list.size()-1) {
                buider.append(list.get(i)+",");
            }
            else {
                buider.append(list.get(i));
            }
        }
        return buider.toString();
    }
    // 根据角色ID来删除该角色的所有权限 再重新添加权限 达到更准确的更新权限的目的
    public void updateRoleMenu(Integer roleId,String[] menuId){
        DBUtil.update("delete from roleMenu where roleId=?",roleId);
        for (String s : menuId) {
            DBUtil.update("insert into roleMenu(roleId,menuId) values(?,?)",roleId,s);
        }
    }
    //添加角色信息 并且回显
    public RoleInfo addRole(RoleInfo roleInfo) {
        String sql="insert into roleInfo(roleName,des) values(?,?)";
        //返回添加的id
        int roleId = DBUtil.addWithId(sql, roleInfo.getRoleName(),roleInfo.getDes());
        //数据回显
        RoleInfo roleInfo1 = DBUtil.getSingleObj("select * from roleInfo where id=?", RoleInfo.class, roleId);
        return roleInfo1;
    }
    //用户角色管理 查询列表
    public AdminInfo getRoleAdmin(Integer adminId){
        String sql="select a.*,r.roleName from adminInfo a join roleInfo r on a.roleId=r.id  where a.id =?";
        return DBUtil.getSingleObj(sql,AdminInfo.class,adminId);
    }
    public RoleInfo isExit(String roleName) {
        return DBUtil.getSingleObj("select * from roleInfo where roleName=?",RoleInfo.class,roleName);
    }
    //根据roleName查找roleId
    public RoleInfo getRoleId(String roleName) {
        return DBUtil.getSingleObj("select * from roleInfo where roleName=?",RoleInfo.class,roleName);
    }
    //修改用户的角色
    public void updateAdminRole(int adminId, int roleId) {
        String sql="update adminInfo set roleId=? where id=?";
        DBUtil.update(sql,roleId,adminId);
    }
    //修改角色名称 角色描述
    public void updateRole(RoleInfo roleInfo){
        DBUtil.update("update roleInfo set roleName=? , des=? where id=?",roleInfo.getRoleName(),roleInfo.getDes(),roleInfo.getId());
    }
    //根据ID删除角色 有用户在用着的角色不能删除
    //查询用户所有里的RoleId 能查到的不能删 查不到的能删
    public int deleteRole(int roleId){
        String sql="SELECT DISTINCT roleId FROM adminInfo ";
        List<AdminInfo> list = DBUtil.getList(sql, AdminInfo.class);
        List<Integer> roleIds=new ArrayList<>();
        for (AdminInfo adminInfo : list) {
                roleIds.add(adminInfo.getRoleId());
        }
        if(!roleIds.contains(roleId)){
            return DBUtil.update("delete from roleInfo where id=?",roleId);
        }
//             DBUtil.update("delete from roleInfo where id=?",roleId);
        
        return 0;
    }

    public int getRowCount() {
        String sql="select count(*) from roleInfo";
        long count=DBUtil.getScalar(sql);
        return Integer.parseInt(count+"");
    }

//    public static void main(String[] args) {
//        RoleDao roleDao=new RoleDao();
//        int i = roleDao.deleteRole(14);
//        System.out.println(i);
//    }

}
