<%-- 
    Document   : adminEditProduct
    Created on : Aug 24, 2021, 1:09:46 PM
    Author     : ThaiDuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("pageTitle", "Edit Product - TDShirt");%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="_adminHeader.jsp" %>
<h1 class="mb-5">Edit Product</h1>
<div class="alert alert-success ${requestScope.edited == null ? 'd-none' : ''}">
    Updated new product successfully!
</div>
<form method="POST">
    <div class="mb-2">
        <label class="form-label">Product Name</label>
        <input type="text" class="form-control" name="productName" value="${requestScope.product.name}" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Description</label>
        <textarea class="form-control" name="description" rows="3" required>${requestScope.product.description}</textarea>
    </div>
    <div class="mb-2">
        <label class="form-label">Image URL</label>
        <input type="text" class="form-control" value="${requestScope.product.img}" name="img" required>
    </div>
    <div class="mb-2">
        <label class="form-label">Price</label>
        <input type="number" min="0" step="0.01" value="${requestScope.product.price}" class="form-control" name="price" required>
    </div>
    <div class="mb-2">
        <label class="form-label">Availability</label>
        <input type="number" class="form-control" min="0" value="${requestScope.product.availability}" step="1" name="availability" required>
    </div>
    <p class="mb-1">Available sizes: </p>
    <c:forEach items="${requestScope.sizeList}" var="size">
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" id="${size.size}" name="${size.size}" value="${size.sizeID}" <c:if test="${fn:contains(requestScope.sizeIDList, size.sizeID)}">
                   checked
                </c:if>>

            <label class="form-check-label" for="${size.size}">${size.size}</label>
        </div>
    </c:forEach>
    <br>
    <button type="submit" class="btn btn-primary mt-3">Update Product</button>
</form>

<%@ include file="_adminFooter.jsp" %>
