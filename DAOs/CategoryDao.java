package Oblig3.DAOs;

import Oblig3.SQLReadFiles.Category;
import Oblig3.TableViewClass.AddressObservable;
import Oblig3.TableViewClass.CategoryObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDao {
    String getCategoryQuery = "SELECT * FROM category WHERE category_id = ?";
    PreparedStatement customerById;
    ConnectionAdapter connectionAdapter = new ConnectionAdapter();

    public CategoryDao() {
        connectionAdapter.startConnect();

        try {
            customerById = connectionAdapter
                    .getConnection()
                    .prepareStatement(getCategoryQuery);

        } catch (SQLException e) {
            System.out.println(e.getMessage() );
        }
//        connectionAdapter.stopConnect();
    }

    public ArrayList<CategoryObservable> allCategoryObservableList() {
//        ObservableList<CategoryObservable> list = FXCollections.observableArrayList();
        ArrayList<CategoryObservable> list = new ArrayList<>();

        PreparedStatement statement = null;
        connectionAdapter.startConnect();

        try {
            String query = "Select * From category";
            statement = connectionAdapter.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next() ) {
                CategoryObservable observable = new CategoryObservable(
                        rs.getInt("category_id"),
                        rs.getString("category_name")
                );

                list.add(observable);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() );
        }
        connectionAdapter.stopConnect();

        return list;
    }


    public Category getCategoryById(int category_id ) {
        ConnectionAdapter connectionAdapter = new ConnectionAdapter();
        connectionAdapter.startConnect();

        Category tempCategory = null;

        try {
            PreparedStatement categoryById = connectionAdapter
                    .getConnection()
                    .prepareStatement(getCategoryQuery);

            categoryById.setInt(1, category_id);
            ResultSet rs = categoryById.executeQuery();

            if (rs.next()) {
                tempCategory = new Category(rs.getInt(1), rs.getString(2));
            } else {
                System.out.println("Couldn't find any from customer database");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return tempCategory;
    }


}
