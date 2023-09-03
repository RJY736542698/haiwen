package com.dao;

import com.beans.MemberInfo;
import com.jdbc.DBUtil;
import com.util.PageInfo;
import com.util.String_UtilDate_SqlDate;

import java.sql.Date;
import java.util.List;

public class MemberDao {
    //查询所有会员信息
    public List<MemberInfo> getMemberList(PageInfo pageInfo){
        String sql="select * from memberInfo  limit ?,?";
        return DBUtil.getList(sql,MemberInfo.class,pageInfo.getBeginRow(),pageInfo.getPageSize());
    }
    //查看总用户信息个数
    public int getRowCount(){
        String sql="select count(*) from memberInfo";
        long count = DBUtil.getScalar(sql);
        return Integer.parseInt(count+"");
    }

    public MemberInfo getMember(int id) {
        String sql="select * from memberInfo where id=?";
        return DBUtil.getSingleObj(sql,MemberInfo.class,id);
    }
    //删除会员
    public void deleteMember(int id) {
        String sql="delete from memberInfo where id=?";
        DBUtil.update(sql,id);
    }

    //多条件查询 总条数
    public int getRowCount1(String memberNo, Date date1,Date date2){
        String sql="select count(*) from memberInfo where memberNo like '%"+memberNo+"%' and registerDate between ? and ?";
        long count = DBUtil.getScalar(sql,date1,date2);
        return Integer.parseInt(count+"");
    }
    public int getRowCount2(Date date1,Date date2){
        String sql="select count(*) from memberInfo where registerDate between ? and ?";
        long count = DBUtil.getScalar(sql,date1,date2);
        return Integer.parseInt(count+"");
    }

    //根据会员名称和注册日期查询
    public List<MemberInfo> getMemberByNameAndDate(String memberNo, Date date1,Date date2 ,PageInfo pageInfo){
        if(memberNo!=null && !memberNo.equals("")){
            String sql="select * from memberInfo where memberNo like '%"+memberNo+"%' and registerDate between ? and ? limit ?,?";
            return DBUtil.getList(sql,MemberInfo.class,date1,date2,pageInfo.getBeginRow(),pageInfo.getPageSize());
        }else {
            String sql="select * from memberInfo where registerDate between ? and ? limit ?,?";
            return DBUtil.getList(sql,MemberInfo.class,date1,date2,pageInfo.getBeginRow(),pageInfo.getPageSize());
        }

    }
}
