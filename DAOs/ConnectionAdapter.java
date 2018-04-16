package Oblig3.DAOs;

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
        System.out.println("ColumnName: "+ columnName);
        System.out.println("ColumnValue: "+ columnValue);
        System.out.println("id: "+ id);

        System.out.println("table: "+ tableName+ " columnName : "+columnName+" columnValue : "+ columnValue + " id : "+id);
        try (Connection conn = this.createConnection();
//             PreparedStatement statement = conn.prepareStatement(updateDatabaseQuery);
             PreparedStatement statement = conn.prepareStatement("Update "+tableName+" SET "+columnName+" = ? WHERE customer_id = ?");
        ) {
//            statement.setString(1,tableName);
//            statement.setString(2,columnName);
//            statement.setString(3,columnValue);
//            statement.setString(4,id);

            statement.setString(1,columnValue);
            statement.setString(2,id);

            statement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage() );
        }


    }


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
