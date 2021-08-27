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
public class AdminEditProduct extends HttpServlet {


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
            int productID = Integer.parseInt(request.getParameter("id"));
            ArrayList<Size> availableSizeList = new ArrayList<>();
            ArrayList<Integer> sizeIDList = new ArrayList<>();
            model.Product product = new model.Product();
            ProductDAO productDAO = new ProductDAO();
            product = productDAO.getProductByID(productID);
            // Get All Sizes
            ArrayList<Size> sizeList = new ArrayList<>();
            SizeDAO sizeDAO = new SizeDAO();
            sizeList = sizeDAO.getAllSizes();
            
            // Get available sizes of that product
            availableSizeList = sizeDAO.getSizesByProductID(productID);
            for (Size s : availableSizeList) {
                sizeIDList.add(s.getSizeID());
            }
            request.setAttribute("sizeIDList", sizeIDList);
            request.setAttribute("sizeList", sizeList);
            request.setAttribute("product", product);
        } catch (Exception e) {}
        request.getServletContext().getRequestDispatcher("/adminEditProduct.jsp").forward(request, response);
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
            // Get values from request
            int productID = Integer.parseInt(request.getParameter("id"));
            String productName = request.getParameter("productName");
            String description = request.getParameter("description");
            String img = request.getParameter("img");
            float price = Float.parseFloat(request.getParameter("price"));
            int availability = Integer.parseInt(request.getParameter("availability"));
            
            // Update Product By ID
            ProductDAO productDAO = new ProductDAO();
            productDAO.updateProduct(productID, productName, description, img, price, availability);
            
            
            ArrayList<Integer> checkedSizes = new ArrayList<>();
            ArrayList<Integer> uncheckedSizes = new ArrayList<>();
            
            // Get All Sizes
            ArrayList<Size> sizeList = new ArrayList<>();
            SizeDAO sizeDAO = new SizeDAO();
            sizeList = sizeDAO.getAllSizes();
            
            // If checked in add to checkedSizes, otherwise - uncheckedSizes
            for (Size s : sizeList) {
                if (request.getParameter(s.getSize()) != null) {
                    checkedSizes.add(s.getSizeID());
                } else {
                    uncheckedSizes.add(s.getSizeID());
                }
            }
            ProductSizeDAO productSizeDAO = new ProductSizeDAO();
            
            for (Integer i : checkedSizes) {
                productSizeDAO.add(productID, i);
            }
            for (Integer i : uncheckedSizes) {
                productSizeDAO.delete(productID, i);
            }
            // Get product to redisplay it on edit page
            model.Product product = productDAO.getProductByID(productID);
            // Get All Sizes
            ArrayList<Size> availableSizeList = new ArrayList<>();
            ArrayList<Integer> sizeIDList = new ArrayList<>();
            
            // Get available sizes of that product
            availableSizeList = sizeDAO.getSizesByProductID(productID);
            for (Size s : availableSizeList) {
                sizeIDList.add(s.getSizeID());
            }
            request.setAttribute("sizeIDList", sizeIDList);
            request.setAttribute("sizeList", sizeList);
            request.setAttribute("edited", true);
            request.setAttribute("product", product);
            request.getServletContext().getRequestDispatcher("/adminEditProduct.jsp").forward(request, response);
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
