package com.example.web1;

import com.example.web1.Service.UserService;
import com.example.web1.Utils.popAlert;
import com.example.web1.moudle.Role;
import com.example.web1.moudle.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebFilter(filterName = "PrivilegeFilter" ,urlPatterns = "/*")
public class PrivilegeFilter implements Filter {
    private Map<String, String> RoleMap = new HashMap<>();
    public void init(FilterConfig config) throws ServletException {
        RoleMap.put("addUser","admin");
        RoleMap.put("addUserController","admin");
        RoleMap.put("getUsers","admin");
        RoleMap.put("updateRoles","admin");
        RoleMap.put("updateUsers","admin");
    }
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;

        //用户请求资源地址
        String uri = httpServletRequest.getRequestURI();
        StringBuffer url = httpServletRequest.getRequestURL();

        String[] uris = uri.split("/");
        if(uris.length < 3){
            httpServletResponse.sendRedirect("login");
            return;
        }
        System.out.println("0"+uris[0]);
        System.out.println(uris[1]);
        System.out.println(uris[2]);
        uri = uris[2];
        //对比条件,如果没有限制就放行
        if(RoleMap.get(uri) == null){
            chain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        //不为空，先判断是否登录
        System.out.println(httpServletRequest.getSession().getAttribute("user"));
        if(httpServletRequest.getSession().getAttribute("user") == null){
            httpServletResponse.sendRedirect("login");
            return;
        }
        //判断权限
        User user = (User)httpServletRequest.getSession().getAttribute("user");
        UserService userService = new UserService();
        List<Role> roles = userService.getRoles(user);
        List<String> list = new ArrayList<>();
        for(Role role: roles){
            list.add(role.getRole_name());
        }
        if(!list.contains(RoleMap.get(uri))){
            return;
        }
        chain.doFilter(httpServletRequest,httpServletResponse);
    }
}
