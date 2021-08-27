/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Admin;
/**
 *
 * @author ThaiDuong
 */
public class AdminDAO extends DBContext {
    public Admin getAdmin(String username, String password) throws SQLException {
        String sql = "SELECT * FROM Admin WHERE Username=? AND Password=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            return new Admin(rs.getString("Username"), rs.getString("Password"), rs.getString("Fullname"));
            
        }
        
        return null;
    }
    public Admin getAdmin(String username) throws SQLException {
        String sql = "SELECT * FROM Admin WHERE Username=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            return new Admin(rs.getString("Username"), rs.getString("Password"), rs.getString("Fullname"));
            
        }
        
        return null;
    }
}
