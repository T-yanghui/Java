<%--
  Created by IntelliJ IDEA.
  User: yang
  Date: 2021/3/2
  Time: 下午10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户</title>
</head>
<body>
<form action="updateUsers?update=2" method="post" >
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="user_name" value=${user.user_name} readonly></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="password" value=${user.password} minlength="6" maxlength="15"></td>
        </tr>
        <tr>
            <td><input type="hidden" name="user_id" value=${user.user_id}></td>
        </tr>
        <tr>
            <td><input type="submit" value="update"></td>
            <td><input type="reset" value="重置"></td>
        </tr>
    </table>
</form>
<%
    if ((String)request.getAttribute("update") == "success") { %>
<script>
    alert("修改成功！");
    window.location="login";
</script>
<%}%>
</body>
</html>
