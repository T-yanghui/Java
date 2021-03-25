<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - message</title>
</head>
<body>
<%
    String message = (String) request.getAttribute("message");
%>
<h1><%= message %>
</h1>
<br/>
</body>
</html>