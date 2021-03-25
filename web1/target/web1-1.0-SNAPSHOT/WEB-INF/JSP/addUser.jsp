<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>添加用户</title>
    <style type="text/css">
        input:invalid {
            border: 2px dashed olivedrab;
        }

        input:valid {
            border: 2px solid #000000;
        }
    </style>

</head>
<body>
<form action="addUser" method="post" >
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username" required minlength="1" maxlength="10"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="password" required minlength="6" maxlength="15"></td>
        </tr>
        <tr>
            <td><input type="submit" value="添加用户"></td>
            <td><input type="reset" value="重置"></td>
        </tr>
    </table>
</form>
</body>
<%
    String registerInfo = (String)request.getAttribute("registerInfo");         // 获取错误属性
    if(registerInfo != null) {
%>
<%--               注册结果弹窗提示                      --%>
<script type="text/javascript" language="javascript">
<%
    if(registerInfo.equals("true")){
%>
    alert("注册成功！");
    window.location='login' ;
    <%
    }
    %>
    <%
    if(registerInfo.equals("false")) {
    %>
    alert("注册失败(用户名重复)！");
    window.location = "addUser";
    <%
    }%>
</script>
<%
    }
%>
</html>