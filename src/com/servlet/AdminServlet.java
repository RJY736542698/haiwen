package com.servlet;

import com.beans.AdminInfo;
import com.dao.AdminDao;
import com.util.Des;
import com.util.PageInfo;
import com.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    AdminDao adminDao=new AdminDao();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理请求中文乱码,将来可以放到过滤器中处理
        req.setCharacterEncoding("utf-8");
        String flag = req.getParameter("flag");
        if(flag.equals("adminadd")){
            adminAdd(req,resp);
        }else if(flag.equals("isExit")){
            isExit(req,resp);
        }else if(flag.equals("manage")){
            manager(req,resp);
        }else if(flag.equals("deleteAdmin")){
            deleteAdmin(flag,req,resp);
        }
        else if(flag.equals("lockAdmin")){
            lockAdmin(flag,req,resp);
        }
        else if(flag.equals("UnlockAdmin")){
            UnlockAdmin(flag,req,resp);
        }
        else if(flag.equals("getupdateAdmin")){
            getupdateAdmin(req,resp);
        }
        else if(flag.equals("updateAdmin")){
            updateAdmin(req,resp);
        }
        else if(flag.equals("deletesAdmin")){
            deletesAdmin(req,resp);
        }
        else if(flag.equals("passwordUpdate")){
            System.out.println("passwordUpdate");
            passwordUpdate(req,resp);
        }
    }
        //修改用户密码
    private void passwordUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id =Integer.parseInt( req.getParameter("id"));
        String oldPassword=req.getParameter("oldPassword");
        oldPassword=Des.encStr(oldPassword);
        String newPassword=req.getParameter("password");
        newPassword=Des.encStr(newPassword);
        System.out.println(id+""+oldPassword+""+newPassword);
        //检查oldpassword输入是否正确
        boolean b = adminDao.checkOldPassword(id, oldPassword);
        if(b==true){
            adminDao.updatePassword(id,newPassword);
            req.setAttribute("msg","修改成功！");
            req.getSession().invalidate();//退出登陆以后清除session
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
//            resp.getWriter().print("ok");
        }else{
            req.setAttribute("msg","修改失败！就密码输入错误");
            req.getRequestDispatcher("/passwordUpdate.jsp").forward(req,resp);
        }

    }

    private void adminAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String adminName = req.getParameter("adminName");
        String password = req.getParameter("password");
        password= Des.encStr(password);
        String note = req.getParameter("note");
        AdminInfo adminInfo=new AdminInfo();
        adminInfo.setAdminName(adminName);
        adminInfo.setPassword(password);
        adminInfo.setNote(note);
        int i=adminDao.adminAdd(adminInfo);
        req.setAttribute("msg","添加成功");
        adminDao.isExit(adminInfo.getAdminName());
        req.setAttribute("param",adminInfo);
        req.getRequestDispatcher("/admin/admin-add.jsp").forward(req,resp);
    }
    private void isExit(HttpServletRequest req, HttpServletResponse resp) throws  IOException{
        String adminName = req.getParameter("adminName");
        AdminInfo exit = adminDao.isExit(adminName);
//        System.out.println(exit);
        if(exit==null) {
            resp.getWriter().print("ok");
        }else{
            resp.getWriter().print("no");
        }
    }
    //显示所有用户信息
    private void manager(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int pageIndex=1;
        String pageIndexStr=req.getParameter("pageIndex");
        if (pageIndexStr!=null){
            pageIndex=Integer.parseInt(pageIndexStr);
        }
        int pageSize=5;
        int rowCount=adminDao.getRowCount();
        PageInfo page= PageUtil.getPageInfo(pageSize,rowCount,pageIndex);
        List<AdminInfo> adminList = adminDao.getAdminList(page);
        req.setAttribute("adminList",adminList);
        req.setAttribute("page",page);
        req.getRequestDispatcher("/admin/admin-manage.jsp").forward(req,resp);
    }
    //单行删除用户信息
    private void deleteAdmin(String flag,HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
        int id;
        String strid=req.getParameter("id");
        if(strid!=null){
            id=Integer.parseInt(strid);
            adminDao.updateState(flag,id);
            manager(req,resp);
        }
    }
    //锁定用户
    private void lockAdmin(String flag,HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
        int id;
        String strid=req.getParameter("id");
        if(strid!=null){
            id=Integer.parseInt(strid);
            adminDao.updateState(flag,id);
            manager(req,resp);
        }
    }
    //为用户解锁
    private void UnlockAdmin(String flag,HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
        int id;
        String strid=req.getParameter("id");
        if(strid!=null){
            id=Integer.parseInt(strid);
            adminDao.updateState(flag,id);
            manager(req,resp);
        }
    }
    //获取要修改的用户的信息 进行数据回显
    private void getupdateAdmin(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
        String adminName=req.getParameter("adminName");
        AdminInfo adminInfo = adminDao.isExit(adminName);
        req.setAttribute("admin",adminInfo);
        req.getRequestDispatcher("/admin/admin-update.jsp").forward(req,resp);
    }
    //修改用户信息
    private void updateAdmin(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
        int id;
        String strid=req.getParameter("id");
        String adminName=req.getParameter("adminName");
//        String state=req.getParameter("state");
        String note=req.getParameter("note");
        if(strid!=null){
            id=Integer.parseInt(strid);
            AdminInfo adminInfo=new AdminInfo();
            adminInfo.setId(id);
//            adminInfo.setState(state);
            adminInfo.setAdminName(adminName);
            adminInfo.setNote(note);
            adminDao.updateAdmin(adminInfo);
            adminDao.isExit(adminName);
            req.setAttribute("admin",adminInfo);
            req.setAttribute("msg","修改成功");
            req.getRequestDispatcher("/admin/admin-update.jsp").forward(req,resp);

        }
    }
    //批量删除用户
    private void deletesAdmin (HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
        String[] strids=req.getParameterValues("id");
        int id;
        for (String strid : strids) {
            System.out.println(strid);
            if(strid!=null){
               id=Integer.parseInt(strid);
                System.out.println(id);
               adminDao.updateState("deleteAdmin",id);
            }
        }
        manager(req,resp);
    }

}
