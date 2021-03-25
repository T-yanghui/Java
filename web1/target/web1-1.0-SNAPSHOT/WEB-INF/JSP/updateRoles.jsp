<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yang
  Date: 2021/3/7
  Time: 上午11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户角色管理</title>
</head>
<body>
<table border="1px">
    <tr>
        <td>当前用户名称</td>
        <td>${user_name}</td>
    </tr>

    <tr>
        <td>当前用户所拥有的角色</td>
        <td>
            <c:forEach items="${roles}" var="userRole">
                ${userRole.role_name}
            </c:forEach>
        </td>
    </tr>

    <tr>
        <td>当前系统所拥有的角色</td>
        <td>
            <form method="post" action="${pageContext.request.contextPath}/updateRoles">

                <%--要为用户添加角色，需要知道是哪一个用户，通过hidden传递过去用户的id--%>
                <input type="hidden" name="user_id" value="${user_id}">
                <input type="hidden" name="user_name" value="${user_name}">
                <c:forEach items="${allroles}" var="role">
                    <input type="checkbox" name="role_id" value="${role.role_id}">${role.role_name}
                </c:forEach>

                <input type="submit" value="添加角色！">
            </form>
        </td>
    </tr>

</table>
</body>
</html>
