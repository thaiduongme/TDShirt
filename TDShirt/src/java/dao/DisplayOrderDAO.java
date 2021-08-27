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
import model.DisplayOrder;

/**
 *
 * @author ThaiDuong
 */
public class DisplayOrderDAO extends DBContext {
    public ArrayList<DisplayOrder> getAll(int orderID) throws SQLException {
        ArrayList<DisplayOrder> list = new ArrayList<>();
        String sql = "SELECT Product.IMG, Product.Name, Size.Size, Product.Price, OrderDetail.Quantity FROM [Order] JOIN OrderDetail ON OrderDetail.OrderID=[Order].OrderID JOIN Product ON Product.ProductID=OrderDetail.ProductID JOIN Size ON Size.SizeID=OrderDetail.SizeID WHERE [Order].OrderID=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, orderID);
        
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            list.add(new DisplayOrder(rs.getString("IMG"), rs.getString("Name"), rs.getString("Size"), rs.getFloat("Price"), rs.getInt("Quantity")));
        }
        
        return list;
    }
}
