<%-- 
    Document   : orderCompleteView
    Created on : Aug 23, 2021, 8:59:28 PM
    Author     : ThaiDuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("pageTitle", "View Cart - TDShirt");%>
<%@ include file="_header.jsp" %>
<%@ include file="_navbar.jsp" %>
<div class="container">
    <h1 class="mt-3">Order #${requestScope.orderID}</h1>
    <p>Thanks for purchasing, Please remember Order ID: <strong>${requestScope.orderID}</strong> and Email: <strong>${requestScope.email}</strong> or Phone: <strong>${requestScope.phone}</strong> in order to track in the future.</p>
</div>

<%@ include file="_footer.jsp" %>