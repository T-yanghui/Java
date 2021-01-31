package com.example.web1;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Servlet2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pic = req.getParameter("picture");
        //获取资源路径
        String path = this.getServletContext().getRealPath("/static/"+pic);
        //读取资源
        FileInputStream fileInputStream = new FileInputStream(path);
        //设置消息头，告诉浏览器，我要下载图片
        resp.setHeader("Content-Disposition", "attachment; filename="+pic);
        //把读取到的资源写给浏览器
        int len = 0;
        byte[] bytes = new byte[1024];
        ServletOutputStream servletOutputStream = resp.getOutputStream();

        while ((len = fileInputStream.read(bytes)) > 0) {
            servletOutputStream.write(bytes, 0, len);
        }

        //关闭资源
        servletOutputStream.close();
        fileInputStream.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
