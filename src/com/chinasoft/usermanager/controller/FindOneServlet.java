package com.chinasoft.usermanager.controller;

import com.alibaba.fastjson.JSON;
import com.chinasoft.usermanager.domain.User;
import com.chinasoft.usermanager.service.UserService;
import com.chinasoft.usermanager.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findOne")
public class FindOneServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Integer id = Integer.valueOf(req.getParameter("id"));
        UserService service = new UserServiceImpl();
        User user = service.findOne(id);
        String s = JSON.toJSONString(user);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(s);
//        req.getSession().setAttribute("user",user);
//        resp.sendRedirect(req.getServletContext().getAttribute("path")+"/update.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
