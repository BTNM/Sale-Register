package Oblig3.DAOs;

import Oblig3.SQLReadFiles.Invoice_items;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceItemsDao {
    String getInvoiceItemsQuery = "SELECT * FROM main.invoice_items WHERE invoice = ?";



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
