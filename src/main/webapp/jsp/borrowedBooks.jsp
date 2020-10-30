<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 28.10.2020
  Time: 17:41
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

<table>
    <thead>
    <tr>
        <th>ISBN</th>
        <th>Name</th>
        <th>Author</th>
        <th>Genre</th>
        <th>Year</th>
        <th>Borrow date</th>
        <th>Remove</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <c:set var="borrowedBooks" value='${requestScope["borrowedBooks"]}' />

        <c:forEach items="${borrowedBooks}" var="borrowedBook">
            <td>${borrowedBook.isbn}</td>
            <td>${borrowedBook.name}</td>
            <td>${borrowedBook.author}</td>
            <td>${borrowedBook.genre}</td>
            <td>${borrowedBook.year}</td>
            <td>${borrowedBook.borrow_date}</td>
            <td><a id="r" href="<%=request.getContextPath()%>/borrowedBook?act=remove&isbn=${borrowedBook.isbn}&id=${borrowedBook.id}">remove</a></td><br>
        </c:forEach>
    </tr>
    </tbody>
</table>
<h3 style="text-align: center">Add borrowed book</h3>
<form method="get" action="<%=request.getContextPath()%>/borrowedBook">
    <input type="text" value="ISBN" name="isbn">
    <input type="text" value="Borrow date" name="borrow_date">
    <input type="submit" name="act" value="add">
</form>
</body>
</html>
