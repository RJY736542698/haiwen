package com.servlet;

import com.alibaba.fastjson.JSON;
import com.beans.CateInfo;
import com.dao.CateDao;
import com.util.PageInfo;
import com.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/CateServlet")
public class CateServlet extends HttpServlet {
    static CateDao cateDao=new CateDao();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        //处理请求中文乱码,将来可以放到过滤器中处理
        req.setCharacterEncoding("utf-8");
        String flag=req.getParameter("flag");
        if(flag.equals("smallCate")){
            smallCate(req,resp);
        }
        else if("bigCateadd".equals(flag)) {
            bigCateadd(req,resp);
        }
        else if("smallCateadd".equals(flag)) {
            smallCateadd(req,resp);
        }else if("manage".equals(flag)) {
            manage(req,resp);
        }else if("modifyBigCate".equals(flag)) {
            modifyBigCate(req,resp);
        }
        else if("isExitCate".equals(flag)) {
            isExitCate(req,resp);
        }
        else if("bigCateupdate".equals(flag)) {
            bigCateupdate(req,resp);
        }
        else if("deleteBigCate".equals(flag)) {
            deleteBigCate(req,resp);
        }
        else if("modifySmallCate".equals(flag)) {
            modifySmallCate(req,resp);
        }
        else if("smallCateupdate".equals(flag)) {
            smallCateupdate(req,resp);
        }
        else if("deleteSmallCate".equals(flag)) {
            deleteSmallCate(req,resp);
        }
    }
    //添加 之前判断是否存在该分类
    private void isExitCate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String cateName=req.getParameter("cateName");
        CateInfo cateInfo = cateDao.getIdBycateName(cateName);
        if(cateInfo==null) {
            resp.getWriter().print("ok");
        }else{
            resp.getWriter().print("no");
        }
    }

    private void deleteSmallCate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int smallCateId=Integer.parseInt(req.getParameter("smallCateId"));
        int i = cateDao.deleteSmallCateById(smallCateId);
        manage(req,resp);
    }

    private void smallCateupdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int smallCateId=Integer.parseInt(req.getParameter("smallCateId"));
        int parentId=Integer.parseInt(req.getParameter("parentId"));
        String cateName=req.getParameter("cateName");
        String des=req.getParameter("des");
        CateInfo cateInfo=new CateInfo();
        cateInfo.setCateName(cateName);
        cateInfo.setDes(des);
        cateInfo.setId(smallCateId);
        cateInfo.setParentId(parentId);
        cateDao.updateSmallCateById(cateInfo);
        req.setAttribute("msg","修改成功");
        modifySmallCate(req,resp);

    }

    private void modifySmallCate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int smallId=Integer.parseInt(req.getParameter("smallCateId"));
        CateInfo cate = cateDao.getCateById(smallId);
        req.setAttribute("cate",cate);
        req.getRequestDispatcher("/cate/smallcate-update.jsp").forward(req,resp);
    }

    private void deleteBigCate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bigCateId = Integer.parseInt(req.getParameter("bigCateId"));
        int i = cateDao.deleteBigCateById(bigCateId);
        if(i==0){
          req.setAttribute("msg","delete");
        }
            manage(req,resp);
    }
    private void bigCateupdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bigCateId=Integer.parseInt(req.getParameter("bigCateId"));
        String cateName=req.getParameter("cateName");
        String des=req.getParameter("des");
        CateInfo cateInfo=new CateInfo();
        cateInfo.setCateName(cateName);
        cateInfo.setDes(des);
        cateInfo.setId(bigCateId);
        cateDao.updateBigCateById(cateInfo);
        req.setAttribute("msg","修改成功");
        modifyBigCate(req,resp);
    }

    private void modifyBigCate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bigId=Integer.parseInt(req.getParameter("bigCateId"));
        CateInfo cate = cateDao.getCateById(bigId);
        req.setAttribute("cate",cate);
        req.getRequestDispatcher("/cate/bigcate-update.jsp").forward(req,resp);
    }

    private void manage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageIndex=1;
        String pageIndexStr=req.getParameter("pageIndex");
        if (pageIndexStr!=null){
            pageIndex=Integer.parseInt(pageIndexStr);
        }
        int pageSize=5;
        int rowCount=cateDao.getRowCount();
        PageInfo page= PageUtil.getPageInfo(pageSize,rowCount,pageIndex);
        List<CateInfo> cateList = cateDao.getCateList(0,page);
        List<CateInfo> list=new ArrayList<>();
        for (CateInfo cateInfo : cateList) {
            List<CateInfo> smallCateList = cateDao.getSmallCateList(cateInfo.getId());
            for (CateInfo info : smallCateList) {
                list.add(info);
            }
        }
        req.setAttribute("smallCateList",list);
        req.setAttribute("cateList",cateList);
        req.setAttribute("page",page);
        req.getRequestDispatcher("/cate/cate-manage.jsp").forward(req,resp);

    }

    private void smallCateadd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cateName=req.getParameter("cateName");
        String des=req.getParameter("des");
        int parentId=Integer.parseInt(req.getParameter("parentId"));
        CateInfo cateInfo=new CateInfo();
        cateInfo.setCateName(cateName);
        cateInfo.setDes(des);
        cateInfo.setParentId(parentId);
        cateDao.CateAdd(cateInfo);
        req.setAttribute("msg","添加成功");
        req.getRequestDispatcher("/cate/smallcate-add.jsp").forward(req,resp);
    }

    //添加一级分类
    private void bigCateadd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cateName=req.getParameter("cateName");
        String des=req.getParameter("des");
        CateInfo cateInfo=new CateInfo();
        cateInfo.setCateName(cateName);
        cateInfo.setDes(des);
        cateInfo.setParentId(0);
        cateDao.CateAdd(cateInfo);
        req.setAttribute("msg","添加成功");
        req.getRequestDispatcher("/cate/bigcate-add.jsp").forward(req,resp);

    }
    private void smallCate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer bigCateId =Integer.parseInt( req.getParameter("bigCateId"));
        List<CateInfo> smallCateList = cateDao.getSmallCateList(bigCateId);
        String data= JSON.toJSONString(smallCateList);
        resp.getWriter().print(data);
    }
}
