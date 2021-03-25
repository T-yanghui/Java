package com.example.web1;

import com.example.web1.Service.UserService;
import com.example.web1.moudle.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "updateUsers", value = "/updateUsers")
public class updateUsers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_name = request.getParameter("user_name");
        User.UserBuilder builder = User.UserBuilder.anUser();
        User user = builder.withUser_name(user_name).build();
        UserService userService = new UserService();
        String doDelete = request.getParameter("delete");
        String doUpdate = request.getParameter("update");
        if (doDelete!=null && doDelete.equals("true")) {
            userService.deleteUser(user);
            response.sendRedirect("getUsers");
        }
        else if(doUpdate!=null && request.getParameter("update").equals("1")){
               user = userService.findUser(user);
               request.setAttribute("user",user);
               request.getRequestDispatcher("/WEB-INF/JSP/updateUsers.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        查询用户
        String doUpdate = request.getParameter("update");

        if(doUpdate!=null && doUpdate.equals("2")){
//            获取更新信息
            String user_id = request.getParameter("user_id");
            String user_name = request.getParameter("user_name");
            String pwd = request.getParameter("password");

            User.UserBuilder builder = User.UserBuilder.anUser();
            User user = builder.withUser_name(user_name).withPassword(pwd).build();
            UserService userService = new UserService();
            userService.updateUser(user);
            request.setAttribute("update","success");
            request.getRequestDispatcher("/WEB-INF/JSP/updateUsers.jsp").forward(request,response);
        }
    }
}
