/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ThaiDuong
 */
public class OrderDAO extends DBContext {
    public int getLatestID() throws SQLException {
        String sql = "SELECT TOP 1 OrderID FROM [Order] ORDER BY OrderID DESC";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            return rs.getInt("OrderID");
        }
        return -1;
        
    }
    
    public void addOrder(int customerID) throws SQLException {
        String sql = "INSERT INTO [Order] (CreatedDate, CustomerID, StatusID) VALUES (GETDATE(), ?, 1);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, customerID);
        
        statement.executeUpdate();
    }
    
    public void updateStatus(int orderID, int statusID) throws SQLException {
        String sql = "UPDATE [Order] SET [Order].StatusID=? WHERE [Order].OrderID=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, statusID);
        statement.setInt(2, orderID);
        statement.executeUpdate();
    }
}
