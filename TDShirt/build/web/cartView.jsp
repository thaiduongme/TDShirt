<%-- 
    Document   : cartView
    Created on : Aug 23, 2021, 1:11:25 PM
    Author     : ThaiDuong
--%>

<%@page import="model.CartItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Product"%>
<%@page import="dao.ProductDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("pageTitle", "View Cart - TDShirt");%>
<%@ include file="_header.jsp" %>
<%@ include file="_navbar.jsp" %>

<div class="container">
    <h1 class="mt-3">View Cart</h1>
    <% if (session.getAttribute("cart") == null) { %>
    <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
        <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
            <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
        </symbol>
    </svg>
    <div class="alert alert-warning d-flex align-items-center mt-3" role="alert">
        <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Warning:"><use xlink:href="#exclamation-triangle-fill"/></svg>
        <div>
            Your cart is empty!
        </div>
    </div>
    <% } else if (((ArrayList<CartItem>) session.getAttribute("cart")).isEmpty()) { %>
    <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
        <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
            <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
        </symbol>
    </svg>
    <div class="alert alert-warning d-flex align-items-center mt-3" role="alert">
        <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Warning:"><use xlink:href="#exclamation-triangle-fill"/></svg>
        <div>
            Your cart is empty!
        </div>
    </div>
    <% } else { %>
    <table class="table">
        <thead>
            <tr>
                <th scope="col">Thumbnail</th>
                <th scope="col">Name</th>
                <th scope="col">Size</th>
                <th scope="col">Price</th>
                <th scope="col">Quantity</th>
                <th scope="col">Total</th>
                <th scope="col">Action</th>
            </tr>
        </thead>
        <tbody>
            <c:set var="subtotal" scope="page" value="0.0" />
            <c:forEach items="${sessionScope.cart}" var="cartItem">
                <tr>
                    <td><img src="${cartItem.product.img}" style="width: 100px" /></td>
                    <td>${cartItem.product.name}</td>
                    <td>${cartItem.size.size}</td>
                    <td>$${cartItem.product.price}</td>
                    <td>${cartItem.quantity}</td>
                    <td><fmt:formatNumber value="${cartItem.product.price * cartItem.quantity}" 
                                      type="currency"/></td>
                        <c:set var="subtotal" scope="page" value="${subtotal + cartItem.product.price * cartItem.quantity}" />
                    <td><a href="?removeProductID=${cartItem.product.productID}&sizeID=${cartItem.size.sizeID}" class="btn btn-outline-danger">Remove</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <h2 class="text-end">Subtotal: <fmt:formatNumber value="${subtotal}" type="currency" /></h2>
    <div class="text-end">
        <a href="checkout" class="btn btn-primary btn-lg">Check out</a>
    </div>
    <% }%>
</div>



<%@ include file="_footer.jsp" %>