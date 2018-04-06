package Oblig3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
//        main.createNewDatabase("test.db");


//        ArrayList<String> tempArray = main.readQueryFromFile("C:\\Users\\BaoThien\\Dropbox\\IdeaProjects\\src\\Oblig3\\oblig3v1_database.sql");
//        for (String l : tempArray) {
////            System.out.println(l);
//            main.executeCompleteQuery(l);
//        }

        main.startUpFromSqlFile("C:\\Users\\BaoThien\\Dropbox\\IdeaProjects\\src\\Oblig3\\oblig3v1_database.sql");

    }

    public void startUpFromSqlFile (String path) {
        ArrayList<String> tempArray = readQueryFromFile(path);
        for (String l : tempArray) {
//            System.out.println(l);
            executeCompleteQuery(l);
        }
    }

    public ArrayList<String> readQueryFromFile (String path) {
        ArrayList<String> arrayList = new ArrayList<>();
        BufferedReader br = null;
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

            for (String l : stringSplitted) {
//                System.out.println(l);
                arrayList.add(l);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrayList;
    }



    public void executeCompleteQuery (String query) {
        try {
            Connection conn = this.connect("test.db");
            Statement statement = conn.createStatement();

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

    public void createNewDatabase (String fileName) {
        String url = "jdbc:sqlite:C:/Users/BaoThien/Dropbox/IdeaProjects/src/Oblig3/"+fileName;

        try ( Connection conn = DriverManager.getConnection(url) ) {
            if (conn != null) {
                DatabaseMetaData dataMeta = conn.getMetaData();
                System.out.println("The driver is "+ dataMeta.getDriverName() +"\nA new database has been created");
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private Connection connect(String fileName) {
        String url = "jdbc:sqlite:C:/Users/BaoThien/Dropbox/IdeaProjects/src/Oblig3/"+fileName;
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }


}
