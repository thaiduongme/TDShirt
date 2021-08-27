<%-- 
    Document   : checkoutView
    Created on : Aug 23, 2021, 2:45:37 PM
    Author     : ThaiDuong
--%>

<%@page import="model.CartItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<% request.setAttribute("pageTitle", "View Cart - TDShirt");%>
<%@ include file="_header.jsp" %>
<%@ include file="_navbar.jsp" %>
<div class="container">
    <% if (session.getAttribute("cart") == null) {
            response.sendRedirect("home"); %>

    <% } else if (((ArrayList<CartItem>) session.getAttribute("cart")).isEmpty()) {
        response.sendRedirect("home"); %>
    <% } else { %>
    <h1 class="mt-3">Checkout</h1>
    <div class="row">
        <div class="col-md-4">
            <ul class="list-group list-group mt-3">
                <c:set var="total" scope="page" value="0.0"></c:set>
                <c:forEach items="${sessionScope.cart}" var="cartItem">
                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="ms-2 me-auto">
                        <div class="fw-bold">${cartItem.product.name}</div>
                        Size: ${cartItem.size.size} &nbsp; Quantity: ${cartItem.quantity}
                    </div>
                        <span class="badge bg-light text-dark rounded-pill"><fmt:formatNumber value="${cartItem.product.price * cartItem.quantity}" type="currency" /></span>
                </li>
                <c:set var="total" scope="page" value="${total + cartItem.product.price * cartItem.quantity}"></c:set>
                </c:forEach>
                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="ms-2 me-auto">
                        <div class="fw-bold">Total</div>
                    </div>
                    <span class="badge bg-primary rounded-pill"><fmt:formatNumber value="${total}" type="currency"></fmt:formatNumber></span>
                </li>
            </ul>
        </div>
        <div class="col-md-8">
            <form method="POST">
                <div class="mb-2">
                    <label class="form-label">Full Name</label>
                    <input type="text" class="form-control" name="fullname" required>
                </div>
                <div class="mb-2">
                    <label class="form-label">Address</label>
                    <input type="text" class="form-control" name="address" required>
                </div>
                <div class="mb-2">
                    <label class="form-label">Email</label>
                    <input type="email" class="form-control" name="email" required>
                </div>
                <div class="mb-2">
                    <label class="form-label">Phone</label>
                    <input type="tel" minlength="10" maxlength="11" class="form-control" name="phone" required>
                </div>
                <button type="submit" class="btn btn-primary">Complete Order</button>
            </form>
        </div>
    </div>
    <% }%>

</div>


<%@ include file="_footer.jsp" %>