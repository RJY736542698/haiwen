package com.dao;

import com.beans.CateInfo;
import com.beans.GoodsInfo;
import com.jdbc.DBUtil;
import com.util.PageInfo;
import com.util.PageUtil;

import java.util.ArrayList;
import java.util.List;

public class GoodsDao {
    //查看商品维护列表  带分页的
    public List<GoodsInfo> getGoodsList(PageInfo pageInfo){
        String sql="SELECT g.id,g.goodsName,g.price,g.unit,c.cateName bigCateName FROM goodsInfo g JOIN cateinfo c ON g.bigCateId=c.id  limit ?,?";
        List<GoodsInfo> list = DBUtil.getList(sql, GoodsInfo.class,pageInfo.getBeginRow(),pageInfo.getPageSize());
        return list;
    }
    //根据商品id 查找对应的小分类
    public CateInfo getSmallCateNameById(int id){
            String sql="SELECT c.* FROM cateInfo c JOIN goodsinfo g ON c.id=g.smallCateId WHERE parentId IN(SELECT c.id FROM cateInfo c JOIN goodsinfo g ON c.id=g.bigCateId WHERE c.parentId=0) AND g.id=?";

        CateInfo singleObj = DBUtil.getSingleObj(sql, CateInfo.class, id);
            return  singleObj;
    }

    //查看商品总个数 进行分页
    public int getCount(){
        String sql="select count(*) from goodsInfo ";
        long count=DBUtil.getScalar(sql);
        return Integer.parseInt(count+"");
    }

    //商品删除
    public int deleteGoods(int id){
        return DBUtil.update("delete from goodsInfo where id=?",id);
    }
    //查看商品总个数 进行分页
    public int getCount1(int bigId,int smallId,String goodsName){
            String sql="select count(*) from goodsInfo g join cateInfo c on g.bigCateId=c.id where g.bigCateId in(select id from cateInfo where id=?) and g.smallCateId in(select id from cateInfo where id=?) and g.goodsName like '%"+goodsName+"%'";
            long count=DBUtil.getScalar(sql,bigId,smallId);
            return Integer.parseInt(count+"");
    }
    public int getCount2(String goodsName){
            String sql="select count(*) from goodsInfo g join cateInfo c on g.bigCateId=c.id where  g.goodsName like '%"+goodsName+"%'";
            long count=DBUtil.getScalar(sql);
            return Integer.parseInt(count+"");
    }
    public int getCount3(int bigId,int smallId){
            String sql="select count(*) from goodsInfo g join cateInfo c on g.bigCateId=c.id where g.bigCateId in(select id from cateInfo where id=?) and g.smallCateId in(select id from cateInfo where id=?) ";
            long count=DBUtil.getScalar(sql,bigId,smallId);
            return Integer.parseInt(count+"");
    }

    //多条件查询
    public List<GoodsInfo> getgoods(int bigId,int smallId,String goodsName,PageInfo pageInfo){
        if(goodsName!=null && !goodsName.equals("")){
            if(bigId>0){
                String sql="select g.id,g.goodsName,g.price,g.unit,c.cateName bigCateName from goodsInfo g join cateInfo c on g.bigCateId=c.id where g.bigCateId in(select id from cateInfo where id=?) and g.smallCateId in(select id from cateInfo where id=?) and g.goodsName like '%"+goodsName+"%' limit ?,? ";
                return DBUtil.getList(sql,GoodsInfo.class,bigId,smallId,pageInfo.getBeginRow(),pageInfo.getPageSize());
            }else{
                String sql="select g.id,g.goodsName,g.price,g.unit,c.cateName bigCateName from goodsInfo g join cateInfo c on g.bigCateId=c.id where g.goodsName like '%"+goodsName+"%' limit ?,?";
                return DBUtil.getList(sql,GoodsInfo.class,pageInfo.getBeginRow(),pageInfo.getPageSize());
            }
        }else{
            String sql="select g.id,g.goodsName,g.price,g.unit,c.cateName bigCateName from goodsInfo g join cateInfo c on g.bigCateId=c.id where g.bigCateId in(select id from cateInfo where id=?) and g.smallCateId in(select id from cateInfo where id=?) limit ?,?  ";
            return DBUtil.getList(sql,GoodsInfo.class,bigId,smallId,pageInfo.getBeginRow(),pageInfo.getPageSize());
        }
    }
    //添加商品
    public int addGoods(GoodsInfo goodsInfo){
        String sql="insert into goodsInfo(goodsName,bigCateId,smallCateId,unit,price,producter,pictureData,des) values(?,?,?,?,?,?,?,?)";
        return DBUtil.addWithId(sql,goodsInfo.getGoodsName(),goodsInfo.getBigCateId(),goodsInfo.getSmallCateId(),goodsInfo.getUnit(),goodsInfo.getPrice(),goodsInfo.getProducter(),goodsInfo.getPictureData(),goodsInfo.getDes());
    }
    //根据id查找商品 显示商品图片
    public GoodsInfo getGoodsById(int id){
        return DBUtil.getSingleObj("select * from goodsInfo where id=?",GoodsInfo.class,id);
    }
    //修改商品
    public int updateGoods(GoodsInfo goodsInfo){
        String sql="update goodsInfo set goodsName=?,bigCateId=?,smallCateId=?,unit=?,price=?,producter=?,pictureData=?,des=? where id=?";
        return DBUtil.update(sql,goodsInfo.getGoodsName(),goodsInfo.getBigCateId(),goodsInfo.getSmallCateId(),goodsInfo.getUnit(),goodsInfo.getPrice(),goodsInfo.getProducter(),goodsInfo.getPictureData(),goodsInfo.getDes(),goodsInfo.getId());
    }
}
