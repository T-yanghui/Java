<%--
  Created by IntelliJ IDEA.
  User: yang
  Date: 2021/2/27
  Time: 上午11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<form action="login" method="post">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" required="required" name="user_name"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" required="required" name="password"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Login"></td>
            <td><input type="reset" value="Reset"></td>
        </tr>
    </table>
</form>
<%
    if ((String)request.getAttribute("login") == "success") { %>
<script>
    alert("登录成功！");
    window.location="files";
</script>
<%}%>
<%
    if ((String)request.getAttribute("login") == "failed") { %>
<script>
    alert("密码或者用户名错误！");
</script>
<%}%>
</body>
</html>
