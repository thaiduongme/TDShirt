<%-- 
    Document   : homePageView
    Created on : Aug 21, 2021, 11:50:46 AM
    Author     : ThaiDuong
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setAttribute("pageTitle", "Welcome to TDShirt");%>
<%@ include file="_header.jsp" %>
<%@ include file="_navbar.jsp" %>
<div class="container">
    <!-- SLIDE SHOW -->
    <div id="carouselExampleIndicators" class="carousel slide rounded mt-4" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="./img/banner1.jpg" class="d-block w-100" alt="...">
            </div>
            <div class="carousel-item">
                <img src="./img/banner2.jpg" class="d-block w-100" alt="...">
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
    
    <!-- BEST SELLING PRODUCTS -->
    <div class="best-sellling mt-5">
        <h2 class="text-center mb-4">Best Selling TShirts</h2>
        <div class="row">
            <c:forEach items="${requestScope.bestSellingProducts}" var="product">
               <div class="col-sm-6 col-md-4">
                <div class="product-small-box border p-3 shadow-sm rounded" onclick="window.location.href='product?id=${product.productID}';">
                    <img src="${product.img}" class="d-block img-fluid" />
                    <span class="product-smallbox-title fw-normal">${product.name}</span><br />
                    <span class="product-smallbox-price text-danger fw-bold">$${product.price}</span>
                </div>
            </div> 
            </c:forEach>
        </div>
    </div>

    <!-- LATEST PRODUCTS -->
    <div class="best-sellling mt-5">
        <h2 class="text-center mb-4">Latest TShirts</h2>
        <div class="row">
            <c:forEach items="${requestScope.latestProducts}" var="product">
               <div class="col-sm-6 col-md-4 mt-3">
                <div class="product-small-box border p-3 shadow-sm rounded" onclick="window.location.href='product?id=${product.productID}';">
                    <img src="${product.img}" class="d-block img-fluid" />
                    <span class="product-smallbox-title fw-normal">${product.name}</span><br />
                    <span class="product-smallbox-price text-danger fw-bold">$${product.price}</span>
                </div>
            </div> 
            </c:forEach>
        </div>
    </div>
</div>

<%@ include file="_footer.jsp" %>