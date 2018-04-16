package Oblig3.DAOs;

import Oblig3.SQLReadFiles.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    String getCustomerQuery = "SELECT * FROM main.customer WHERE customer_id = ?";

    public void addCustomer(Customer customer)
    {

    }

    public Customer getCustomerById (int customer_id) {
        ConnectionAdapter connectionAdapter = new ConnectionAdapter();
        connectionAdapter.startConnect();
        Customer tempCustomer = null;

        try {
            PreparedStatement customerById = connectionAdapter
                            .getConnection()
                            .prepareStatement(getCustomerQuery);

            customerById.setInt(1,customer_id);
            ResultSet rs = customerById.executeQuery();

            if (rs.next() ) {
                tempCustomer = new Customer(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5) );
            } else {
                System.out.println("Couldn't find any from customer database");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        connectionAdapter.stopConnect();
        return tempCustomer;
    }
}
