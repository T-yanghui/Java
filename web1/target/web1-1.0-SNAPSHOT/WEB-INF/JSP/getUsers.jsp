<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>管理用户</title>
</head>
<body>
<script type="text/javascript">
<%
    if(request.getAttribute("users") == null){
%>
    alert("无用户");
<%
    }
%>
</script>
<table border="1px">
        <tr>
            <td>用户名</td>
            <td>密码</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.user_name}</td>
                <td>${user.password}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/updateRoles?user_id=${user.user_id}&user_name=${user.user_name}">
                        为用户授权角色
                    </a>
                    <a href="${pageContext.request.contextPath}/updateUsers?user_name=${user.user_name}&update=1">修改用户</a>
                    <a href="${pageContext.request.contextPath}/updateUsers?user_name=${user.user_name}&delete=true">删除用户</a>
                </td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>