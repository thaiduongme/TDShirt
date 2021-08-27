/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TrackItemDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TrackItem;

/**
 *
 * @author ThaiDuong
 */
public class Track extends HttpServlet {


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
        request.getServletContext().getRequestDispatcher("/trackOrderView.jsp").forward(request, response);
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
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        String emailOrPhone = request.getParameter("emailOrPhone");
        TrackItem trackItem = new TrackItem();
        
        
        TrackItemDAO trackItemDAO = new TrackItemDAO();
        try {
            trackItem = trackItemDAO.getTrackItem(orderID, emailOrPhone);
            request.setAttribute("trackItem", trackItem);
            if (trackItem == null) {request.setAttribute("trackFlag", true);} else {request.setAttribute("trackFlag", false);}
        } catch (SQLException ex) {
            Logger.getLogger(Track.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (Exception e) {}
        
        
        request.getServletContext().getRequestDispatcher("/trackOrderView.jsp").forward(request, response);
        
        
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
