package Oblig3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

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

        Customer customer = readSql.getCustomerById(1);
        Address address = readSql.getAddressById(customer.getAddress()); // 1

        VBox customerInfo = new VBox();
        customerInfo.setSpacing(5); // gap between nodes

        Label customerName = new Label(customer.getCustomer_name());

        Label customerStreet = new Label(address.getStreet_name()+ " " + address.getStreet_number() );
        Label customerPostal = new Label(address.getPostal_code()+ " " + address.getPostal_town() );
//        Label customerStreetName = new Label(address.getStreet_name());
//        Label customerStreetNumber = new Label(address.getStreet_number());
//        Label customerPostalCode = new Label(address.getPostal_code());
//        Label customerPostalTown = new Label(address.getPostal_town());

        Label phoneNumber = new Label(customer.getPhone_number());
        Label billingAccount = new Label(customer.getBilling_account());

        customerInfo.getChildren().addAll(customerName, customerStreet, customerPostal, phoneNumber, billingAccount);
//        customerInfo.getChildren().addAll(customerName,customerStreetName,customerStreetNumber,customerPostalCode,customerPostalTown,phoneNumber,billingAccount);


        Invoice invoice = readSql.getInvoiceById(customer.getCustomer_id());

        VBox fakturaInfo = new VBox();
        fakturaInfo.setSpacing(5);
//        fakturaInfo.setAlignment(Pos.TOP_RIGHT);

        String.format("%-5s %-5s","CustomerId: ", invoice.getCustomer());

        Label customerId = new Label("CustomerId: "+ invoice.getCustomer() );
        Label invoiceId = new Label("InvoiceId: " + invoice.getInvoice_id() );
        Label invoiceDate = new Label("InvoiceDate: " + invoice.getDato() );


        fakturaInfo.getChildren().addAll(customerId, invoiceId, invoiceDate);

//
        GridPane topGridLayout = new GridPane();
        topGridLayout.setHgap(4);
        topGridLayout.setVgap(4);
        topGridLayout.setPadding(new Insets(4)); // padding outer layer around the layout

        topGridLayout.add(customerInfo,0,0);
        topGridLayout.add(fakturaInfo,3,0);

        Product product = new Product();

        String.format("%s %s %s %s %s", product.getCategory(), product.getDescription(), "antall", product.getPrice() );
        Label productDescription = new Label();


        BorderPane mainBp = new BorderPane();
        mainBp.setTop(topGridLayout);

//        HBox topPart = new HBox();
//        topPart.setPadding(new Insets(5,10,5,10));
//        topPart.setSpacing(10);
//        topPart.getChildren().addAll(customerInfo, fakturaInfo );
//
//        mainBp.setTop(topPart);

        Scene fakturaScene = new Scene(mainBp,400,600);

        return fakturaScene;
    }

//    public VBox addCustomerInfoVbox () {
//
//
//
//    }

}
