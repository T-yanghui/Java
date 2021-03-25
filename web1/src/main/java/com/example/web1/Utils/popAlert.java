package com.example.web1.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class popAlert {
    public static void send(HttpServletRequest request,HttpServletResponse response,String message) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert("+message+"); window.history.back(-1); </script>");
        out.flush();
        out.close();
    }
}
