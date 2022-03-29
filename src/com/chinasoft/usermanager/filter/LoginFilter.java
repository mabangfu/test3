package com.chinasoft.usermanager.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/index.jsp")
@WebServlet("/loginFilter")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
//        需要提供一个随机字符串通过cookie发送给前台并设定有效期和redis中有效期时间长度一致

//        关闭浏览器再开启时前台页面上会带上该字符串 获取该字符去redis查询用户id值 如果查到 则表示登录过
//        反之，表示没有登录过 然后跳转登录页面


//        获取登陆验证的凭证
        Object user = session.getAttribute("user");
        String uri = request.getRequestURI();
        if (user != null || uri.contains("/login.jsp") ||
                uri.contains("verifycode") || uri.contains("login") ||
                uri.contains("/css/") || uri.contains("/js/") ||
                uri.contains("/fonts/")) {
            chain.doFilter(req, resp);
        } else {
            response.sendRedirect(request.getServletContext().getAttribute("path")+"/login.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
