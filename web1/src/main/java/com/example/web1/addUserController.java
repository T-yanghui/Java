package com.example.web1;

import com.example.web1.Service.UserService;
import com.example.web1.Utils.UUID20;
import com.example.web1.moudle.User;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*                 add user             */
public class addUserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_name = req.getParameter("username");
        String password = req.getParameter("password");

        User.UserBuilder builder = User.UserBuilder.anUser();
        User user = builder.withUser_id(UUID20.getUUID()).withUser_name(user_name).withPassword(password).build();

        try {
            UserService userService = new UserService();
            User res = userService.findUser(user);
            if(res != null){
                req.setAttribute("registerInfo","false");
            }
            else {
                userService.addUser(user);
                req.setAttribute("registerInfo","true");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("addUserController_添加用户失败!");
        }
        req.getRequestDispatcher("/WEB-INF/JSP/addUser.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}