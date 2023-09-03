package com.aop;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        HttpServletRequest request=(HttpServletRequest)req;

        String url=request.getRequestURI();

        String contexPath=request.getContextPath();  //  /shop-sys
        String [] excluPaths= {
                contexPath+"/images",
                contexPath+"/css",
                contexPath+"/js",
                contexPath+"/login.jsp",
                contexPath+"/LoginServlet",
        };

        //不需要拦截的url直接放行
        for(String path:excluPaths) {
            if(url.startsWith(path)) {
                chain.doFilter(req,resp);
                return;
            }
        }

        //contextPath不拦,比如 /shop-sys 或 /shop-sys/
        if(url.equals(contexPath)||url.equals(contexPath+"/")) {
            chain.doFilter(req,resp);
            return;
        }

        //其他的所有请求,都要求要有session
        HttpSession session = request.getSession();
        if(session.getAttribute("session_admin")!=null) {
            chain.doFilter(req,resp);
        }
        else {
            String script="<script>alert('未登录或登陆超时,请重新登陆 ! '); window.top.location.href='login.jsp'</script>";
            resp.getWriter().print(script);
        }

    }
}
