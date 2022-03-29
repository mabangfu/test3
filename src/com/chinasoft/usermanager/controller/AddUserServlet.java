package com.chinasoft.usermanager.controller;

import com.chinasoft.usermanager.domain.User;
import com.chinasoft.usermanager.service.UserService;
import com.chinasoft.usermanager.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addUsers")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
//        将浏览器中传入的所有参数以map的形式封装
        Map<String,String[]> parameterMap = req.getParameterMap();
        try {
            User user = new User();
//        将map中的参数封装到对象中
            BeanUtils.populate(user,parameterMap);
            UserService userService = new UserServiceImpl();
            userService.addUsers(user);
//            重定向
            resp.sendRedirect(req.getServletContext().getAttribute("path")+"/findAllUserByPage?current=1&size=10");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
