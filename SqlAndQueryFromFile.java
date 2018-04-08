package Oblig3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SqlAndQueryFromFile {
    private static String databaseName = "SaleRegister.db";
    public static String sqlQueryPath = "C:\\Users\\Bao Thien\\Dropbox\\IdeaProjects\\src\\Oblig3\\oblig3v1_database.sql"; // hjemmepc Bao Thien, skolepc BaoThien
    public static String datebasePath = "jdbc:sqlite:C:/Users/Bao Thien/Dropbox/IdeaProjects/src/Oblig3/"+databaseName;

    Connection conn;

    String getAddressQuery = "SELECT * FROM main.address WHERE address_id = ?";
    String getCategoryQuery = "";
    String getCustomerQuery = "SELECT * FROM main.customer WHERE customer_id = ?";
    String getInvoiceQuery = "";
    String getInvoice_itemsQuery = "";
    String getProductQuery = "";

    PreparedStatement customerById;
    PreparedStatement addressById;


    public SqlAndQueryFromFile() {
        try {
            conn = this.connect();

            customerById = conn.prepareStatement(getCustomerQuery);
            addressById = conn.prepareStatement(getAddressQuery);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


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

        try {
            conn = DriverManager.getConnection(datebasePath);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }




}
