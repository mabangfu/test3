package com.chinasoft.usermanager.controller;

import com.chinasoft.usermanager.service.UserService;
import com.chinasoft.usermanager.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        UserService userService = new UserServiceImpl();
        userService.deleteUser(id);
        resp.sendRedirect(req.getServletContext().getAttribute("path")+"/findAllUserByPage?current=1&size=10");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
