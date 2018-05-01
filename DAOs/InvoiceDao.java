package Oblig3.DAOs;

import Oblig3.SQLReadFiles.CustomerAddressDetails;
import Oblig3.SQLReadFiles.Invoice;
import Oblig3.TableViewClass.AddressObservable;
import Oblig3.TableViewClass.InvoiceDetails;
import Oblig3.TableViewClass.InvoiceObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InvoiceDao {
    String getInvoiceQuery = "SELECT * FROM main.invoice WHERE customer = ?";
    PreparedStatement customerById;
    ConnectionAdapter connectionAdapter = new ConnectionAdapter();

    public InvoiceDao() {
        connectionAdapter.startConnect();

        try {
            customerById = connectionAdapter
                    .getConnection()
                    .prepareStatement(getInvoiceQuery);

        } catch (SQLException e) {
            System.out.println(e.getMessage() );
        }
//        connectionAdapter.stopConnect();
    }

    public CustomerAddressDetails getCustomerAddressDetails (int invoiceId) {
        PreparedStatement customerAddressDetailsById;
        CustomerAddressDetails temp = null;

        connectionAdapter.startConnect();

        try {
            String getCustomerAddressDetailsQuery = "SELECT C.customer_name, A.street_name, A.street_number, A.postal_code, A.postal_town, C.phone_number, c.billing_account, C.customer_id, I.invoice_id, I.dato from (customer as C join invoice I ON C.customer_id = I.customer) JOIN address AS A ON C.address = A.address_id WHERE invoice_id = ?";
            customerAddressDetailsById = connectionAdapter.getConnection().prepareStatement(getCustomerAddressDetailsQuery);

            customerAddressDetailsById.setInt(1,invoiceId);
            ResultSet rs = customerAddressDetailsById.executeQuery();

            if (rs.next() ) {
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

    public ArrayList<InvoiceDetails> allInvoiceDetails () {
        ArrayList<InvoiceDetails> list = new ArrayList<>();

        PreparedStatement invoiceDetailsById = null;
        connectionAdapter.startConnect();

        try {
            String getInvoiceDetailsQuery = "SELECT p.product_id, p.product_name, p.category,p.description, (SELECT COUNT(*) FROM invoice_items WHERE invoice=II.invoice) as quantity,p.price, ((SELECT COUNT(*) FROM invoice_items WHERE invoice=II.invoice)*p.price) as sum FROM product AS p JOIN invoice_items AS II ON p.product_id = II.product";
            invoiceDetailsById = connectionAdapter.getConnection().prepareStatement(getInvoiceDetailsQuery);
            ResultSet rs = invoiceDetailsById.executeQuery();

            while (rs.next() ) {
                InvoiceDetails invoiceDetails = new InvoiceDetails(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7)
                );

                list.add(invoiceDetails);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        connectionAdapter.stopConnect();
        return list;
    }


    public ArrayList<InvoiceObservable> allInvoiceObservableList() {
//        ObservableList<InvoiceObservable> list = FXCollections.observableArrayList();
        ArrayList<InvoiceObservable> list = new ArrayList<>();

        PreparedStatement statement = null;
        connectionAdapter.startConnect();

        try {
            String query = "Select * From invoice";
            statement = connectionAdapter.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next() ) {
                InvoiceObservable observable = new InvoiceObservable(
                        rs.getInt("invoice_id"),
                        rs.getInt("customer"),
                        rs.getString("dato")
                );

                list.add(observable);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() );
        }
        connectionAdapter.stopConnect();

        return list;
    }


    public Invoice getInvoiceById (int customer_id) {
        ConnectionAdapter connectionAdapter = new ConnectionAdapter();
        connectionAdapter.startConnect();

        Invoice tempInvoice = null;

        try {
            PreparedStatement invoiceById = connectionAdapter
                    .getConnection()
                    .prepareStatement(getInvoiceQuery);

            invoiceById.setInt(1, customer_id);
            ResultSet rs = invoiceById.executeQuery();

            if (rs.next() ) {
                tempInvoice = new Invoice(rs.getInt(1), rs.getInt(2), rs.getString(3));
            } else {
                System.out.println("Couldn't find any from customer database");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return tempInvoice;
    }



}
