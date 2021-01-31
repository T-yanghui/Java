package com.example.web1;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Servlet1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("1_post");
        String username = req.getParameter("username");
        System.out.println(username);
        String result = (username.equals("田大宝")?"Successfully":"failed");
        ServletOutputStream out = resp.getOutputStream();
        out.write(result.getBytes(StandardCharsets.UTF_8));
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
