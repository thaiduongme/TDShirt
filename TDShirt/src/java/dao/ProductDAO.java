/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Product;
import java.util.ArrayList;

/**
 *
 * @author ThaiDuong
 */
public class ProductDAO extends DBContext {

    // Get TOP 9 Best Selling Products order by Total Sold
    public ArrayList<Product> getTop9BestSellingProducts() throws SQLException {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE ProductID IN (SELECT TOP 9 ProductID FROM OrderDetail GROUP BY ProductID ORDER BY SUM(Quantity) DESC);";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            list.add(new Product(rs.getInt("ProductID"), rs.getInt("Availability"), rs.getString("Name"), rs.getString("Description"), rs.getString("IMG"), rs.getFloat("Price")));
        }

        return list;
    }

    // Get a Product By ID, if doesn't exist return null
    public Product getProductByID(int ID) throws SQLException {
        String sql = "SELECT * FROM Product WHERE ProductID=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, ID);

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            return new Product(rs.getInt("ProductID"), rs.getInt("Availability"), rs.getString("Name"), rs.getString("Description"), rs.getString("IMG"), rs.getFloat("Price"));
        }

        return null;
    }

    public int getProductAvailability(int ID) throws SQLException {
        String sql = "SELECT Availability FROM Product WHERE ProductID=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, ID);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            return rs.getInt("Availability");
        }

        return -1;

    }

    public void updateProductAvailability(int ID, int newAvailability) throws SQLException {
        String sql = "UPDATE Product SET Availability=? WHERE ProductID=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, newAvailability);
        statement.setInt(2, ID);
        statement.executeUpdate();
    }

    public ArrayList<Product> getAllProducts() throws SQLException {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product;";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            list.add(new Product(rs.getInt("ProductID"), rs.getInt("Availability"), rs.getString("Name"), rs.getString("Description"), rs.getString("IMG"), rs.getFloat("Price")));
        }

        return list;
    }
    
    public void addProduct(String name, String description, String img, float price, int availability) throws SQLException {
        String sql = "INSERT INTO Product (Name, Description, IMG, Price, Availability) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, description);
        statement.setString(3, img);
        statement.setFloat(4, price);
        statement.setInt(5, availability);
        statement.executeUpdate();
    }
    
    public int getLatestID() throws SQLException {
        String sql = "SELECT TOP 1 ProductID FROM Product ORDER BY ProductID DESC";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            return rs.getInt("ProductID");
        }
        return -1;
    }
    
    public ArrayList<Product> get9LatestProducts() throws SQLException {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT TOP 9 * FROM Product ORDER BY ProductID DESC";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            list.add(new Product(rs.getInt("ProductID"), rs.getInt("Availability"), rs.getString("Name"), rs.getString("Description"), rs.getString("IMG"), rs.getFloat("Price")));
        }

        return list;
    }
    
    public void updateProduct(int productID, String productName, String description, String img, float price, int availability) throws SQLException {
        String sql = "UPDATE Product SET Name=?, Description=?, IMG=?, Price=?, Availability=? WHERE ProductID=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, productName);
        statement.setString(2, description);
        statement.setString(3, img);
        statement.setFloat(4, price);
        statement.setInt(5, availability);
        statement.setInt(6, productID);
        
        statement.executeUpdate();
    }
    
    public void delete(int productID) throws SQLException {
        String sql = "DELETE FROM ProductSize WHERE ProductID=?;DELETE FROM OrderDetail WHERE ProductID=?;DELETE FROM Product WHERE ProductID=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, productID);
        statement.setInt(2, productID);
        statement.setInt(3, productID);
        
        statement.executeUpdate();
    }
}
