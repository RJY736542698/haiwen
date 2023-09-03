package com.dao;

import com.beans.CateInfo;
import com.google.gson.internal.$Gson$Preconditions;
import com.jdbc.DBUtil;
import com.util.PageInfo;

import java.util.ArrayList;
import java.util.List;

public class CateDao {
    //查询大分类的名称
    public List<CateInfo> getCateList(int parentId){
        String sql="select * from cateInfo where parentId=?";
        List<CateInfo> list = DBUtil.getList(sql, CateInfo.class, parentId);
        return list;
    }
    //查询大分类的名称 分页
    public List<CateInfo> getCateList(int parentId,PageInfo pageInfo){
        String sql="select * from cateInfo where parentId=? limit ?,?";
        List<CateInfo> list = DBUtil.getList(sql, CateInfo.class, parentId,pageInfo.getBeginRow(),pageInfo.getPageSize());
        return list;
    }
    public int getRowCount(){
        String sql ="select count(*) from cateInfo where parentId=0";
        long count=DBUtil.getScalar(sql);
        return Integer.parseInt(count+"");
    }

    //查询大分类的id 根据大分类Id 查询小分类  parentId 用来查询大分类的名称的id  id 用来判断小分类parentId为是否在大分类的id
    public List<CateInfo> getSmallCateList(int id){
        String sql="select * from cateInfo where parentId=?";
        List<CateInfo> list = DBUtil.getList(sql, CateInfo.class, id);
        for (CateInfo cateInfo : list) {
            if(cateInfo.getParentId()==0){
                cateInfo.setSubCateList(getCateList(id));
            }
        }
        return list;
    }

    //根据类型名称查询id
    public CateInfo getIdBycateName(String cateName){
        String sql="select * from cateInfo where cateName=?";
        return DBUtil.getSingleObj(sql,CateInfo.class,cateName);
    }
    //根据id查找
    public CateInfo getCateById(int id){
        String sql="select * from cateInfo where id=?";
        return DBUtil.getSingleObj(sql,CateInfo.class,id);
    }
    //根据bigid修改
    public int updateBigCateById(CateInfo cateInfo){
        String sql="update cateInfo set cateName=?,des=? where id=?";
        return DBUtil.update(sql,cateInfo.getCateName(),cateInfo.getDes(),cateInfo.getId());
    }
    //根据bigid删除  若下面有子分类 不可删除
    public int deleteBigCateById(int id){
        String sql1="select id from cateInfo where parentId=?";
        List<CateInfo> list = DBUtil.getList(sql1, CateInfo.class,id);
        if(list.size()==0){
            String sql="delete from cateInfo where id=? ";
            return DBUtil.update(sql,id);
        }
        return 0;
    }
    //修改小分类
    public int updateSmallCateById(CateInfo cateInfo){
        String sql="update cateInfo set cateName=?,des=? ,parentId=? where id=?";
        return DBUtil.update(sql,cateInfo.getCateName(),cateInfo.getDes(),cateInfo.getParentId(),cateInfo.getId());
    }
    //删除小分类
    public int deleteSmallCateById(int id) {
        String sql="delete from cateInfo where id=? ";
        return DBUtil.update(sql,id);
    }

    //添加一级分类 二级分类
    public void CateAdd(CateInfo cateInfo) {
        String sql="insert into cateInfo(cateName,des,parentId) values(?,?,?)";
        DBUtil.update(sql,cateInfo.getCateName(),cateInfo.getDes(),cateInfo.getParentId());
    }

    public static void main(String[] args) {
        CateDao cateDao=new CateDao();
        cateDao.deleteBigCateById(1);
    }

}
