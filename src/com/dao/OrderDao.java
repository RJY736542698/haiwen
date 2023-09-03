package com.dao;

import com.beans.OrderGoods;
import com.beans.OrderInfo;
import com.enums.OrderState;
import com.jdbc.DBUtil;
import com.util.PageInfo;
import com.util.String_UtilDate_SqlDate;

import java.sql.Date;
import java.util.List;

public class OrderDao {
    //查看所有订单
    public List<OrderInfo> getOrderList(PageInfo pageInfo){
        String sql="select * from orderInfo limit ?,?";
        return DBUtil.getList(sql,OrderInfo.class,pageInfo.getBeginRow(),pageInfo.getPageSize());
    }

    public int getRowCount() {
        String sql="select count(*) from orderInfo";
        long count = DBUtil.getScalar(sql);
        return Integer.parseInt(count+"");
    }
    //多条件查询总页数    orderNo!=null && !orderNo.equals("")    !orderState.equals("全部")
    public int getRowCount1(String orderNo, Date date1,Date date2,String orderState){
        String sql=" select count(*) from orderInfo where orderNo like '%"+orderNo+"%' and orderState=? and orderDate between ? and ? ";
        long count = DBUtil.getScalar(sql,orderState,date1,date2);
        return Integer.parseInt(count+"");
    }
    public int getRowCount2(String orderNo, Date date1,Date date2){
        String sql=" select count(*) from orderInfo where orderNo like '%"+orderNo+"%' and orderDate between ? and ?  ";
        long count = DBUtil.getScalar(sql,date1,date2);
        return Integer.parseInt(count+"");
    }
    public int getRowCount3( Date date1,Date date2,String orderState){
        String sql=" select count(*) from orderInfo where orderState=? and orderDate between ? and ? ";
        long count = DBUtil.getScalar(sql,orderState,date1,date2);
        return Integer.parseInt(count+"");
    }
    public int getRowCount4(Date date1,Date date2){
        String sql=" select count(*) from orderInfo where orderDate between ? and ? ";
        long count = DBUtil.getScalar(sql,date1,date2);
        return Integer.parseInt(count+"");
    }
    //多条件查询
    public List<OrderInfo> getOrderByNoAndDate(String orderNo, Date date1,Date date2,String orderState,PageInfo pageInfo){
        if(orderNo!=null && !orderNo.equals("")){
            if(!orderState.equals("全部")){
                String sql="select * from orderInfo where orderNo like '%"+orderNo+"%' and orderState=? and orderDate between ? and ? limit ?,?";
                return DBUtil.getList(sql,OrderInfo.class,orderState,date1,date2,pageInfo.getBeginRow(),pageInfo.getPageSize());
            }else{
                String sql="select * from orderInfo where orderNo like '%"+orderNo+"%' and orderDate between ? and ? limit ?,?";
                return DBUtil.getList(sql,OrderInfo.class,date1,date2,pageInfo.getBeginRow(),pageInfo.getPageSize());
            }
        }else{
            if(!orderState.equals("全部")){
                String sql="select * from orderInfo where orderState=? and orderDate between ? and ? limit ?,?";
                return DBUtil.getList(sql,OrderInfo.class,orderState,date1,date2,pageInfo.getBeginRow(),pageInfo.getPageSize());
            }else{
                String sql="select * from orderInfo where orderDate between ? and ? limit ?,?";
                return DBUtil.getList(sql,OrderInfo.class,date1,date2,pageInfo.getBeginRow(),pageInfo.getPageSize());
            }
        }
    }
    //根据订单ID查询商品
    public List<OrderGoods> getOrderGoodsById(int orderId) {
        String sql="select * from orderGoods where orderId=?";
        return DBUtil.getList(sql,OrderGoods.class,orderId);
    }
    //根据订单ID查询订单详情
    public OrderInfo getOrderById(int orderId){
        String sql="SELECT o.* , memberNo, m.address memberAddress FROM orderInfo o JOIN memberInfo m ON  o.memberId=m.id WHERE o.id=?";
        return DBUtil.getSingleObj(sql,OrderInfo.class,orderId);
    }

    public void fahuo(int orderId) {
        Date date=String_UtilDate_SqlDate.utilDateToSqlDate(new java.util.Date());
        String sql="update orderInfo set orderState='"+ OrderState.SHIPPED.getmsg() +"' , sendDate='"+date+"' where id=?";
        DBUtil.update(sql,orderId);
    }
}
