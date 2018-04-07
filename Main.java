package Oblig3;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {

    public static void main(String[] args) {
        Main main = new Main();
        ReadQueryFromFile readSql = new ReadQueryFromFile();

        File file = new File(ReadQueryFromFile.datebasePath);
        if(!file.exists()){
            readSql.startUpFromSqlFile(ReadQueryFromFile.sqlQueryPath);
//            main.startUpFromSqlFile(sqlQueryPath);
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



}
