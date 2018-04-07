package Oblig3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class sqlAndQueryFromFile {
    private static String databaseName = "SaleRegister.db";
    public static String sqlQueryPath = "C:\\Users\\Bao Thien\\Dropbox\\IdeaProjects\\src\\Oblig3\\oblig3v1_database.sql"; // hjemmepc Bao Thien, skolepc BaoThien
    public static String datebasePath = "jdbc:sqlite:C:/Users/Bao Thien/Dropbox/IdeaProjects/src/Oblig3/"+databaseName;

    Connection conn;

    String getAddressQuery = "";
    String getCategoryQuery = "";
    String getCustomerQuery = "SELECT * FROM main.customer";
    String getInvoiceQuery = "";
    String getInvoice_itemsQuery = "";
    String getProductQuery = "";

    PreparedStatement getCustomerById;

    public sqlAndQueryFromFile () {
        try {
            conn = this.connect();

            getCustomerById = conn.prepareStatement(getCustomerQuery);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    public ArrayList<String> getCustomer () {
        ArrayList<String> tempArray = new ArrayList<>(); //make new class for every customer

        try {
//            getCustomerById.setString();
            ResultSet rs = getCustomerById.executeQuery();

            while (rs.next()) {
//                rs.getString(0);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return tempArray;
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
