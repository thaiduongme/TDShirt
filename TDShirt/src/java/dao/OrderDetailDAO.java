/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ThaiDuong
 */
public class OrderDetailDAO extends DBContext {
    public void add(int orderID, int productID, int quantity, int sizeID) throws SQLException {
        String sql = "INSERT INTO OrderDetail VALUES (?, ?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, orderID);
        statement.setInt(2, productID);
        statement.setInt(3, quantity);
        statement.setInt(4, sizeID);
        statement.executeUpdate();
    }
}
