package Oblig3.DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionAdapter {
    private static String databaseName = "SaleRegister.db";
    public static String sqlQueryPath = "C:\\Users\\BaoThien\\Dropbox\\IdeaProjects\\src\\Oblig3\\oblig3v1_database.sql"; // hjemmepc Bao Thien, skolepc BaoThien
    public static String datebasePath = "jdbc:sqlite:C:/Users/BaoThien/Dropbox/IdeaProjects/src/Oblig3/"+databaseName;

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

        try (Connection conn = this.createConnection();
//             PreparedStatement statement = conn.prepareStatement(updateDatabaseQuery);
             PreparedStatement statement = conn.prepareStatement("Update ? SET ? = ? WHERE customer_id = ?");
        ) {
            statement.setString(1,tableName);
            statement.setString(2,columnName);
            statement.setString(3,columnValue);
            statement.setString(4,id);

            statement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage() );
        }


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
