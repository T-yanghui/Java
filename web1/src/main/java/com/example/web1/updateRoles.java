package com.example.web1;

import com.example.web1.Service.RoleService;
import com.example.web1.Service.UserService;
import com.example.web1.moudle.Role;
import com.example.web1.moudle.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "updateRoles", value = "/updateRoles")
public class updateRoles extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_id = request.getParameter("user_id");
        String user_name = request.getParameter("user_name");
        User user = new User();
        user.setUser_id(user_id);
        user.setUser_name(user_name);
        //获取用户所有角色
        UserService userService = new UserService();
        List<Role> roles = userService.getRoles(user);
        //获取所有角色
        RoleService roleService = new RoleService();
        List<Role> allRoles = roleService.getAll();
        request.setAttribute("user_name",user_name);
        request.setAttribute("user_id",user_id);
        request.setAttribute("roles",roles);
        request.setAttribute("allroles",allRoles);
        //交给updateRole.jsp处理
        request.getRequestDispatcher("/WEB-INF/JSP/updateRoles.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //获取用户信息
        String[] role_ids = request.getParameterValues("role_id");
        String user_id = request.getParameter("user_id");
        String user_name = request.getParameter("user_name");
        User user = new User();
        user.setUser_id(user_id);user.setUser_name(user_name);
        //更新角色信息
        List<Role> roles = new ArrayList<>();
        for (String role_id : role_ids){
            Role role = new Role();
            role.setRole_id(role_id);
            roles.add(role);
        }
        try {
            UserService userService = new UserService();
            userService.updateRole(user,roles);
        }catch (Exception e){
            e.printStackTrace();
        }
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
