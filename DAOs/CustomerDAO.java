package Oblig3.DAOs;

import Oblig3.SQLReadFiles.Customer;
import Oblig3.SQLReadFiles.CustomerAddressDetails;
import Oblig3.SQLReadFiles.InvoiceDetailsSQL;
import Oblig3.TableViewClass.CustomerObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAO {
    ConnectionAdapter connectionAdapter = new ConnectionAdapter();
    String getCustomerQuery = "SELECT * FROM main.customer WHERE customer_id = ?";
    PreparedStatement customerById;

    String getInvoiceDetailsQuery = "SELECT *, (SELECT COUNT(*) FROM invoice_items WHERE invoice=II.invoice) as quantity FROM product AS p JOIN invoice_items AS II ON p.product_id = II.product;\n";
//    String getInvoiceDetailsQuery = "SELECT p.category,p.product_name,p.description, count(II.product) AS Quantity,p.price FROM product AS p join invoice_items AS II ON p.product_id = II.product";
//    SELECT * FROM ( SELECT p.product_id, p.category,p.product_name,p.description, count(II.product) AS Quantity,p.price FROM product AS p join invoice_items AS II ON p.product_id = II.product )
//    SELECT p.product_id, p.category,p.product_name,p.description, count(II.product) AS Quantity,p.price FROM product AS p join invoice_items AS II ON p.product_id = II.product WHERE product_id=1
//    SELECT count(invoice_items.product) AS Quantity from invoice_items WHERE invoice = 1
    PreparedStatement invoiceDetailsById;

    String getCustomerAddressDetailsQuery = "SELECT C.customer_name, A.street_name, A.street_number, A.postal_code, A.postal_town, C.phone_number, c.billing_account, C.customer_id, I.invoice_id, I.dato from (customer as C join invoice I ON C.customer_id = I.customer) JOIN address AS A ON C.address = A.address_id";
    PreparedStatement customerAddressDetailsById;

    public CustomerDAO () {
        connectionAdapter.startConnect();

        try {
            customerById = connectionAdapter.getConnection().prepareStatement(getCustomerQuery);
            invoiceDetailsById = connectionAdapter.getConnection().prepareStatement(getInvoiceDetailsQuery);
            customerAddressDetailsById = connectionAdapter.getConnection().prepareStatement(getCustomerAddressDetailsQuery);

        } catch (SQLException e) {
            System.out.println(e.getMessage() );
        }
//        connectionAdapter.stopConnect();
    }

    public CustomerAddressDetails getCustomerAddressDetails () {
        connectionAdapter.startConnect();

        CustomerAddressDetails temp = null;

        try {
//            PreparedStatement invoiceDetailsById = connectionAdapter
//                    .getConnection()
//                    .prepareStatement(getInvoiceDetailsQuery);

//            customerAddressDetailsById.setInt(1,customer_id);

            ResultSet rs = customerAddressDetailsById.executeQuery();

            if (rs.next() ) {
//                temp = new InvoiceDetailsSQL(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6));
                temp = new CustomerAddressDetails(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(9),rs.getString(10)  );
            } else {
                System.out.println("Couldn't find any from customer database");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        connectionAdapter.stopConnect();
        return temp;
    }

    public InvoiceDetailsSQL getInvoiceDetails (int customer_id) {
        connectionAdapter.startConnect();

        InvoiceDetailsSQL temp = null;

        try {
//            PreparedStatement invoiceDetailsById = connectionAdapter
//                    .getConnection()
//                    .prepareStatement(getInvoiceDetailsQuery);

//            invoiceDetailsById.setInt(1,customer_id);

            ResultSet rs = invoiceDetailsById.executeQuery();

            if (rs.next() ) {
//                tempCustomer = new Customer(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5) );
                temp = new InvoiceDetailsSQL(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6));

            } else {
                System.out.println("Couldn't find any from customer database");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        connectionAdapter.stopConnect();
        return temp;
    }


    public ArrayList<CustomerObservable> allCustomerObservableList() {
//        ObservableList<CustomerObservable> list = FXCollections.observableArrayList();
        ArrayList<CustomerObservable> list = new ArrayList<>();

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

//    public ObservableList<CustomerObservable> getTestObservableTable () {
//        ObservableList<CustomerObservable> table = FXCollections.observableArrayList(
//                new CustomerObservable(2,"test name2",8,"0200 0000","test account"),
//                new CustomerObservable(3,"test name3",7,"0030 0000","test account"),
//                new CustomerObservable(4,"test name4",6,"0004 0000","test account")
//        );
//
//        return table;
//    }

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
