/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProductDAO;
import dao.ProductSizeDAO;
import dao.SizeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Size;

/**
 *
 * @author ThaiDuong
 */
public class AdminAddProduct extends HttpServlet {

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
            // Get All Sizes
            ArrayList<Size> sizeList = new ArrayList<>();
            SizeDAO sizeDAO = new SizeDAO();
            sizeList = sizeDAO.getAllSizes();
            request.setAttribute("sizeList", sizeList);

        } catch (Exception e) {
        }
        request.getServletContext().getRequestDispatcher("/adminAddProduct.jsp").forward(request, response);
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
            String productName = request.getParameter("productName");
            String description = request.getParameter("description");
            String img = request.getParameter("img");
            float price = Float.parseFloat(request.getParameter("price"));
            int availability = Integer.parseInt(request.getParameter("availability"));
            ArrayList<Integer> sizes = new ArrayList<>();
            
            // Get All Sizes
            ArrayList<Size> sizeList = new ArrayList<>();
            SizeDAO sizeDAO = new SizeDAO();
            sizeList = sizeDAO.getAllSizes();
            
            // If checked in request, add to sizes
            for (Size s : sizeList) {
                if (request.getParameter(s.getSize()) != null) {
                    sizes.add(s.getSizeID());
                }
            }
            
            // Insert new Product
            ProductDAO productDAO = new ProductDAO();
            productDAO.addProduct(productName, description, img, price, availability);
            // Get Latest ID of Product table
            int productID = productDAO.getLatestID();
            
            // For each item in sizes, add new
            ProductSizeDAO productSizeDAO = new ProductSizeDAO();
            for (Integer i : sizes) {
                productSizeDAO.add(productID, i);
            }
            request.setAttribute("added", true);
            request.getServletContext().getRequestDispatcher("/adminAddProduct.jsp").forward(request, response);
        } catch (Exception e) {
        }
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
