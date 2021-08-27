<%-- 
    Document   : _navbar
    Created on : Aug 21, 2021, 11:58:21 AM
    Author     : ThaiDuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- NAV MENU BAR -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container">
    <a class="navbar-brand" href="home"><i class="fas fa-tshirt text-primary"></i> TDShirt</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link <% if (request.getAttribute("javax.servlet.forward.request_uri").toString().contains("home")) {%>active<% } %>" aria-current="page" href="home">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">About Us</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#" >Contact Us</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Blogs</a>
        </li>
        <li class="nav-item">
          <a class="nav-link <% if (request.getAttribute("javax.servlet.forward.request_uri").toString().contains("track")) {%>active<% } %>" href="track">Track Order</a>
        </li>
        
      </ul>
      <form class="d-flex">
        <a class="btn btn-outline-primary" href="cart">View Cart</a>
      </form>
    </div>
  </div>
</nav>
