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
import model.Size;

/**
 *
 * @author ThaiDuong
 */
public class SizeDAO extends DBContext {
    public ArrayList<Size> getSizesByProductID(int ID) throws SQLException {
        ArrayList<Size> sizeList = new ArrayList<>();
        String sql = "SELECT * FROM Size WHERE SizeID IN (SELECT SizeID FROM ProductSize WHERE ProductID=?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, ID);
        
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            sizeList.add(new Size(rs.getInt("SizeID"), rs.getString("Size")));
        }
        
        return sizeList;
    }
    
    public ArrayList<Size> getAllSizes() throws SQLException {
        ArrayList<Size> sizeList = new ArrayList<>();
        String sql = "SELECT * FROM Size;";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            sizeList.add(new Size(rs.getInt("SizeID"), rs.getString("Size")));
        }
        
        return sizeList;
    }
    
    public Size getSizeByID(int ID) throws SQLException {
        String sql = "SELECT Size FROM Size WHERE SizeID=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, ID);
        
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            return new Size(ID, rs.getString("Size"));
        }
        
        return null;
    }
}
