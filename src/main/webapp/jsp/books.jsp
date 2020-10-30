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
<form method="get" action="<%=request.getContextPath()%>/book">
    <input type="text" value="ISBN" name="isbn">
    <input type="text" value="Name" name="name">
    <input type="text" value="Author" name="author">
    <input type="text" value="Genre" name="genre">
    <input type="text" value="Year" name="year">
    <input type="text" value="Copies" name="copy">
    <input type="submit" name="act" value="add">
</form>
<table>
    <thead>
    <tr>
        <th>ISBN</th>
        <th>Name</th>
        <th>Author</th>
        <th>Genre</th>
        <th>Year</th>
        <th>Copies</th>
        <%
            if(session.getAttribute("password")!=null){
        %>
        <th>Change</th>
        <th>Remove</th>
        <%
            }
        %>
    </tr>
    </thead>
    <tbody>
        <tr>
            <c:set var="books" value='${requestScope["books"]}' />

            <c:forEach items="${books}" var="book">
                <td>${book.isbn}</td>
                <td>${book.name}</td>
                <td>${book.author}</td>
                <td>${book.genre}</td>
                <td>${book.year}</td>
                <td>${book.copy}</td>
                <%
                    if(session.getAttribute("password")!=null){
                %>
                <td><a id="change">Change</a></td>
                <form id="a" method="get" action="<%=request.getContextPath()%>/book">
                    <input type="url" name="isbn" value="${book.isbn}">
                    <input name="name" value="${book.name}" type="text">
                    <input name="author" value="${book.author}" type="text">
                    <input name="genre" value="${book.genre}" type="text">
                    <input name="year" value="${book.year}" type="text">
                    <input name="copy" value="${book.copy}" type="text">
                    <input type="submit" name="act" value="change">
                </form>
                <td><a id="r" href="<%=request.getContextPath()%>/book?act=remove&isbn=${book.isbn}">remove</a></td>
                <%
                    }
                %>
            </c:forEach>
        </tr>
    </tbody>
</table>

</body>
</html>
