<%-- 
    Document   : errorPage
    Created on : Aug 24, 2021, 9:19:55 AM
    Author     : ThaiDuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("pageTitle", "404 Not Found - TDShirt");%>
<%@ include file="_header.jsp" %>
<%@ include file="_navbar.jsp" %>
<div class="container">
    <h1 class="mt-3">Uh oh, Something went wrong...</h1>
    <a class="btn btn-primary" href="home">Back Home</a>
</div>


<%@ include file="_footer.jsp" %>