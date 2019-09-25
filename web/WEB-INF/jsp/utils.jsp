<%--
  jsp页，工具页
  User: Gary
  Date: 2019/9/22
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    Integer port = request.getServerPort();
    String basePath = null;
    if (port == 80){
        basePath = request.getScheme() + "://" + request.getServerName() + path;
    }
    else{
        basePath = request.getScheme() + "://" + request.getServerName() + ":" + port + path;
    }
    pageContext.setAttribute("basePath", basePath);
%>
