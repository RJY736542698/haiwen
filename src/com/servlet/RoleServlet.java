package com.servlet;

import com.beans.AdminInfo;
import com.beans.MenuInfo;
import com.beans.RoleInfo;
import com.dao.AdminDao;
import com.dao.MenuDao;
import com.dao.RoleDao;
import com.util.PageInfo;
import com.util.PageUtil;

import javax.management.relation.Role;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/RoleServlet")
public class RoleServlet extends HttpServlet {
    private RoleDao roleDao=new RoleDao();
    private MenuDao menuDao=new MenuDao();
    AdminDao adminDao=new AdminDao();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String flag=req.getParameter("flag");
        if(flag.equals("manage")){
            manager(req,resp);
        }else if(flag.equals("roleMenu")){
            roleMenu(req,resp);
        }else if(flag.equals("updateRoleMenu")){
            updateRoleMenu(req,resp);
        }else if(flag.equals("roleadd")){
            roleAdd(req,resp);
        }
        else if(flag.equals("roleAdminmanager")){
            roleAdminmanager(req,resp);
        }
        else if(flag.equals("showRole")){
            showRole(req,resp);
        } else if(flag.equals("isExit")){
            isExit(req,resp);
        }
        else if(flag.equals("roleModify")){
            roleModify(req,resp);
        }
        else if(flag.equals("goUpdateRole")){
            goUpdateRole(req,resp);
        }
        else if(flag.equals("updateRole")){
            updateRole(req,resp);
        }
        else if(flag.equals("deleteRole")){
            deleteRole(req,resp);
        }
    }

    private void deleteRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int roleId=Integer.parseInt(req.getParameter("roleId"));
        int i = roleDao.deleteRole(roleId);
        System.out.println(i);
        req.setAttribute("msg",i);
        manager(req,resp);
    }

    private void updateRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int roleId=Integer.parseInt(req.getParameter("roleId"));
        String roleName=req.getParameter("roleName");
        String des=req.getParameter("des");
        RoleInfo roleInfo1=new RoleInfo();
        roleInfo1.setId(roleId);
        roleInfo1.setRoleName(roleName);
        roleInfo1.setDes(des);
        roleDao.updateRole(roleInfo1);
//        int roleId=Integer.parseInt(req.getParameter("roleId"));
        RoleInfo roleInfo = roleDao.getRoleInfoById(roleId);
        req.setAttribute("roleInfo",roleInfo);
        req.setAttribute("msg","修改成功");
        req.getRequestDispatcher("/perm/role-update.jsp").forward(req,resp);
    }

    //根据角色ID获取角色信息 跳转到修改页面
    private void goUpdateRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int roleId=Integer.parseInt(req.getParameter("roleId"));
        RoleInfo roleInfo = roleDao.getRoleInfoById(roleId);
        req.setAttribute("roleInfo",roleInfo);
        req.getRequestDispatcher("/perm/role-update.jsp").forward(req,resp);
    }

    //修改用户角色 进行数据回显
    private void roleModify(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int adminId=Integer.parseInt(req.getParameter("id"));
        String roleName=req.getParameter("roleName");
        RoleInfo roleInfo = roleDao.getRoleId(roleName);
        roleDao.updateAdminRole(adminId,roleInfo.getId());
        req.setAttribute("msg","修改成功!");
        showRole(req,resp);
    }

    //角色分配的之前 根据用户id数据回显
    private void showRole(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int adminId=Integer.parseInt(req.getParameter("id"));
        List<RoleInfo> roleList = roleDao.getRoleList();
        AdminInfo roleAdmin = roleDao.getRoleAdmin(adminId);
        req.setAttribute("roleAdmin",roleAdmin);
        req.setAttribute("roleList",roleList);
        req.getRequestDispatcher("/perm/role-modify.jsp").forward(req,resp);
    }
    //判断角色是否存在
    private void isExit(HttpServletRequest req, HttpServletResponse resp) throws  IOException{
        String roleName = req.getParameter("roleName");
        RoleInfo exit = roleDao.isExit(roleName);
        System.out.println(exit);
        if(exit==null) {
            resp.getWriter().print("ok");
        }else{
            resp.getWriter().print("no");
        }
    }
    //用户角色管理
    private void roleAdminmanager(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int pageIndex=1;
        String pageIndexStr=req.getParameter("pageIndex");
        if (pageIndexStr!=null){
            pageIndex=Integer.parseInt(pageIndexStr);
        }
        int pageSize=5;
        int rowCount=adminDao.getRowCount();
        PageInfo page= PageUtil.getPageInfo(pageSize,rowCount,pageIndex);
        List<AdminInfo> adminList = adminDao.getRoleAdminList(page);
        req.setAttribute("adminList",adminList);
        req.setAttribute("page",page);
        req.getRequestDispatcher("/perm/roleAdmin-manage.jsp").forward(req,resp);
    }
    //添加角色
    private void roleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        RoleInfo roleInfo=new RoleInfo();
        String roleName = req.getParameter("roleName");
        String des = req.getParameter("des");
        roleInfo.setRoleName(roleName);
        roleInfo.setDes(des);
        RoleInfo roleInfo1 = roleDao.addRole(roleInfo);
        req.setAttribute("param",roleInfo1);
        req.setAttribute("msg","添加成功");
        req.getRequestDispatcher("/perm/role-add.jsp").forward(req,resp);

    }

    //根据角色ID修改菜单管理  为了防止出现错误 先将数据库里角色所有的权限都删除 在根据所选中的权限重新添加
    private void updateRoleMenu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int roleId=Integer.parseInt(req.getParameter("id"));
        String[] menuId=req.getParameterValues("ids");
        roleDao.updateRoleMenu(roleId,menuId);
        req.setAttribute("msg","修改成功");
        roleMenu(req,resp);


    }
    //查看角色权限分配
    private void roleMenu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int roleId=Integer.parseInt(req.getParameter("id"));
        RoleInfo roleInfo = roleDao.getRoleInfoById(roleId);
        List<MenuInfo> menuList=menuDao.getMenuList(0);
        String menuIdStr=roleDao.getMenuIdStr(roleId);
        req.setAttribute("roleInfo", roleInfo);
        req.setAttribute("menuList", menuList);
        req.setAttribute("menuIdStr", menuIdStr);
        req.getRequestDispatcher("/perm/role-menu.jsp").forward(req,resp);
    }

    //查询角色列表,转到角色管理页
    private void manager(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageIndex = 1;
        String pageIndexStr = req.getParameter("pageIndex");
        if (pageIndexStr != null) {
            pageIndex = Integer.parseInt(pageIndexStr);
        }
        int pageSize = 5;
        int rowCount = roleDao.getRowCount();
        PageInfo page= PageUtil.getPageInfo(pageSize,rowCount,pageIndex);
        List<RoleInfo> roleList = roleDao.getRoleList(page);
        req.setAttribute("page",page);
        req.setAttribute("roleList",roleList);
        req.getRequestDispatcher("/perm/role-manage.jsp").forward(req,resp);
    }
}