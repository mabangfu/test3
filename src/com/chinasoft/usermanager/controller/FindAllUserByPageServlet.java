package com.chinasoft.usermanager.controller;

import com.chinasoft.usermanager.domain.PageInfo;
import com.chinasoft.usermanager.domain.User;
import com.chinasoft.usermanager.service.UserService;
import com.chinasoft.usermanager.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 分页查询用户列表
 */
@WebServlet("/findAllUserByPage")
public class FindAllUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
//        1.接收每页显示记录数和当前页号
        String cur = request.getParameter("current");
        if(cur == null || "".equals(cur)){
            cur = "1";
        }
        Integer current = Integer.valueOf(cur);
        String si = request.getParameter("size");
        if(si == null || "".equals(si)){
            si = "10";
        }
        Integer size = Integer.valueOf(si);
//        2.调用service层
        UserService userService = new UserServiceImpl();
        Map<String, String[]> condition = request.getParameterMap();
        PageInfo<User> page = userService.findUserByPage(size,current,condition);
//        3.将分页对象放到request域中
        request.setAttribute("page",page);
        request.setAttribute("condition",condition);
//        4.转发到List.jsp页面
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
