<%-- 
    Document   : productView
    Created on : Aug 23, 2021, 10:18:41 AM
    Author     : ThaiDuong
--%>

<%@page import="model.Product"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="errorPage.jsp" %>  
<% Product rProduct = (Product)request.getAttribute("rProduct"); %>
<% request.setAttribute("pageTitle", rProduct.getName());%>
<%@ include file="_header.jsp" %>
<%@ include file="_navbar.jsp" %>
<div class="container">
    <div class="alert alert-success alert-dismissible fade show mt-3 ${sessionScope.added == null ? 'd-none' : ''}" role="alert">
        <% if (session.getAttribute("added") != null) session.setAttribute("added", null); %>
        <strong>Added to cart!</strong> Continue shopping or <a href="cart" class="alert-link">Click Here</a> to view your cart and proceed checkout.
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <div class="row">
        <div class="col-md-6">
            <img src="${requestScope.rProduct.img}" class="img-fluid" />
        </div>
        <div class="col-md-6">
            <h1 class="mt-md-5 fs-2">${requestScope.rProduct.name}</h1>
            <p class="text-danger fw-bold fs-4">$${requestScope.rProduct.price}</p>
            <span class="text-muted mb-3 d-block">In stock: ${requestScope.rProduct.availability}</span>
            <form method="POST">
                <p class="mb-0">Size:</p>
                <c:forEach items="${requestScope.sizeList}" var="size">
                    <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="size" id="${size.size}" value="${size.sizeID}" required>
                    <label class="form-check-label" for="${size.size}">${size.size}</label>
                </div>
                </c:forEach>
                <div class="form-group mt-3">
                    <label for="quantity">Quantity: </label>
                    <input type="number" name="quantity" min="1" max="${requestScope.rProduct.availability}" step="1" value="1" class="form-control w-25 text-center" id="quantity" required />
                </div>
                <input type="hidden" name="id" value="${param.id}" />

                <input type="submit" value="Add To Cart" class="btn btn-primary btn-lg mt-3 w-50" ${requestScope.rProduct.availability <= 0 ? 'disabled' : ''} />
            </form>
            <hr>
            <h3>Description</h3>
            <p>${requestScope.rProduct.description}</p>
        </div>
    </div>
</div>

<%@ include file="_footer.jsp" %>