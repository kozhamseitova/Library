<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 25.10.2020
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body{
            background-image: url("${pageContext.request.contextPath}/jsp/img/back.jpg");
        }
        img{
            width: 33%;
        }
    </style>
</head>
<body>
<%
    if(session.getAttribute("password")==null){
%>
<div style="height: 10em;position: relative; ">
    <div style="margin: 0; position: absolute; top: 50%; left: 50%; margin-right: -50%; transform: translate(-50%, -50%)">
        <h1 style=" font-family: Arial Black, Gadget, sans-serif; font-size: 32px; letter-spacing: -2.2px;word-spacing: -0.2px;color: #81594A;font-weight: normal; text-decoration: none solid rgb(68, 68, 68);font-style: normal; font-variant: small-caps; text-transform: none;">Hello, enter password to confirm that you are a library employee</h1><br>
        <form method="get" action="<%=request.getContextPath()%>/signin">
            <input type="password" name="password" placeholder="Password">
            <input type="submit">
        </form>
    </div>
</div>
<%
    }
%>
<div style="width: 70%; margin-left: 15%;">
    <a href="<%=request.getContextPath()%>/book?act=frommain"><img src="<%=request.getContextPath()%>/jsp/img/books.png"></a>
    <%
        if(session.getAttribute("password")!=null){
    %>
    <a href="<%=request.getContextPath()%>/reader?act=frommain"><img src="<%=request.getContextPath()%>/jsp/img/readers.png"></a>
    <a href="<%=request.getContextPath()%>/logout"><img src="<%=request.getContextPath()%>/jsp/img/logout.png"></a>
    <%
        }
    %>
</div>
</body>
</html>
