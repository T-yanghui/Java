package com.example.web1;

import com.example.web1.Service.UserService;
import com.example.web1.moudle.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/JSP/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_name = request.getParameter("user_name");
        String pwd = request.getParameter("password");
        User user = new User();
        user.setUser_name(user_name);
        UserService userService = new UserService();
        User res = userService.findUser(user);
        if(res == null){
            request.setAttribute("login","failed");
            request.getRequestDispatcher("/WEB-INF/JSP/login.jsp").forward(request,response);
        }
        if(res.getPassword().equals(pwd)){
            request.setAttribute("login","success");
            request.getSession().setAttribute("user",res);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }
}
