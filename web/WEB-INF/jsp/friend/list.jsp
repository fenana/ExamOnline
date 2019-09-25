<%--
  Created by IntelliJ IDEA.
  User: feng
  Date: 2019/9/23
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>资讯列表页</title>
    <%@ include file="../utils.jsp"%>
    <link rel="stylesheet" href="${basePath}/scripts/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <script src="${basePath}/scripts/jquery/2.1.1/jquery.min.js"></script>
    <script src="${basePath}/scripts/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>

<h1>资讯列表页</h1>

<table border="1" align="center" width="600">
    <tr>
        <td colspan="3" align="right"><a href="add">添加</a></td>
    </tr>
    <tr>
        <td>编号</td>
        <td>姓名</td>
        <td>座右铭</td>
    </tr>
    <c:forEach items="${items}" var="row">
        <tr style="cursor: pointer;">
            <td>${row.id}</td>
            <td title="${row.name}">${row.motto}</td>
            <td><a href="modify?id=${row.id}">编辑</a>
                <a href="remove?id=${row.id}" onclick="return confirm('你确认删除吗？');">删除</a></td>

        </tr>
    </c:forEach>

</table>

</body>
</html>
