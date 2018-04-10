package Oblig3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {
    static SqlAndQueryFromFile readSql = new SqlAndQueryFromFile();


    public static void main(String[] args) {
//        Main main = new Main();

//        File file = new File(SqlAndQueryFromFile.datebasePath);
//        if(!file.exists()){
//            readSql.startUpFromSqlFile(SqlAndQueryFromFile.sqlQueryPath);




//            main.startUpFromSqlFile(sqlQueryPath);
//            main.startUpFromSqlFile("C:\\Users\\Bao Thien\\Dropbox\\Skole\\UIB 8\\INFO233\\Oblig\\Oblig3\\oblig3v1_database.sql");
//        }

//        ArrayList<Customer> tempList = readSql.getAllCustomer();
//
//        for (Customer c : tempList) {
//            System.out.println("id: "+c.getCustomer_id()+" name: "+ c.getCustomer_name()+ " phone nr: "+ c.getPhone_number() );
//        }

        System.out.println(String.format("%10s %-10s %-2s", "hello","hello","hello" ) );

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
        




        BorderPane mainBp = new BorderPane();
        mainBp.setTop(addTopLayout() );
        mainBp.setCenter(getMiddleTableView() );

//        HBox topPart = new HBox();
//        topPart.setPadding(new Insets(5,10,5,10));
//        topPart.setSpacing(10);
//        topPart.getChildren().addAll(customerInfo, fakturaInfo );
//
//        mainBp.setTop(topPart);

        Scene fakturaScene = new Scene(mainBp,400,600);


        return fakturaScene;
    }

    private TableView getMiddleTableView() {
        TableView productTable = new TableView();
//        productTable.setEditable(true);
        TableColumn categoryCol = new TableColumn("Category Id");
        TableColumn descriptionCol = new TableColumn("Description");
        TableColumn quantityCol = new TableColumn("Quantity");
        TableColumn priceCol = new TableColumn("Price per unit");
        TableColumn sumQuanityCol = new TableColumn("Sum of quantity");

        productTable.getColumns().addAll(categoryCol,descriptionCol,quantityCol,priceCol,sumQuanityCol);
        return productTable;
    }

    public String printLeftAdjusted(String name, String actual) {
        int line = 25;
        int arg1 = line - name.length();
        int arg2 = line - actual.length();

        return String.format("|%-"+arg1+"s %"+arg2+"s |",name,actual );
    }

    public GridPane addTopLayout () {
        Customer customer = readSql.getCustomerById(1);
        Address address = readSql.getAddressById(customer.getAddress()); // 1

        VBox customerInfo = new VBox();
        customerInfo.setSpacing(5); // gap between nodes

        Label customerName = new Label(customer.getCustomer_name());

        Label customerStreet = new Label(address.getStreet_name()+ " " + address.getStreet_number() );
        Label customerPostal = new Label(address.getPostal_code()+ " " + address.getPostal_town() );

        Label phoneNumber = new Label(customer.getPhone_number());
        Label billingAccount = new Label(customer.getBilling_account());

        customerInfo.getChildren().addAll(customerName, customerStreet, customerPostal, phoneNumber, billingAccount);


        Invoice invoice = readSql.getInvoiceById(customer.getCustomer_id());

        VBox fakturaInfo = new VBox();
        fakturaInfo.setSpacing(5);

//        fakturaInfo.setAlignment(Pos.TOP_RIGHT);

//        String.format("%-5s %-5s","CustomerId: ", invoice.getCustomer());

//        Label customerId = new Label(printLeftAdjusted("CustomerId: ", String.valueOf(invoice.getCustomer()) ));
//        Label invoiceId = new Label(printLeftAdjusted("InvoiceId: ",String.valueOf(invoice.getInvoice_id()) ));
//        Label invoiceDate = new Label(printLeftAdjusted("InvoiceDate: ",invoice.getDato() ));
//
//        fakturaInfo.getChildren().addAll(customerId, invoiceId, invoiceDate);

        HBox row1 = new HBox();
        Label customerIdLabel = new Label("CustomerId: ");
        Label customerIdValue = new Label(String.valueOf(invoice.getCustomer() ));
        row1.getChildren().addAll(customerIdLabel, customerIdValue);
//        customerIdLabel.setAlignment(Pos.TOP_LEFT);
//        customerIdValue.setAlignment();
        row1.setAlignment(Pos.TOP_LEFT);

        HBox row2 = new HBox();
        Label invoiceIdLabel = new Label("InvoiceId: ");
        Label invoiceIdValue = new Label(String.valueOf(invoice.getInvoice_id()) );
        row2.getChildren().addAll(invoiceIdLabel, invoiceIdValue);
        row2.setAlignment(Pos.TOP_LEFT);

        HBox row3 = new HBox();
        Label invoiceDateLabel = new Label("InvoiceDate: ");
        Label invoiceDateValue = new Label(invoice.getDato() );
        row3.getChildren().addAll(invoiceDateLabel, invoiceDateValue);
        row3.setAlignment(Pos.TOP_LEFT);

        fakturaInfo.getChildren().addAll(row1,row2, row3);

        Product product = readSql.getProductById(customer.getAddress());

        //category_id, description, quantity, price per unit, sum of quantity
//        String.format("%s %s %s %s %s", product.getCategory(), product.getDescription(), "0", product.getPrice(),"36" );
//        Label productDescription = new Label();



        GridPane topGridLayout = new GridPane();
        topGridLayout.setHgap(10);
        topGridLayout.setVgap(10);
        topGridLayout.setPadding(new Insets(4)); // padding outer layer around the layout

        topGridLayout.add(customerInfo,0,0);
        topGridLayout.add(fakturaInfo,14,0);

        return topGridLayout;
    }

}
