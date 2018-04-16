package Oblig3.DAOs;

import Oblig3.SQLReadFiles.Invoice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceDao {
    String getInvoiceQuery = "SELECT * FROM main.invoice WHERE customer = ?";


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
