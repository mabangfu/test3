package com.chinasoft.usermanager.controller;

import com.chinasoft.usermanager.service.UserService;
import com.chinasoft.usermanager.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteAll")
public class DeleteCheckedUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] arry = req.getParameterValues("ids");
        for(String id : arry){
            System.out.println(id);
            Integer i = Integer.valueOf(id);
            UserService service = new UserServiceImpl();
            service.deleteUser(i);
        }
        resp.sendRedirect(req.getServletContext().getAttribute("path")+"/findAllUserByPage?current=1&size=10");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
