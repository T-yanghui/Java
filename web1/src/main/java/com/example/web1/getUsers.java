package com.example.web1;

import com.example.web1.Service.UserService;
import com.example.web1.moudle.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "getUsers", value = "/getUsers")
public class getUsers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("****getUsers***");
        UserService userService = new UserService();
        List<User> users = userService.getAll();
        request.setAttribute("users",users);
        request.getRequestDispatcher("/WEB-INF/JSP/getUsers.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
