<%-- 
    Document   : trackOrderView
    Created on : Aug 23, 2021, 3:26:54 PM
    Author     : ThaiDuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("pageTitle", "Track Order - TDShirt");%>
<%@ page errorPage="errorPage.jsp" %> 
<%@ include file="_header.jsp" %>
<%@ include file="_navbar.jsp" %>
<div class="container">
    <h1 class="mt-3">Track your order</h1>
    <form method="POST">
        <div class="mb-2">
            <label class="form-label">Order ID</label>
            <input type="number" class="form-control" name="orderID" required>
        </div>
        <div class="mb-2">
            <label class="form-label">Email or Phone</label>
            <input type="text" class="form-control" name="emailOrPhone" required>
        </div>
        <button type="submit" class="btn btn-primary">Track</button>
    </form>
    <% if (request.getAttribute("trackFlag") == null) { %>

    <% } else if ((Boolean) request.getAttribute("trackFlag")) {
        request.setAttribute("trackFlag", false); %>
    <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
        <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
            <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
        </symbol>
    </svg>
    <div class="alert alert-warning d-flex align-items-center mt-3" role="alert">
        <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Warning:"><use xlink:href="#exclamation-triangle-fill"/></svg>
        <div>
            No data found!
        </div>
    </div>
    <% } else { %>
    <table class="table mt-3">
        <thead>
            <tr>
                <th scope="col">Order ID</th>
                <th scope="col">Full Name</th>
                <th scope="col">Address</th>
                <th scope="col">Order Date</th>
                <th scope="col">Status</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>${requestScope.trackItem.orderID}</td>
                <td>${requestScope.trackItem.fullName}</td>
                <td>${requestScope.trackItem.address}</td>
                <td>${requestScope.trackItem.orderDate}</td>
                <td>${requestScope.trackItem.status}</td>
            </tr>
        </tbody>
    </table>
    <% }%>
</div>

<%@ include file="_footer.jsp" %>