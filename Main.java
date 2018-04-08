package Oblig3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Main extends Application {
    static SqlAndQueryFromFile readSql = new SqlAndQueryFromFile();


    public static void main(String[] args) {
//        Main main = new Main();



        File file = new File(SqlAndQueryFromFile.datebasePath);
        if(!file.exists()){
            readSql.startUpFromSqlFile(SqlAndQueryFromFile.sqlQueryPath);


//            main.startUpFromSqlFile(sqlQueryPath);
//            main.startUpFromSqlFile("C:\\Users\\Bao Thien\\Dropbox\\Skole\\UIB 8\\INFO233\\Oblig\\Oblig3\\oblig3v1_database.sql");
        }

//        ArrayList<Customer> tempList = readSql.getAllCustomer();
//
//        for (Customer c : tempList) {
//            System.out.println("id: "+c.getCustomer_id()+" name: "+ c.getCustomer_name()+ " phone nr: "+ c.getPhone_number() );
//        }



        //start javafx start() method
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {


//        GridPane startGrid = new GridPane();
//
//        Scene startScene = new Scene(startGrid,600,400);
//
        primaryStage.setTitle("Sale Register System");
        primaryStage.setScene(getFaktura());
        primaryStage.show();

    }

    public Scene getFaktura() {
//        Address address1;
//        Category category1;
//        Customer customer1;
//        Invoice invoice1;
//        Invoice_items invoice_items;
//        Product product;

        Customer customer1 = readSql.getCustomerById(1);

        VBox customerInfo = new VBox();
        customerInfo.setSpacing(5); // gap between nodes

        Label customerName = new Label("");
        Label customerStreetName = new Label("");
        Label customerStreetNumber = new Label("");
        Label customerPostalCode = new Label();
        Label customerPostalTown = new Label();

        Label phoneNumber = new Label();
        Label billingAccount = new Label();





        customerInfo.getChildren().addAll();




        VBox fakturaInfo = new VBox();
        fakturaInfo.setSpacing(5);

        fakturaInfo.getChildren().addAll();

        GridPane topGridLayout = new GridPane();
        topGridLayout.setHgap(4);
        topGridLayout.setVgap(4);
        topGridLayout.setPadding(new Insets(4)); // padding outer layer around the layout

        topGridLayout.add(customerInfo,0,0);
        topGridLayout.add(fakturaInfo,2,0);

        BorderPane bp = new BorderPane();

        Scene fakturaScene = new Scene(bp,400,600);

        return fakturaScene;
    }

    public VBox addCustomerInfoVbox () {



    }

}
