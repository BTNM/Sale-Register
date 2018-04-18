package Oblig3.DAOs;

import Oblig3.SQLReadFiles.Invoice;
import Oblig3.TableViewClass.AddressObservable;
import Oblig3.TableViewClass.InvoiceObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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


    public ObservableList<InvoiceObservable> allInvoiceObservableList() {
        ObservableList<InvoiceObservable> list = FXCollections.observableArrayList();
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
