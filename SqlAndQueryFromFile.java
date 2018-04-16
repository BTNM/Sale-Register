package Oblig3;

import Oblig3.SQLReadFiles.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SqlAndQueryFromFile {
    private static String databaseName = "SaleRegister.db";
    public static String sqlQueryPath = "C:\\Users\\BaoThien\\Dropbox\\IdeaProjects\\src\\Oblig3\\oblig3v1_database.sql"; // hjemmepc Bao Thien, skolepc BaoThien
    public static String datebasePath = "jdbc:sqlite:C:/Users/BaoThien/Dropbox/IdeaProjects/src/Oblig3/"+databaseName;


    Connection conn;

    String getAddressQuery = "SELECT * FROM main.address WHERE address_id = ?";
    String getCategoryQuery = "SELECT * FROM category WHERE category_id = ?";
    String getCustomerQuery = "SELECT * FROM main.customer WHERE customer_id = ?";
    String getInvoiceQuery = "SELECT * FROM main.invoice WHERE customer = ?";
    String getInvoiceItemsQuery = "SELECT * FROM main.invoice_items WHERE invoice = ?";
    String getProductQuery = "SELECT * FROM product WHERE product_id = ?";
    String getInvoiceQuantityQuery = "SELECT count(invoice_items.invoice) From invoice_items WHERE invoice = ?";

    String updateDatabaseQuery = "Update customer SET ? = ? WHERE customer_id = ?";

    PreparedStatement addressById;
    PreparedStatement categoryById;
    PreparedStatement customerById;
    PreparedStatement invoiceById;
    PreparedStatement invoiceItemsById;
    PreparedStatement productById;

    PreparedStatement invoiceQuantityById;

    PreparedStatement updateDatabaseById;

    // mvc pattern
    public SqlAndQueryFromFile() {
        try {
            conn = this.connect();

            addressById = conn.prepareStatement(getAddressQuery);
            categoryById = conn.prepareStatement(getCategoryQuery);
            customerById = conn.prepareStatement(getCustomerQuery);
            invoiceById = conn.prepareStatement(getInvoiceQuery);
            invoiceItemsById = conn.prepareStatement(getInvoiceItemsQuery);
            productById = conn.prepareStatement(getProductQuery);

            invoiceQuantityById = conn.prepareStatement(getInvoiceQuantityQuery);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    public int getInvoiceQuantity (int invoice) {
        int invoiceNr = 0;

        try {
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

    public void updataDatabaseFromTableView (String columnName, String columnValue, String id) {
        System.out.println("ColumnName: "+ columnName);
        System.out.println("ColumnValue: "+ columnValue);
        System.out.println("id: "+ id);

        try (Connection conn = this.connect();
//             PreparedStatement statement = conn.prepareStatement(updateDatabaseQuery);
             PreparedStatement statement = conn.prepareStatement("Update customer SET customer_id = ? WHERE customer_id = ?");

             ) {
//            statement.setString(1,columnName);
//            statement.setString(2,columnValue);
//            statement.setString(3,id);
            statement.setString(1,columnValue);
            statement.setString(2,id);

            statement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage() );
        }


    }

    public Product getProductById(int product_id) {
        Product tempProduct = null;

        try {
            productById.setInt(1,product_id);
            ResultSet rs = productById.executeQuery();

            if (rs.next() ) {
                tempProduct = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getInt(5));
            } else {
                System.out.println("Couldn't find any from customer database");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return tempProduct;
    }

    public Category getCategoryById(int category_id ) {
        Category tempCategory = null;

        try {
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

    public Invoice_items getInvoiceItemsById(int invoice) {
        Invoice_items tempInvoiceItems = null;

        try {
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


    public Invoice getInvoiceById (int customer_id) {
        Invoice tempInvoice = null;

        try {

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


    public Customer getCustomerById (int customer_id) {
        Customer tempCustomer = null;

        try {

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

        return tempCustomer;
    }

    public Address getAddressById (int address_id) {
        Address tempAddress = null;

        try {
            addressById.setInt(1, address_id);
            ResultSet rs = addressById.executeQuery();

            if (rs.next()) {
                tempAddress = new Address(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            } else {
                System.out.println("Couldn't find any from address database");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return tempAddress;
    }

    public ArrayList<Customer> getAllCustomer () {
//        ArrayList<String> tempArray = new ArrayList<>(); //make new class for every customer

        ArrayList<Customer> tempList = new ArrayList<>();

        try {
//            getCustomerById.setString();
            ResultSet rs = customerById.executeQuery();

            int count = 0;
            while (rs.next()) {
                Customer tempCustomer = new Customer(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5));
                tempList.add(tempCustomer);
//                tempArray.add(rs.getString(count) );
//                System.out.println(rs.getString(0));
//                count++;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

//        return tempArray;
        return tempList;
    }

    public void startUpFromSqlFile(String path) {
        ArrayList<String> tempArray = readQueryFromFile(path);
        for (String l : tempArray) {
//            System.out.println(l);
            executeCompleteQuery(l);
        }
    }

    private ArrayList<String> readQueryFromFile(String path) {
        ArrayList<String> arrayList = new ArrayList<>();
        BufferedReader br;
        StringBuilder sb = new StringBuilder();
        // C:\Users\BaoThien\Dropbox\IdeaProjects\src\Oblig3\oblig3v1_database.sql
        try {
            br = new BufferedReader(new FileReader(path));
            String line;

            while ((line = br.readLine()) != null ) {
                sb.append(line);

            }
            String wholeString = sb.toString();
            String stringSplitted[] = wholeString.split(";");

            // add all splitted strings to temp arrayList
            arrayList.addAll(Arrays.asList(stringSplitted));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrayList;
    }


    private void executeCompleteQuery(String query) {
        // inside the try catch, auto close() of conn after use
        try (Connection conn = this.connect();
             Statement statement = conn.createStatement();) {

            statement.execute(query);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//    public void createNewTable (String query) {
//        try {
//            Connection conn = this.connect("test.db");
//            Statement statement = conn.createStatement();
//
//            statement.execute(query);
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

//    public void createNewDatabase (String fileName) {
//        String url = "jdbc:sqlite:C:/Users/BaoThien/Dropbox/IdeaProjects/src/Oblig3/"+fileName;
//
//        try ( Connection conn = DriverManager.getConnection(url) ) {
//            if (conn != null) {
//                DatabaseMetaData dataMeta = conn.getMetaData();
//                System.out.println("The driver is "+ dataMeta.getDriverName() +"\nA new database has been created");
//            }
//
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//    }

    private Connection connect() {
//        String url = "jdbc:sqlite:C:/Users/Bao Thien/Dropbox/IdeaProjects/src/Oblig3/"+fileName;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(datebasePath);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }




}
