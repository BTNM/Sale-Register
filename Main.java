package Oblig3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Main extends Application {
    private static String databaseName = "SaleRegister.db";
    private static String sqlQueryPath = "C:\\Users\\Bao Thien\\Dropbox\\IdeaProjects\\src\\Oblig3\\oblig3v1_database.sql"; // hjemmepc Bao Thien, skolepc BaoThien
    private static String datebasePath = "jdbc:sqlite:C:/Users/Bao Thien/Dropbox/IdeaProjects/src/Oblig3/"+databaseName;

    public static void main(String[] args) {
        Main main = new Main();

        File file = new File(datebasePath);
        if(!file.exists()){
            main.startUpFromSqlFile(sqlQueryPath);
//            main.startUpFromSqlFile("C:\\Users\\Bao Thien\\Dropbox\\Skole\\UIB 8\\INFO233\\Oblig\\Oblig3\\oblig3v1_database.sql");
        }

        //start javafx start() method
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {


//        GridPane startGrid = new GridPane();
//
//        Scene startScene = new Scene(startGrid,600,400);
//
//        primaryStage.setTitle("Sale Register System");
//        primaryStage.setScene(startScene);
//        primaryStage.show();

    }


    private void startUpFromSqlFile(String path) {
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
