<%-- 
    Document   : adminManageOrder
    Created on : Aug 24, 2021, 2:44:07 PM
    Author     : ThaiDuong
--%>

<% request.setAttribute("pageTitle", "Manage Order - TDShirt");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="_adminHeader.jsp" %>
<div class="alert alert-success ${requestScope.updated == null ? 'd-none' : ''}">
    Updated status successfully!
</div>
            <h1 class="mb-5">Manage Order</h1>
            <form method="POST">
            <button type="submit" class="btn btn-success" name="shipped" value="true">Mark As Shipped</button>
            <button type="submit" class="btn btn-primary" name="processing" value="true">Mark As Processing</button>
            <table class="table mt-3">
                <thead>
                    <tr>
                        <th></th>
                        <th scope="col">Order ID</th>
                        <th scope="col">Date</th>
                        <th scope="col">Customer Name</th>
                        <th scope="col">Address</th>
                        <th scope="col">Status</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                
                    <c:forEach items="${requestScope.trackItems}" var="trackItem">
                        <tr>
                            <td><input type="checkbox" name="${trackItem.orderID}" value="true" class="form-check-input" /></td>
                        <td>${trackItem.orderID}</td>
                        <td><fmt:formatDate pattern="dd-MM-yyyy" value="${trackItem.orderDate}" /></td>
                        <td>${trackItem.fullName}</td>
                        <td>${trackItem.address}</td>
                        <td>${trackItem.status}</td>
                        <td><a href="manage-order/view-details?id=${trackItem.orderID}" class="btn btn-secondary">View Details</a></td>
                    </tr>
                    </c:forEach>
                    
                    
                </tbody>
            </table>
            </form>
        
<%@ include file="_adminFooter.jsp" %>
