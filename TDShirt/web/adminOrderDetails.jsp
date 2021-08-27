<%-- 
    Document   : adminOrderDetails
    Created on : Aug 24, 2021, 3:25:17 PM
    Author     : ThaiDuong
--%>

<% request.setAttribute("pageTitle", "Manage Order - TDShirt");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="_adminHeader.jsp" %>
<h1 class="mb-5">View Order #${param.id} Details</h1>

<h2>Customer Info</h2>
<table class="table mt-3">
    <thead>
        <tr>
            <th scope="col">Full Name</th>
            <th scope="col">Address</th>
            <th scope="col">Email</th>
            <th scope="col">Phone</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>${requestScope.customer.fullName}</td>
            <td>${requestScope.customer.address}</td>
            <td>${requestScope.customer.email}</td>
            <td>${requestScope.customer.phone}</td>
        </tr>


    </tbody>
</table>
<h2 class="mt-5">Ordered Products</h2>
<table class="table mt-3">
    <thead>
        <tr>
            <th scope="col">Thumbnail</th>
            <th scope="col">Name</th>
            <th scope="col">Size</th>
            <th scope="col">Price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Total</th>
        </tr>
    </thead>
    <tbody>
        <c:set var="subtotal" value="0.0" />
        <c:forEach items="${requestScope.displayOrderList}" var="displayOrder">
           <tr>
            <td><img src="${displayOrder.img}" style="width: 100px" /></td>
            <td>${displayOrder.name}</td>
            <td>${displayOrder.size}</td>
            <td>$${displayOrder.price}</td>
            <td>${displayOrder.quantity}</td>
            <td><fmt:formatNumber type="currency" value="${displayOrder.price * displayOrder.quantity}" /></td>
            <c:set var="subtotal" value="${subtotal + displayOrder.price * displayOrder.quantity}" />
        </tr> 
        </c:forEach>
        


    </tbody>
</table>
<h2 class="text-end">Subtotal: <fmt:formatNumber value="${subtotal}" type="currency" /></h2>

<%@ include file="_adminFooter.jsp" %>
