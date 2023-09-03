package com.servlet;

import com.beans.AdminInfo;
import com.dao.AdminDao;
import com.util.Des;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    AdminDao adminDao=new AdminDao();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        String flag=req.getParameter("flag");
        if(flag.equals("login")){
            login(req,resp);
        }else if(flag.equals("logout")){
            logout(req,resp);
        }else if(flag.equals("refresh")){
            refresh(req,resp);
        }
    }
    //刷新session
    private void refresh(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        session.getAttribute("session-admin");
    }



    //退出登录
    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();//退出登陆以后清除session
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        //从前端接收数据
        String adminName = req.getParameter("adminName");
        String password = req.getParameter("password");
        System.out.println(password);
        password= Des.encStr(password);
        System.out.println(password);
        AdminInfo login = adminDao.login(adminName, password);
        System.out.println(login);
        //为null表示账号或者密码不正确 或者 该账号被删除了
        if(login==null){
            //resp.getWriter().print();其中的print不能使用println,因为会换行
            resp.getWriter().print("0");
        }else{
            //找到该管理员了,还需判断账号是否被锁定了 没被锁定就将对象存储到sesion中 返回1  锁定就返回2
            if(login.getState().equals("1")){
                req.setAttribute("login",login);
                req.getSession().setAttribute("session_admin",login);
                resp.getWriter().print("1");
            }else{
                resp.getWriter().print("2");
            }
        }
    }
}
