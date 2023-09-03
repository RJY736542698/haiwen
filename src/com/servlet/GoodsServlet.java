package com.servlet;

import com.alibaba.fastjson.JSON;
import com.beans.CateInfo;
import com.beans.GoodsInfo;
import com.dao.CateDao;
import com.dao.GoodsDao;
import com.jdbc.DBUtil;
import com.util.PageInfo;
import com.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
@WebServlet("/GoodsServlet")
@MultipartConfig
public class GoodsServlet extends HttpServlet {
    GoodsDao goodsDao=new GoodsDao();
    CateDao cateDao=new CateDao();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        //处理请求中文乱码,将来可以放到过滤器中处理
        req.setCharacterEncoding("utf-8");
        String flag = req.getParameter("flag");
        if (flag.equals("manage")) {
            manage(req, resp);
        } else if (flag.equals("deleteGoods")) {
            deleteGoods(req, resp);
        } else if (flag.equals("selectGoods")) {
            selectGoods(req, resp);
        } else if (flag.equals("addGoods")) {
            addGoods(req, resp);
        } else if ("showPicture".equals(flag)) {
            showPicture(req, resp);
        }
        else if ("modifyGoods".equals(flag)) {
            modifyGoods(req, resp);
        }
        else if ("updateGoods".equals(flag)) {
            updateGoods(req, resp);
        }
    }

    private void updateGoods(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String goodsName=req.getParameter("goodsName");
        int bigCateId=Integer.parseInt(req.getParameter("bigCateId"));
        int goodsId=Integer.parseInt(req.getParameter("goodsId"));
        System.out.println(goodsId);
        int smallCateId=Integer.parseInt(req.getParameter("smallCateId"));
        String unit=req.getParameter("unit");
        Float price=Float.parseFloat(req.getParameter("price"));
        String producter=req.getParameter("producter");
        String des=req.getParameter("des");
        //获取图片
        Part part=req.getPart("picture");
        InputStream inputStream = part.getInputStream();
        byte[] pictureData=new byte[inputStream.available()];
        inputStream.read(pictureData);
        GoodsInfo goodsInfo=new GoodsInfo();
        goodsInfo.setId(goodsId);
        goodsInfo.setGoodsName(goodsName);
        goodsInfo.setBigCateId(bigCateId);
        goodsInfo.setSmallCateId(smallCateId);
        goodsInfo.setUnit(unit);
        goodsInfo.setPrice(price);
        goodsInfo.setProducter(producter);
        goodsInfo.setDes(des);
        goodsInfo.setPictureData(pictureData);
        System.out.println(goodsInfo);
        goodsDao.updateGoods(goodsInfo);
        GoodsInfo goods = goodsDao.getGoodsById(goodsId);
        req.setAttribute("goodsId",goods.getId());
        req.setAttribute("goods",goods);
        req.setAttribute("msg","修改成功!");
        req.getRequestDispatcher("/goods/goods-update.jsp").forward(req,resp);
    }

    private void modifyGoods(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int goodsId=Integer.parseInt(req.getParameter("goodsId"));
        GoodsInfo goods = goodsDao.getGoodsById(goodsId);
        req.setAttribute("goodsId",goods.getId());
        req.setAttribute("goods",goods);
        req.getRequestDispatcher("/goods/goods-update.jsp").forward(req,resp);
    }

    private void showPicture(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int goodsId=Integer.parseInt(req.getParameter("goodsId"));
//        System.out.println(goodsId);
        GoodsInfo goods = goodsDao.getGoodsById(goodsId);
        resp.setContentType("image/jpg");
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write(goods.getPictureData());
        outputStream.flush();
    }
    private void addGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        System.out.println("bbb");
        String goodsName=req.getParameter("goodsName");
        int bigCateId=Integer.parseInt(req.getParameter("bigCateId"));
        int smallCateId=Integer.parseInt(req.getParameter("smallCateId"));
        String unit=req.getParameter("unit");
        Float price=Float.parseFloat(req.getParameter("price"));
        String producter=req.getParameter("producter");
        String des=req.getParameter("des");
        //获取图片
        Part part=req.getPart("picture");
        InputStream inputStream = part.getInputStream();
        byte[] pictureData=new byte[inputStream.available()];
        inputStream.read(pictureData);
        GoodsInfo goodsInfo=new GoodsInfo();
        goodsInfo.setGoodsName(goodsName);
        goodsInfo.setBigCateId(bigCateId);
        goodsInfo.setSmallCateId(smallCateId);
        goodsInfo.setUnit(unit);
        goodsInfo.setPrice(price);
        goodsInfo.setProducter(producter);
        goodsInfo.setDes(des);
        goodsInfo.setPictureData(pictureData);
        System.out.println(goodsInfo);
        int goodsId = goodsDao.addGoods(goodsInfo);
        System.out.println(goodsId);
        req.setAttribute("goodsId",goodsId);
        req.setAttribute("bigCateId",bigCateId);
        req.setAttribute("smallCateId",smallCateId);
        req.setAttribute("msg","添加成功");
        req.getRequestDispatcher("/goods/goods-add.jsp").forward(req,resp);
    }

    private void selectGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String goodsName=req.getParameter("goodsName");
        int bigId=Integer.parseInt(req.getParameter("bigCateId"));
        int smallId=Integer.parseInt(req.getParameter("smallCateId"));
        int pageIndex=1;
        String pageIndexStr=req.getParameter("pageIndex");
        if (pageIndexStr!=null){
            pageIndex=Integer.parseInt(pageIndexStr);
        }
        int pageSize=5;
        if(goodsName!=null && !goodsName.equals("")){
            if(bigId>0){

                int rowCount=goodsDao.getCount1(bigId,smallId,goodsName);
                PageInfo pageInfo= PageUtil.getPageInfo(pageSize,rowCount,pageIndex);
                List<GoodsInfo> goodsList = goodsDao.getgoods(bigId, smallId, goodsName,pageInfo);
                for (GoodsInfo goodsInfo : goodsList) {
                    System.out.println(goodsInfo);
                    CateInfo smallCateNameById = goodsDao.getSmallCateNameById(goodsInfo.getId());
                    goodsInfo.setSmallCateName(smallCateNameById.getCateName());
                }
                req.setAttribute("page",pageInfo);
                req.setAttribute("bigId",bigId);
                req.setAttribute("smallId",smallId);
                req.setAttribute("goodsName",goodsName);
                req.setAttribute("goodsList",goodsList);
                req.getRequestDispatcher("/goods/goods-select-page.jsp").forward(req,resp);

            }else{

                int rowCount=goodsDao.getCount2(goodsName);
                PageInfo pageInfo= PageUtil.getPageInfo(pageSize,rowCount,pageIndex);
                List<GoodsInfo> goodsList = goodsDao.getgoods(bigId, smallId, goodsName,pageInfo);
                for (GoodsInfo goodsInfo : goodsList) {
                    System.out.println(goodsInfo);
                    CateInfo smallCateNameById = goodsDao.getSmallCateNameById(goodsInfo.getId());
                    goodsInfo.setSmallCateName(smallCateNameById.getCateName());
                }
                req.setAttribute("page",pageInfo);
//                req.setAttribute("bigId",bigId);
                req.setAttribute("smallId",smallId);
                req.setAttribute("goodsName",goodsName);
                req.setAttribute("goodsList",goodsList);
                req.getRequestDispatcher("/goods/goods-select-page.jsp").forward(req,resp);
            }
        }else{

            int rowCount=goodsDao.getCount3(bigId,smallId);
            PageInfo pageInfo= PageUtil.getPageInfo(pageSize,rowCount,pageIndex);
            List<GoodsInfo> goodsList = goodsDao.getgoods(bigId, smallId, goodsName,pageInfo);
            for (GoodsInfo goodsInfo : goodsList) {
                System.out.println(goodsInfo);
                CateInfo smallCateNameById = goodsDao.getSmallCateNameById(goodsInfo.getId());
                goodsInfo.setSmallCateName(smallCateNameById.getCateName());
            }
            req.setAttribute("page",pageInfo);
            req.setAttribute("bigId",bigId);
            req.setAttribute("smallId",smallId);
//            req.setAttribute("goodsName",goodsName);
            req.setAttribute("goodsList",goodsList);
            req.getRequestDispatcher("/goods/goods-select-page.jsp").forward(req,resp);

        }

    }

    //查看商品维护列表
    private void manage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int pageIndex=1;
        String pageIndexStr=req.getParameter("pageIndex");
        if (pageIndexStr!=null){
            pageIndex=Integer.parseInt(pageIndexStr);
        }
        int pageSize=5;
        int rowCount=goodsDao.getCount();
        PageInfo pageInfo= PageUtil.getPageInfo(pageSize,rowCount,pageIndex);
        List<GoodsInfo> goodsList = goodsDao.getGoodsList(pageInfo);

        for (GoodsInfo goodsInfo : goodsList) {
            CateInfo smallCateNameById = goodsDao.getSmallCateNameById(goodsInfo.getId());
            goodsInfo.setSmallCateName(smallCateNameById.getCateName());
        }
        req.setAttribute("goodsList",goodsList);
        req.setAttribute("page",pageInfo);
        req.getRequestDispatcher("/goods/goods-manage.jsp").forward(req,resp);
    }
    //商品删除
    private void deleteGoods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id=Integer.parseInt(req.getParameter("id"));
        goodsDao.deleteGoods(id);
        manage(req,resp);
    }

}
