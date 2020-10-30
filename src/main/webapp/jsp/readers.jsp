<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 25.10.2020
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        body{
            background-image: url("${pageContext.request.contextPath}/jsp/img/back.jpg");
        }
    </style>
</head>
<body>
<%@include file="header.jsp"%>
<form method="get" action="<%=request.getContextPath()%>/reader">
    <input type="text" value="id" name="id">
    <input type="text" value="First name" name="fname">
    <input type="text" value="Last name" name="lname">
    <input type="text" value="Registration date" name="reg_date">
    <input type="submit" name="act" value="add">
</form>
<table>
    <thead>
    <tr>
        <th>id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Registration date</th>
        <th>Change</th>
        <th>Remove</th>
        <th>Borrowed books</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <c:set var="readers" value='${requestScope["readers"]}' />

        <c:forEach items="${readers}" var="reader">
            <td>${reader.id}</td>
            <td>${reader.fname}</td>
            <td>${reader.lname}</td>
            <td>${reader.reg_date}</td>
            <td><a id="change">Change</a></td>
            <form style="display: none;" id="a" method="get" action="<%=request.getContextPath()%>/reader">
                <input name="id" value="${reader.id}">
                <input name="fname" value="First name" type="text">
                <input name="lname" value="Last name" type="text">
                <input name="reg_date" value="Registration date" type="text">
                <input type="submit" name="act" value="change">
            </form>
            <td><a id="r" href="<%=request.getContextPath()%>/reader?act=remove&id=${reader.id}">remove</a></td>
            <td><a href="<%=request.getContextPath()%>/borrowedBook?id=${reader.id}&act=frommain">Open</a></td><br>
        </c:forEach>
    </tr>
    </tbody>
</table>
</body>
</html>
