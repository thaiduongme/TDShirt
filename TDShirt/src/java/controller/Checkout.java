/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CustomerDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CartItem;

/**
 *
 * @author ThaiDuong
 */
public class Checkout extends HttpServlet {


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/checkoutView.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        String rFullname = request.getParameter("fullname");
        String rAddress = request.getParameter("address");
        String rEmail = request.getParameter("email");
        String rPhone = request.getParameter("phone");
        ArrayList<CartItem> cart = (ArrayList<CartItem>) request.getSession().getAttribute("cart");
        
        // Insert new Customer
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.addCustomer(rFullname, rAddress, rEmail, rPhone);
        // Get ID of that Customer
        int customerID = customerDAO.getLatestID();
        
        // Insert new Order
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.addOrder(customerID);
        // Get ID of that Order
        int orderID = orderDAO.getLatestID();
        
        // For each item in cart, Insert to Order Detail
        // Update new availability of that product
        ProductDAO productDAO = new ProductDAO();
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        for (CartItem ci : cart) {
            if (productDAO.getProductAvailability(ci.getProduct().getProductID()) - ci.getQuantity() >= 0) {
                orderDetailDAO.add(orderID, ci.getProduct().getProductID(), ci.getQuantity(), ci.getSize().getSizeID());
                productDAO.updateProductAvailability(ci.getProduct().getProductID(), productDAO.getProductAvailability(ci.getProduct().getProductID()) - ci.getQuantity());
                
            }
        }
        
        // Forward to order completed
        request.setAttribute("orderID", orderID);
        request.setAttribute("email", rEmail);
        request.setAttribute("phone", rPhone);
        
        // Clear the cart
        request.getSession().setAttribute("cart", null);
        
        request.getServletContext().getRequestDispatcher("/orderCompleteView.jsp").forward(request, response);
        
        
        
        } catch (Exception e) {}
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
