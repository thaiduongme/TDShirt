/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AdminDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Admin;

/**
 *
 * @author ThaiDuong
 */
@WebServlet(name = "AdminLogin", urlPatterns = {"/admin/login"})
public class AdminLogin extends HttpServlet {

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
        request.getServletContext().getRequestDispatcher("/adminLoginView.jsp").forward(request, response);
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
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            boolean remember = false;
            if (request.getParameter("remember") != null) remember = true;
            Admin cAdmin = null;
            AdminDAO adminDAO = new AdminDAO();
            cAdmin = adminDAO.getAdmin(username, password);
            
            // Login Successfully
            if (cAdmin != null) {
                // Store Admin Object to Session
                request.getSession().setAttribute("admin", cAdmin);
                
                // If remember me, save username to cookie
                if (remember) {
                    Cookie cUsername = new Cookie("username", username);
                    cUsername.setMaxAge(60 * 60 * 24);  // Save for 24hrs
                    response.addCookie(cUsername);
                }
                
                // Redirect to admin/manage-product
                response.sendRedirect("manage-product");
            }
            // Login Failed
            else {
                request.setAttribute("loginFailed", true);
                request.getServletContext().getRequestDispatcher("/adminLoginView.jsp").forward(request, response);
            }
            
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
