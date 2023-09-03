package com.servlet;

import com.beans.OrderGoods;
import com.beans.OrderInfo;
import com.dao.OrderDao;
import com.jdbc.DBUtil;
import com.util.PageInfo;
import com.util.PageUtil;
import com.util.String_UtilDate_SqlDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    OrderDao orderDao=new OrderDao();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String flag=req.getParameter("flag");
        if(flag.equals("manage")){
            manage(req,resp);
        }else if(flag.equals("getOrderByNoAndDate")){
            try {
                getOrderByNoAndDate(req,resp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if(flag.equals("getOrder")){
            getOrder(req,resp);
        }
        else if(flag.equals("fahuo")){
            fahuo(req,resp);
        }
    }

    private void fahuo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId=Integer.parseInt(req.getParameter("id"));
        orderDao.fahuo(orderId);
        manage(req,resp);
    }

    private void getOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId=Integer.parseInt(req.getParameter("id"));
        List<OrderGoods> orderGoodsList=orderDao.getOrderGoodsById(orderId);
        float total=0;
        for (OrderGoods orderGoods : orderGoodsList) {
            total=total+orderGoods.getPrice();
        }
        req.setAttribute("orderGoodsList",orderGoodsList);
        OrderInfo order = orderDao.getOrderById(orderId);
        req.setAttribute("order",order);
        req.setAttribute("total",total);
        req.getRequestDispatcher("/order/order-select-page.jsp").forward(req,resp);
    }

    private void getOrderByNoAndDate(HttpServletRequest req, HttpServletResponse resp) throws ParseException, ServletException, IOException {
        String orderNo=req.getParameter("orderNo");
        String d1=req.getParameter("date1");
        String d2=req.getParameter("date2");
        String orderState=req.getParameter("orderState");
        Date date1=null;
        Date date2=null;
        int pageIndex=1;
        String pageIndexStr=req.getParameter("pageIndex");
        if (pageIndexStr!=null){
            pageIndex=Integer.parseInt(pageIndexStr);
        }
        int pageSize=5;
        if(d1!=null && !d1.equals("")){
            date1= String_UtilDate_SqlDate.utilDateToSqlDate(String_UtilDate_SqlDate.stringToUtuilDate(d1));
        }else{
            java.util.Date date = new java.util.Date(1999-1900, 6-1, 12);
            date1= String_UtilDate_SqlDate.utilDateToSqlDate(date);
        }
        if (d2!=null &&!d2.equals("")){
            date2= String_UtilDate_SqlDate.utilDateToSqlDate(String_UtilDate_SqlDate.stringToUtuilDate(d2));
        }else{
            date2= String_UtilDate_SqlDate.utilDateToSqlDate(new java.util.Date());
        }
        if(orderNo!=null && !orderNo.equals("")){
            if(!orderState.equals("全部")){
                int rowCount=orderDao.getRowCount1(orderNo,date1,date2,orderState);
                PageInfo page= PageUtil.getPageInfo(pageSize,rowCount,pageIndex);
                List<OrderInfo> orderList = orderDao.getOrderByNoAndDate(orderNo, date1, date2, orderState,page);
                req.setAttribute("page",page);
                req.setAttribute("orderList",orderList);
                req.setAttribute("orderNo",orderNo);
                if(d1!=null &&!d1.equals("")){
                    req.setAttribute("date1",date1);
                }if(d2!=null &&!d2.equals("")){
                    req.setAttribute("date2",date2);
                }
                req.setAttribute("orderState",orderState);
                req.getRequestDispatcher("/order/order-NoAndDateAndState.jsp").forward(req,resp);
            }else{
                int rowCount=orderDao.getRowCount2(orderNo,date1,date2);
                PageInfo page= PageUtil.getPageInfo(pageSize,rowCount,pageIndex);
                List<OrderInfo> orderList = orderDao.getOrderByNoAndDate(orderNo, date1, date2, orderState,page);
                req.setAttribute("page",page);
                req.setAttribute("orderList",orderList);
                req.setAttribute("orderNo",orderNo);
                if(d1!=null &&!d1.equals("")){
                    req.setAttribute("date1",date1);
                }if(d2!=null &&!d2.equals("")){
                    req.setAttribute("date2",date2);
                }
//                req.setAttribute("orderState",orderState);
                req.getRequestDispatcher("/order/order-NoAndDateAndState.jsp").forward(req,resp);
            }
        }else{
            if(!orderState.equals("全部")){
                int rowCount=orderDao.getRowCount3(date1,date2,orderState);
                PageInfo page= PageUtil.getPageInfo(pageSize,rowCount,pageIndex);
                List<OrderInfo> orderList = orderDao.getOrderByNoAndDate(orderNo, date1, date2, orderState,page);
                req.setAttribute("page",page);
                req.setAttribute("orderList",orderList);
//                req.setAttribute("orderNo",orderNo);
                if(d1!=null &&!d1.equals("")){
                    req.setAttribute("date1",date1);
                }if(d2!=null &&!d2.equals("")){
                    req.setAttribute("date2",date2);
                }
                req.setAttribute("orderState",orderState);
                req.getRequestDispatcher("/order/order-NoAndDateAndState.jsp").forward(req,resp);

            }else{
                int rowCount=orderDao.getRowCount4(date1,date2);
                PageInfo page= PageUtil.getPageInfo(pageSize,rowCount,pageIndex);
                List<OrderInfo> orderList = orderDao.getOrderByNoAndDate(orderNo, date1, date2, orderState,page);
                req.setAttribute("page",page);
                req.setAttribute("orderList",orderList);
//                req.setAttribute("orderNo",orderNo);
                if(d1!=null &&!d1.equals("")){
                    req.setAttribute("date1",date1);
                }if(d2!=null &&!d2.equals("")){
                    req.setAttribute("date2",date2);
                }
//                req.setAttribute("orderState",orderState);
                req.getRequestDispatcher("/order/order-NoAndDateAndState.jsp").forward(req,resp);
            }
        }

    }

    private void manage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageIndex=1;
        String pageIndexStr=req.getParameter("pageIndex");
        if (pageIndexStr!=null){
            pageIndex=Integer.parseInt(pageIndexStr);
        }
        int pageSize=5;
        int rowCount=orderDao.getRowCount();
        PageInfo page= PageUtil.getPageInfo(pageSize,rowCount,pageIndex);
        List<OrderInfo> orderList = orderDao.getOrderList(page);
        req.setAttribute("orderList",orderList);
        req.setAttribute("page",page);
        req.getRequestDispatcher("/order/order-manage.jsp").forward(req,resp);

    }
}
