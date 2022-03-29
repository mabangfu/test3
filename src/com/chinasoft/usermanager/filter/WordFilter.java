package com.chinasoft.usermanager.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@WebFilter(value = "/login.jsp",dispatcherTypes = DispatcherType.FORWARD)
public class WordFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//        获取登陆验证的凭证
        String username = req.getParameter("username");
//        获取敏感词文件
        FileReader fr = new FileReader("D:/javaweb/UserManager/web/txt/word.txt");
        BufferedReader br = new BufferedReader(fr);
//        System.out.println(username);//测试释放获取用户名
        String str = ""; //用于接收敏感词文件数据
        boolean flag = false;//true 表示存在敏感词
        while ((str = br.readLine()) != null) {
//            System.out.println(str); //测试接收数据内容情况
            if (str.contains(username)) {//判断登陆用户名是否属于敏感词
                flag = true;    //表示当前用户名存在敏感词
                break;
            }
        }
//        System.out.println(name);
        if (flag) {
            req.getSession().setAttribute("msg", "用户名存在敏感词！");
//            System.out.println("用户名存在敏感词！");
            resp.sendRedirect(req.getServletContext().getAttribute("path")+"/login.jsp"); //重定向
        } else {
            filterChain.doFilter(servletRequest,servletResponse); //放行
        }
    }

    @Override
    public void destroy() {

    }
}
