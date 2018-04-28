package Oblig3.DAOs;

import Oblig3.SQLReadFiles.Product;
import Oblig3.TableViewClass.ProductObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDao {
    String getProductQuery = "SELECT * FROM product WHERE product_id = ?";
    PreparedStatement customerById;
    ConnectionAdapter connectionAdapter = new ConnectionAdapter();

    public ProductDao() {
        connectionAdapter.startConnect();

        try {
            customerById = connectionAdapter
                    .getConnection()
                    .prepareStatement(getProductQuery);

        } catch (SQLException e) {
            System.out.println(e.getMessage() );
        }
//        connectionAdapter.stopConnect();
    }


    public ArrayList<ProductObservable> allProductObservableList() {
//        ObservableList<ProductObservable> list = FXCollections.observableArrayList();
        ArrayList<ProductObservable> list = new ArrayList<>();

        PreparedStatement statement = null;
        connectionAdapter.startConnect();

        try {
            String query = "Select * From product";
            statement = connectionAdapter.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next() ) {
                ProductObservable observable = new ProductObservable(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("description"),
                        rs.getFloat("price"),
                        rs.getInt("category")
                );

                list.add(observable);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() );
        }
        connectionAdapter.stopConnect();

        return list;
    }

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
