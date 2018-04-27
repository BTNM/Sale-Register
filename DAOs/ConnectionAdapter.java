package Oblig3.DAOs;

import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ConnectionAdapter {
    private static String databaseName = "SaleRegister.db";
    public static String sqlQueryPath = "C:\\Users\\Bao Thien\\Dropbox\\IdeaProjects\\src\\Oblig3\\oblig3v1_database.sql"; // hjemmepc Bao Thien, skolepc BaoThien
    public static String datebasePath = "jdbc:sqlite:C:/Users/Bao Thien/Dropbox/IdeaProjects/src/Oblig3/"+databaseName;

    private Connection connection;

    public Connection getConnection(){
        return connection;
    }

    public void startConnect() {
        this.connection = createConnection();
    }

    public void stopConnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void updataDatabaseFromTableView (String tableName ,String columnName, String columnValue, String id) {
//        System.out.println("ColumnName: "+ columnName);
//        System.out.println("ColumnValue: "+ columnValue);
//        System.out.println("id: "+ id);

        try (Connection conn = this.createConnection();
//             PreparedStatement statement = conn.prepareStatement(updateDatabaseQuery);
             PreparedStatement statement = conn.prepareStatement("Update "+tableName+" SET "+columnName+" = ? WHERE "+columnName+" = ?");
        ) {

            statement.setString(1,columnValue);
            statement.setString(2,id);

            statement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage() );
        }

    }

    public void insertIntoDatabase(String tableName, String col1, String col2, String col3, String col4, String col5) {
        try (Connection conn = this.createConnection() ) {
            PreparedStatement statement = null;

            switch (tableName) {
                case "customer":
                    statement = conn.prepareStatement("INSERT INTO "+tableName+" (customer_id,customer_name,address,phone_number,billing_account) "+"VALUES (?,?,?,?,?)");
                    statement.setInt(1,Integer.valueOf(col1));
                    statement.setString(2,col2);
                    statement.setInt(3,Integer.valueOf(col3));
                    statement.setString(4,col4);
                    statement.setString(5,col5);
                    break;
                case "address":
                    statement = conn.prepareStatement("INSERT INTO "+tableName+" (address_id,street_number,street_name,postal_code,postal_town) "+"VALUES (?,?,?,?,?)");
                    statement.setInt(1,Integer.valueOf(col1));
                    statement.setString(2,col2);
                    statement.setString(3,col3);
                    statement.setString(4,col4);
                    statement.setString(5,col5);
                    break;
                case "category":
                    statement = conn.prepareStatement("INSERT INTO "+tableName+" (category_id,category_name) "+"VALUES (?,?)");
                    statement.setInt(1,Integer.valueOf(col1));
                    statement.setString(2,col2);
                    break;
                case "invoice":
                    statement = conn.prepareStatement("INSERT INTO "+tableName+" (invoice_id,customer,dato) "+"VALUES (?,?,?)");
                    statement.setInt(1,Integer.valueOf(col1));
                    statement.setInt(2,Integer.valueOf(col2));
                    statement.setString(3,col3);
                    break;
                case "invoice_items":
                    statement = conn.prepareStatement("INSERT INTO "+tableName+" (invoice,product) "+"VALUES (?,?)");
                    statement.setInt(1,Integer.valueOf(col1));
                    statement.setInt(2,Integer.valueOf(col2));
                    break;
                case "product":
                    statement = conn.prepareStatement("INSERT INTO "+tableName+" (product_id,product_name,description,price,category) "+"VALUES (?,?,?,?,?)");
                    statement.setInt(1,Integer.valueOf(col1));
                    statement.setString(2,col2);
                    statement.setString(3,col3);
                    statement.setFloat(4,Float.valueOf(col4));
                    statement.setInt(5,Integer.valueOf(col5));
                    break;
            }

            statement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage() );
        }

    }

    public void insertCustomerIntoDatabase(String tableName, int col1, String col2, int col3, String col4, String col5) {
        try (Connection conn = this.createConnection();
//             PreparedStatement statement = conn.prepareStatement(updateDatabaseQuery);
             PreparedStatement statement = conn.prepareStatement("INSERT INTO "+tableName+" (customer_id,customer_name,address,phone_number,billing_account) "+"VALUES (?,?,?,?,?)");
        ) {
            statement.setInt(1,col1);
            statement.setString(2,col2);
            statement.setInt(3,col3);
            statement.setString(4,col4);
            statement.setString(5,col5);

            statement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage() );
        }

    }

    public void insertAddress () {

    }
    public void insertCategory () {

    }
    public void insertInvoice () {

    }
    public void insertInvoiceItems () {

    }
    public void insertProduct () {

    }

//    public void deleteFromDatabase(String tableName) {
//        try (Connection conn = this.createConnection();
////             PreparedStatement statement = conn.prepareStatement(updateDatabaseQuery);
//             PreparedStatement statement = conn.prepareStatement("DELETE FROM "+tableName+" WHERE ");
//        ) {
//
//
//            statement.execute();
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage() );
//        }
//    }


    public void startUpFromSqlFile(String path) {
        ArrayList<String> tempArray = readQueryFromFile(path);
        for (String l : tempArray) {
//            System.out.println(l);
            executeCompleteQuery(l);
        }
    }

    private void executeCompleteQuery(String query) {
        // inside the try catch, auto close() of conn after use
        try (Connection conn = this.createConnection();
             Statement statement = conn.createStatement();) {

            statement.execute(query);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
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


    private Connection createConnection() {
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
