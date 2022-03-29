package com.chinasoft.usermanager.controller;

import cn.hutool.captcha.CircleCaptcha;
import com.chinasoft.usermanager.domain.User;
import com.chinasoft.usermanager.service.UserService;
import com.chinasoft.usermanager.service.impl.UserServiceImpl;
import com.chinasoft.usermanager.utils.JedisPoolUtil;
import redis.clients.jedis.Jedis;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String code = req.getParameter("verifycode");
        CircleCaptcha captcha = (CircleCaptcha) req.getSession().getAttribute("captcha");

        if (captcha.verify(code)) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            UserService userService = new UserServiceImpl();
            UUID uuid = UUID.randomUUID();
            User user = userService.login(username, password,uuid);
            if (user != null) {
                req.getSession().setAttribute("UUID",uuid);
                Cookie cookie = new Cookie("UUID", req.getSession().getId());
                cookie.setMaxAge(30);
                resp.addCookie(cookie);
                req.getSession().setAttribute("user", user);
                resp.sendRedirect(req.getServletContext().getAttribute("path") + "/index.jsp");
                return;
            }
            req.setAttribute("msg", "用户名或密码错误");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;

        }
        req.setAttribute("msg", "验证码错误");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
        return;
    }
}
