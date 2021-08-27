/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import model.Admin;
import dao.AdminDAO;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ThaiDuong
 */
public class MyUtils {

    public static boolean checkLogin(HttpServletRequest req) {
        try {
            // Check if Admin object is in Session
            if (req.getSession().getAttribute("admin") != null) {
                return true;
            }

            // Else, check cookie
            Cookie cookies[] = req.getCookies();
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equalsIgnoreCase("username")) {
                        Admin cAdmin = null;
                        AdminDAO adminDAO = new AdminDAO();
                        cAdmin = adminDAO.getAdmin(cookies[i].getValue());
                        if (cAdmin != null) {
                            // Store Admin object to session
                            req.getSession().setAttribute("admin", cAdmin);
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static void logOut(HttpServletRequest req, HttpServletResponse res) {
        // Set admin object = null in session
        req.getSession().setAttribute("admin", null);

        // Make cookie expires
        Cookie cUsername = new Cookie("username", "empty");
        cUsername.setMaxAge(0);
        res.addCookie(cUsername);
    }
}
