/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.MyUtils;

/**
 *
 * @author ThaiDuong
 */
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Type cast
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        String servletPath = req.getServletPath();
        
        if (servletPath.equalsIgnoreCase("/admin/login")) {
            if (MyUtils.checkLogin(req)) {
                res.sendRedirect("manage-product");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            if (MyUtils.checkLogin(req)) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect("/TDShirt/admin/login");
                        
            }
        }
    }

    @Override
    public void destroy() {
        
    }
    
}
