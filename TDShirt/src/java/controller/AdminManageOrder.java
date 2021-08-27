/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OrderDAO;
import dao.TrackItemDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TrackItem;

/**
 *
 * @author ThaiDuong
 */
public class AdminManageOrder extends HttpServlet {

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
            // Show all orders
            ArrayList<TrackItem> trackItems = new ArrayList<>();
            TrackItemDAO trackItemDAO = new TrackItemDAO();
            trackItems = trackItemDAO.getAll();

            request.setAttribute("trackItems", trackItems);
        } catch (Exception e) {
        }
        request.getServletContext().getRequestDispatcher("/adminManageOrder.jsp").forward(request, response);
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
            // Get All Orders
            ArrayList<TrackItem> trackItems = new ArrayList<>();
            TrackItemDAO trackItemDAO = new TrackItemDAO();
            trackItems = trackItemDAO.getAll();
            
            // Loop through each order, if it's checked and 'shipper' or 'processing'
            // Update status
            OrderDAO orderDAO = new OrderDAO();
            for (TrackItem ti : trackItems) {
                if (request.getParameter(String.valueOf(ti.getOrderID())) != null) {
                    if (request.getParameter("shipped") != null) {
                        orderDAO.updateStatus(ti.getOrderID(), 2);
                    } else if (request.getParameter("processing") != null) {
                        orderDAO.updateStatus(ti.getOrderID(), 1);
                    }
                }
            }
            // Re-get the orders
            trackItems = trackItemDAO.getAll();
            
            // Reassign to show the alert and orders
            request.setAttribute("updated", true);
            request.setAttribute("trackItems", trackItems);
            request.getServletContext().getRequestDispatcher("/adminManageOrder.jsp").forward(request, response);
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
