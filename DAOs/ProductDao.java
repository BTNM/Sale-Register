package Oblig3.DAOs;

import Oblig3.SQLReadFiles.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDao {
    String getProductQuery = "SELECT * FROM product WHERE product_id = ?";


    public Product getProductById(int product_id) {
        ConnectionAdapter connectionAdapter = new ConnectionAdapter();
        connectionAdapter.startConnect();

        Product tempProduct = null;

        try {
            PreparedStatement productById = connectionAdapter
                    .getConnection()
                    .prepareStatement(getProductQuery);

            productById.setInt(1,product_id);
            ResultSet rs = productById.executeQuery();

            if (rs.next() ) {
                tempProduct = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getInt(5));
            } else {
                System.out.println("Couldn't find any from customer database");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return tempProduct;
    }

}
