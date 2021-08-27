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
public class ProductSizeDAO extends DBContext {
    public void add(int productID, int sizeID) throws SQLException {
        String sql = "INSERT INTO ProductSize VALUES (?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, productID);
        statement.setInt(2, sizeID);
        statement.executeUpdate();
    }
    
    public void delete(int productID, int sizeID) throws SQLException {
        String sql = "DELETE FROM ProductSize WHERE ProductID=? AND SizeID=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, productID);
        statement.setInt(2, sizeID);
        statement.executeUpdate();
    }
}
