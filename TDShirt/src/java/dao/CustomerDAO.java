/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ThaiDuong
 */
public class CustomerDAO extends DBContext {
    public int getLatestID() throws SQLException {
        String sql = "SELECT TOP 1 CustomerID FROM Customer ORDER BY CustomerID DESC;";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            return rs.getInt("CustomerID");
        }
        return -1;
        
    }
    
    public void addCustomer(String fullName, String address, String email, String phone) throws SQLException {
        String sql = "INSERT INTO Customer (Fullname, Address, Email, Phone) VALUES (?, ?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, fullName);
        statement.setString(2, address);
        statement.setString(3, email);
        statement.setString(4, phone);
        
        statement.executeUpdate();
        
    }
    
    public model.Customer getCustomerByOrderID(int orderID) throws SQLException {
        String sql = "SELECT Customer.CustomerID, Fullname, Address, Email, Phone FROM Customer JOIN [Order] ON [Order].CustomerID=Customer.CustomerID WHERE [Order].OrderID=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, orderID);
        
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            return new model.Customer(rs.getInt("CustomerID"), rs.getString("Fullname"), rs.getString("Address"), rs.getString("Email"), rs.getString("Phone"));
        }
        
        return null;
    }
    
    public ArrayList<model.Customer> getAll() throws SQLException {
        ArrayList<model.Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM Customer ORDER BY CustomerID DESC";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            list.add(new model.Customer(rs.getInt("CustomerID"), rs.getString("Fullname"), rs.getString("Address"), rs.getString("Email"), rs.getString("Phone")));
        }
        
        return list;
    }
}
