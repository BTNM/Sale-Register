package Oblig3;

import Oblig3.SQLReadFiles.Address;
import Oblig3.SQLReadFiles.Customer;
import Oblig3.SQLReadFiles.Invoice;
import Oblig3.SQLReadFiles.Product;
import Oblig3.TableViewClass.InvoiceProducts;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {
    Stage mainWindow;

    static SqlAndQueryFromFile readSql = new SqlAndQueryFromFile();


    public static void main(String[] args) {
//        Main main = new Main();

//        File file = new File(SqlAndQueryFromFile.datebasePath);
//            if(!file.exists()) {
//                readSql.startUpFromSqlFile(SqlAndQueryFromFile.sqlQueryPath);
//            }

        // design pattern observables, auto update tableview


//            main.startUpFromSqlFile(sqlQueryPath);
//            main.startUpFromSqlFile("C:\\Users\\Bao Thien\\Dropbox\\Skole\\UIB 8\\INFO233\\Oblig\\Oblig3\\oblig3v1_database.sql");
//        }



        //start javafx start() method
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        mainWindow = primaryStage;


//        GridPane startGrid = new GridPane();
//        Scene startScene = new Scene(startGrid,600,400);
//

        primaryStage.setTitle("Sale Register System");
        primaryStage.setScene(getIntro());
//        primaryStage.setScene(getFaktura() );
        primaryStage.show();

    }

    public Scene getIntro () {
//        Label customers = new Label("Customers");
//        Label productCategory = new Label("Product Categories");
//        Label products = new Label("Products");
//        Label invoices = new Label("Invoices");
//        Label invoiceItems = new Label("Invoice Items");

        Text title = new Text("Sale Register");
        title.setFont(Font.font("Times new roman", FontWeight.BOLD, 20));


        Button customers = new Button("Customers");
        Button productCategory = new Button("Product Categories");
        Button products = new Button("Products");
        Button invoices = new Button("Invoices");
        Button invoiceItems = new Button("Invoice Items");

        TextField name = new TextField();
        name.setPromptText("what name");

//        HBox buttonRow = new HBox();
//        buttonRow.getChildren().addAll(customers, productCategory, products, invoices, invoiceItems);

        FlowPane buttonFlow = new FlowPane();
        buttonFlow.setPadding(new Insets(5,5,5,10));

        buttonFlow.setVgap(10);
        buttonFlow.setHgap(5);
        buttonFlow.setPrefWrapLength(100);
        buttonFlow.setStyle("-fx-background-color: DAE6F3");
//        buttonFlow.setAlignment(Pos.TOP_CENTER);

        buttonFlow.getChildren().addAll(customers, productCategory, products, invoices, invoiceItems);

        BorderPane mainBp = new BorderPane();
        mainBp.setPadding(new Insets(5,5,10,5));
        mainBp.setTop(title);
//        mainBp.setCenter(buttonRow);
        mainBp.setLeft(buttonFlow);

        Scene introScene = new Scene(mainBp, 800,800);
        introScene.setFill(Color.LIGHTBLUE);

        return introScene;
    }


    public Scene getFaktura() {

        GridPane bottomPart = new GridPane();
        bottomPart.setVgap(5);
        bottomPart.setHgap(5);
        bottomPart.setPadding(new Insets(5));

        Label t1 = new Label("test 1");
        Label t2 = new Label("test 2");

        HBox hBox = new HBox();
        bottomPart.add(hBox,40,0);
        hBox.getChildren().addAll(t1,t2);

        BorderPane mainBp = new BorderPane();
        mainBp.setTop(addTopLayout() );
        mainBp.setCenter(getMiddleTableView() );
        mainBp.setBottom(bottomPart);



        Scene fakturaScene = new Scene(mainBp,425,700);


        return fakturaScene;
    }

    private TableView getMiddleTableView() {
        TableView productTable = new TableView();
        productTable.setEditable(true);

        // table col names for the tableview
        TableColumn categoryCol = new TableColumn("Category Id");
        TableColumn descriptionCol = new TableColumn("Description");
        TableColumn quantityCol = new TableColumn("Quantity");
        TableColumn priceCol = new TableColumn("Price per unit");
        TableColumn sumQuanityCol = new TableColumn("Sum of quantity");

        //associate data with the table columns, through the properties defined for each data element. referencing to the methods of the InvoiceProducts

        categoryCol.setCellValueFactory(
                new PropertyValueFactory<InvoiceProducts,String>("categoryId")
        );
        descriptionCol.setCellValueFactory(
                new PropertyValueFactory<InvoiceProducts,String>("description")
        );
        quantityCol.setCellValueFactory(
                new PropertyValueFactory<InvoiceProducts,Integer>("quantity")
        );
        priceCol.setCellValueFactory(
                new PropertyValueFactory<InvoiceProducts,Integer>("pricePerUnit")
        );
        sumQuanityCol.setMaxWidth(100);
        sumQuanityCol.setCellValueFactory(
                new PropertyValueFactory<InvoiceProducts,Integer>("sumQuantity")
        );

        //  data model is defined, and the data is added and associated with the columns, you can add the data to the table by using the setItems()
        productTable.setItems(getProductObservableTable() );
        productTable.getColumns().addAll(categoryCol,descriptionCol,quantityCol,priceCol,sumQuanityCol);



        return productTable;
    }

    public ObservableList<InvoiceProducts> getProductObservableTable () {
        ObservableList<InvoiceProducts> table = FXCollections.observableArrayList(
            new InvoiceProducts("test cate","test desc", 3, 5, 15),
            new InvoiceProducts("test cate","test desc", 5, 5, 25)
        );

        return table;
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

    //    public String printLeftAdjusted(String name, String actual) {
//        int line = 25;
//        int arg1 = line - name.length();
//        int arg2 = line - actual.length();
//
//        return String.format("|%-"+arg1+"s %"+arg2+"s |",name,actual );
//    }



}
