package Oblig3.DAOs;

import Oblig3.SQLReadFiles.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDao {
    String getCategoryQuery = "SELECT * FROM category WHERE category_id = ?";



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
