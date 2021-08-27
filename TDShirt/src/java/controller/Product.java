/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ProductDAO;
import dao.SizeDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Size;
import model.CartItem;

/**
 *
 * @author ThaiDuong
 */
public class Product extends HttpServlet {

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
        try {
            model.Product rProduct = new model.Product();
            ArrayList<Size> sizeList = new ArrayList<>();
            
            int rID = Integer.parseInt(request.getParameter("id"));
            
            // Get Product By ID
            ProductDAO productDAO = new ProductDAO();
            rProduct = productDAO.getProductByID(rID);
            
            // Get available sizes of that product
            SizeDAO sizeDAO = new SizeDAO();
            sizeList = sizeDAO.getSizesByProductID(rID);
            
            request.setAttribute("rProduct", rProduct);
            request.setAttribute("sizeList", sizeList);

            request.getServletContext().getRequestDispatcher("/productView.jsp").forward(request, response);
        } catch (Exception e) {
        }

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
        ArrayList<CartItem> cart = new ArrayList<>();
        model.Product product = new model.Product();
        Size size = new Size();
        
        // Get values from request
        int productID = Integer.parseInt(request.getParameter("id"));
        int sizeID = Integer.parseInt(request.getParameter("size"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        
        // Convert to Product & Size Objects
        ProductDAO productDAO = new ProductDAO();
        SizeDAO sizeDAO = new SizeDAO();
        
        try {
            product = productDAO.getProductByID(productID);
            size = sizeDAO.getSizeByID(sizeID);
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        // Cart is empty
        if (request.getSession().getAttribute("cart") == null) {
            cart.add(new CartItem(product, size, quantity));
        }
        // Cart is NOT empty
        else {
            cart = (ArrayList<CartItem>) request.getSession().getAttribute("cart");
            boolean flag = false;
            
            // Check if productID & sizeID already in the cart
            // If yes, increase the quantity
            for (int i = 0; i < cart.size(); i++) {
                if (cart.get(i).getProduct().getProductID() == productID & cart.get(i).getSize().getSizeID() == size.getSizeID()) {
                    cart.get(i).setQuantity(cart.get(i).getQuantity() + quantity);
                    flag = true;
                }
            }
            
            // If productID & sizeID does not exist in the cart
            if (!flag) {
                cart.add(new CartItem(product, size, quantity));
            }
        }
        
        // Assign to Session
        request.getSession().setAttribute("cart", cart);
        request.getSession().setAttribute("added", true);
        
        // forward to 
        response.sendRedirect("product?id=" +productID);
        

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
