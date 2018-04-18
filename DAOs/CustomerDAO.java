package Oblig3.DAOs;

import Oblig3.SQLReadFiles.Customer;
import Oblig3.TableViewClass.CustomerObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    String getCustomerQuery = "SELECT * FROM main.customer WHERE customer_id = ?";
    PreparedStatement customerById;
    ConnectionAdapter connectionAdapter = new ConnectionAdapter();

    public CustomerDAO () {
        connectionAdapter.startConnect();

        try {
            customerById = connectionAdapter
                    .getConnection()
                    .prepareStatement(getCustomerQuery);

        } catch (SQLException e) {
            System.out.println(e.getMessage() );
        }
//        connectionAdapter.stopConnect();
    }


    public ObservableList<CustomerObservable> allCustomerObservableList() {
        ObservableList<CustomerObservable> list = FXCollections.observableArrayList();
        PreparedStatement statement = null;
        connectionAdapter.startConnect();

        try {
            String query = "Select * From customer";
            statement = connectionAdapter.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next() ) {
                CustomerObservable customerOb = new CustomerObservable(
                        rs.getInt("customer_id"),
                        rs.getString("customer_name"),
                        rs.getInt("address"),
                        rs.getString("phone_number"),
                        rs.getString("billing_account")
                );

                list.add(customerOb);
            }

//            for (CustomerObservable c : list) {
//                System.out.println(c.getCustomerName());
//            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() );
        }
        connectionAdapter.stopConnect();

        return list;
    }

    public ObservableList<CustomerObservable> getTestObservableTable () {
        ObservableList<CustomerObservable> table = FXCollections.observableArrayList(
                new CustomerObservable(2,"test name2",8,"0200 0000","test account"),
                new CustomerObservable(3,"test name3",7,"0030 0000","test account"),
                new CustomerObservable(4,"test name4",6,"0004 0000","test account")
        );

        return table;
    }

    public Customer getCustomerById (int customer_id) {
//        ConnectionAdapter connectionAdapter = new ConnectionAdapter();
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
