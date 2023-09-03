package com.servlet;

import com.beans.MemberInfo;
import com.dao.MemberDao;
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

@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
    MemberDao memberDao=new MemberDao();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String flag=req.getParameter("flag");
        if(flag.equals("manage")){
            manage(req,resp);
        }else if(flag.equals("getMember")){
            getMember(req,resp);
        }
        else if(flag.equals("deleteMember")){
            deleteMember(req,resp);
        } else if(flag.equals("getMemberByNameAndDate")){
            System.out.println("aaaaaaaaaa");
            try {
                getMemberByNameAndDate(req,resp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void getMemberByNameAndDate(HttpServletRequest req, HttpServletResponse resp) throws ParseException, ServletException, IOException {
        String memberNo=req.getParameter("memberNo");
        String d1=req.getParameter("date1");
        String d2=req.getParameter("date2");
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

        if(memberNo!=null && !memberNo.equals("")){
            int rowCount=memberDao.getRowCount1(memberNo,date1,date2);
            PageInfo page= PageUtil.getPageInfo(pageSize,rowCount,pageIndex);
            List<MemberInfo> memberByNameAndDate = memberDao.getMemberByNameAndDate(memberNo, date1, date2,page);
            req.setAttribute("memberList",memberByNameAndDate);
            req.setAttribute("page",page);
            req.setAttribute("memberNo",memberNo);
            if(d1!=null &&!d1.equals("")){
                req.setAttribute("date1",date1);
            }if(d2!=null &&!d2.equals("")){
                req.setAttribute("date2",date2);
            }
            req.getRequestDispatcher("/member/member-SelectNameByDate.jsp").forward(req,resp);
        }else {
            int rowCount=memberDao.getRowCount2(date1,date2);
            PageInfo page= PageUtil.getPageInfo(pageSize,rowCount,pageIndex);
            List<MemberInfo> memberByNameAndDate = memberDao.getMemberByNameAndDate(memberNo, date1, date2,page);
            req.setAttribute("memberList",memberByNameAndDate);
            req.setAttribute("page",page);
            req.setAttribute("memberNo",memberNo);
            if(d1!=null &&!d1.equals("")){
                req.setAttribute("date1",date1);
            }if(d2!=null &&!d2.equals("")){
                req.setAttribute("date2",date2);
            }
            req.getRequestDispatcher("/member/member-SelectNameByDate.jsp").forward(req,resp);
        }




    }

    private void deleteMember(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        memberDao.deleteMember(id);
        manage(req,resp);
    }

    //查看会员
    private void getMember(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        MemberInfo memberInfo = memberDao.getMember(id);
        req.setAttribute("member",memberInfo);
        req.getRequestDispatcher("/member/member-select-page.jsp").forward(req,resp);
    }

    private void manage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageIndex=1;
        String pageIndexStr=req.getParameter("pageIndex");
        if (pageIndexStr!=null){
            pageIndex=Integer.parseInt(pageIndexStr);
        }
        int pageSize=5;
        int rowCount=memberDao.getRowCount();
        PageInfo page= PageUtil.getPageInfo(pageSize,rowCount,pageIndex);
        List<MemberInfo> memberList = memberDao.getMemberList(page);
        req.setAttribute("memberList",memberList);
        req.setAttribute("page",page);
        req.getRequestDispatcher("/member/member-manage.jsp").forward(req,resp);
    }
}
