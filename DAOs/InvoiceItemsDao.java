package Oblig3.DAOs;

import Oblig3.SQLReadFiles.Invoice_items;
import Oblig3.TableViewClass.InvoiceItemsObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceItemsDao {
    String getInvoiceItemsQuery = "SELECT * FROM main.invoice_items WHERE invoice = ?";
    PreparedStatement customerById;
    ConnectionAdapter connectionAdapter = new ConnectionAdapter();

    public InvoiceItemsDao() {
        connectionAdapter.startConnect();

        try {
            customerById = connectionAdapter
                    .getConnection()
                    .prepareStatement(getInvoiceItemsQuery);

        } catch (SQLException e) {
            System.out.println(e.getMessage() );
        }
//        connectionAdapter.stopConnect();
    }


    public ObservableList<InvoiceItemsObservable> allInvoiceItemsObservableList() {
        ObservableList<InvoiceItemsObservable> list = FXCollections.observableArrayList();
        PreparedStatement statement = null;
        connectionAdapter.startConnect();

        try {
            String query = "Select * From invoice_items";
            statement = connectionAdapter.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next() ) {
                InvoiceItemsObservable observable = new InvoiceItemsObservable(
                        rs.getInt("invoice"),
                        rs.getInt("product")
                );

                list.add(observable);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() );
        }
        connectionAdapter.stopConnect();

        return list;
    }


    public Invoice_items getInvoiceItemsById(int invoice) {
        ConnectionAdapter connectionAdapter = new ConnectionAdapter();
        connectionAdapter.startConnect();

        Invoice_items tempInvoiceItems = null;

        try {
            PreparedStatement invoiceItemsById = connectionAdapter
                    .getConnection()
                    .prepareStatement(getInvoiceItemsQuery);

            invoiceItemsById.setInt(1, invoice);
            ResultSet rs = invoiceItemsById.executeQuery();

            if (rs.next() ) {
                tempInvoiceItems = new Invoice_items(rs.getInt(1), rs.getInt(2));
            } else {
                System.out.println("Couldn't find any from customer database");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return tempInvoiceItems;
    }

    public int getInvoiceQuantity (int invoice) {
        ConnectionAdapter connectionAdapter = new ConnectionAdapter();
        connectionAdapter.startConnect();
        int invoiceNr = 0;

        try {
            PreparedStatement invoiceItemsById = connectionAdapter
                    .getConnection()
                    .prepareStatement(getInvoiceItemsQuery);

            invoiceItemsById.setInt(1, invoice);
            ResultSet rs = invoiceItemsById.executeQuery();

            if (rs.next() ) {
                invoiceNr = rs.getInt(1);

            } else {
                System.out.println("Couldn't find any from customer database");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return invoiceNr;
    }


}
