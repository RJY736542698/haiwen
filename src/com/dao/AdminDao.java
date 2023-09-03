package com.dao;

import com.beans.AdminInfo;
import com.enums.AdminState;
import com.jdbc.DBUtil;
import com.util.PageInfo;

import java.util.List;

public class AdminDao {
    //检查旧密码输入是否正确
    public boolean checkOldPassword(int id, String oldPassword) {
        String sql="select * from adminInfo where id=?";
        AdminInfo admin = DBUtil.getSingleObj(sql, AdminInfo.class, id);
        if (oldPassword.equals(admin.getPassword())){
            return true;
        }else {
            return false;
        }
    }
    //修改密码
    public void updatePassword(int id,String newPassword){
        String sql="update adminInfo set password=? where id=?";
        DBUtil.update(sql,newPassword,id);
    }
    //管理员登陆
    public AdminInfo login(String adminName,String password){
        String sql="select a.*,roleName from adminInfo a join roleInfo r on a.roleId=r.id  where adminName=? and password=? and state!='"+ AdminState.DELETE.getValue()+"'";
        return DBUtil.getSingleObj(sql,AdminInfo.class,adminName,password);
    }
    //添加管理员时判断账号是否存在  根据账号数据回显  根据账号查询要修改的信息
    public AdminInfo isExit(String adminName){
        String sql="select * from adminInfo where adminName=?";
        return DBUtil.getSingleObj(sql,AdminInfo.class,adminName);
    }
    //添加管理员
    public int adminAdd(AdminInfo adminInfo){
        String sql="insert into adminInfo(adminName,password,note) value(?,?,?)";
        return DBUtil.update(sql,adminInfo.getAdminName(),adminInfo.getPassword(),adminInfo.getNote());
    }
    //查询用户总数
    public int getRowCount(){
        long count = DBUtil.getScalar("select count(*) from adminInfo where state!='"+AdminState.DELETE.getValue()+"'");
        return Integer.parseInt(count+"");
    }
    //用户维护 查询列表
    public List<AdminInfo> getAdminList(PageInfo pageInfo){
        String sql="select * from adminInfo where state!='"+AdminState.DELETE.getValue()+"' limit ?,?";
        return DBUtil.getList(sql,AdminInfo.class,pageInfo.getBeginRow(),pageInfo.getPageSize());
    }
    //用户角色管理 查询列表
    public List<AdminInfo> getRoleAdminList(PageInfo pageInfo){
        String sql="select a.*,r.roleName from adminInfo a join roleInfo r on a.roleId=r.id  where state!='"+AdminState.DELETE.getValue()+"' limit ?,?";
        return DBUtil.getList(sql,AdminInfo.class,pageInfo.getBeginRow(),pageInfo.getPageSize());
    }
    //删除 锁定 用户的信息 改变状态 批量删除
    public int updateState(String flag,Integer id){
        //删除用户
        if(flag.equals("deleteAdmin")){
            String sql="update adminInfo set state='"+AdminState.DELETE.getValue()+"' where id=?";
            return DBUtil.update(sql,id);
        }
        //锁定用户
        else if(flag.equals("lockAdmin")){
            String sql="update adminInfo set state='"+AdminState.LOCK.getValue()+"' where id=?";
            return DBUtil.update(sql,id);
        }
        //解锁用户
        else{
            String sql="update adminInfo set state='"+AdminState.NORMAL.getValue()+"' where id=?";
            return DBUtil.update(sql,id);
        }
    }
    //修改用户
    public int updateAdmin(AdminInfo adminInfo){
        String sql="update adminInfo set note=? where id=?";
        return DBUtil.update(sql,adminInfo.getNote(),adminInfo.getId());
    }

}
