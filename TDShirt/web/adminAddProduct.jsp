<%-- 
    Document   : adminAddProduct
    Created on : Aug 24, 2021, 11:32:09 AM
    Author     : ThaiDuong
--%>

<% request.setAttribute("pageTitle", "Add Product - TDShirt");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="_adminHeader.jsp" %>
<h1 class="mb-5">Add Product</h1>
<div class="alert alert-success ${requestScope.added == null ? 'd-none' : ''}">
    Added new product successfully!
</div>
<form method="POST">
    <div class="mb-2">
        <label class="form-label">Product Name</label>
        <input type="text" class="form-control" name="productName" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Description</label>
        <textarea class="form-control" name="description" rows="3" required></textarea>
    </div>
    <div class="mb-2">
        <label class="form-label">Image URL</label>
        <input type="text" class="form-control" name="img" required>
    </div>
    <div class="mb-2">
        <label class="form-label">Price</label>
        <input type="number" min="0" step="0.01" class="form-control" name="price" required>
    </div>
    <div class="mb-2">
        <label class="form-label">Availability</label>
        <input type="number" class="form-control" min="0" step="1" name="availability" required>
    </div>
    <p class="mb-1">Available sizes: </p>
    <c:forEach items="${requestScope.sizeList}" var="size">
        <div class="form-check form-check-inline">
        <input class="form-check-input" type="checkbox" id="${size.size}" name="${size.size}" value="${size.sizeID}" checked>
        <label class="form-check-label" for="${size.size}">${size.size}</label>
    </div>
    </c:forEach>
    <br>
    <button type="submit" class="btn btn-primary mt-3">Add Product</button>
</form>

<%@ include file="_adminFooter.jsp" %>