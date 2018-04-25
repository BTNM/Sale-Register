package Oblig3;

import Oblig3.DAOs.*;
import Oblig3.SQLReadFiles.*;
import Oblig3.TableViewClass.AllTableviews;
import Oblig3.TableViewClass.CustomerObservable;
import Oblig3.TableViewClass.InvoiceProducts;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    static ConnectionAdapter adapter = new ConnectionAdapter();
    static AllTableviews allTables = new AllTableviews();

    // prøvde å begynne med all logic delen forså å gjøre GUI senere, deretter begynte jeg å bruke Fxml men da greide jeg ikke å koble begge delene sammen
    // det ble litt rot når jeg prøvde å koble logikk delen med fxml som opererte med litt forkjellig måte
    // så getCustomerScene() og getFakturaScene() logikken virker såpass, der man kan endre direkte i tabbleview og endringene blir sent tilbake til databasen
    // trenger bare å copy paste de virkende delene til tableview til de andre klassene, men får ikke koblet det til fxml så venter med å implementere de andre scenene

    public static void main(String[] args) {
//        File file = new File(SqlAndQueryFromFile.datebasePath);
        File file = new File(ConnectionAdapter.datebasePath);
        if(!file.exists()) {
            adapter.startUpFromSqlFile(ConnectionAdapter.sqlQueryPath);
        }


        // i hver scene kan lage filter methode/knapp etc som filtrerer eks all invoice til en kunde eller alle produktene til hver kategori

        //start javafx start() method
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("FxmlFiles/intro.fxml"));
//        Scene startScene = new Scene(root,800,600);
//        primaryStage.setScene(startScene);
//        primaryStage.setTitle("Sale Register System");

        // øvre del er fxml med user interaction, og nedre delen er individuelle scener med mesteparten av logikk og atferden i programmet. Der man kan endre info i tableview direkte i det grafiske grensesnittet og endringene blir sendt tilbake til databasen
        // klarte ikke å kombinere UI og logikken sammen, men de virker individuelt,
        // introScene() er tom scene siden jeg prøvde å bruke fxml til å løse UI midtvei, så du sjekke scenene individuelt

//        primaryStage.setScene(getIntroScene() );
        primaryStage.setScene(getCustomerScene() );
//        primaryStage.setScene(getAddressScene() );
//        primaryStage.setScene(getFakturaScene() );
        primaryStage.show();

    }

    public Scene getIntroScene () {
        Text title = new Text("Sale Register");
        title.setFont(Font.font("Times new roman", FontWeight.BOLD, 20));

        Button home = new Button("Home page");
        Button customers = new Button("Customers");
        Button productCategory = new Button("Product Categories");
        Button products = new Button("Products");
        Button invoices = new Button("Invoices");
        Button invoiceItems = new Button("Invoice Items");

//        home.setOnAction( e -> {
//            mainWindow.setScene(getIntro() );
//        });
//        invoices.setOnAction(e -> {
//            mainWindow.setScene(getFaktura());
//        });


//        TextField name = new TextField();
//        name.setPromptText("what name");

//        HBox buttonRow = new HBox();
//        buttonRow.getChildren().addAll(customers, productCategory, products, invoices, invoiceItems);

        FlowPane buttonFlow = new FlowPane();
        buttonFlow.setPadding(new Insets(5,5,5,10));

        buttonFlow.setVgap(10);
        buttonFlow.setHgap(5);
        buttonFlow.setPrefWrapLength(100);
        buttonFlow.setStyle("-fx-background-color: DAE6F3");
//        buttonFlow.setAlignment(Pos.TOP_CENTER);

        buttonFlow.getChildren().addAll(home, customers, productCategory, products, invoices, invoiceItems);

        BorderPane mainBp = new BorderPane();
        mainBp.setPadding(new Insets(5,5,10,5));
        mainBp.setTop(title);
//        mainBp.setCenter(buttonRow);
        mainBp.setLeft(buttonFlow);

        Scene introScene = new Scene(mainBp, 600,600);
        introScene.setFill(Color.LIGHTBLUE);

        return introScene;
    }


    public Scene getFakturaScene() {

        TextField cateIdInput = new TextField();
        cateIdInput.setPromptText("Category Id");

        TextField descInput = new TextField();
        descInput.setPromptText("Description");

        TextField quantityInput = new TextField();
        quantityInput.setPromptText("Quantity");

        TextField pericePerUnitInput = new TextField();
        pericePerUnitInput.setPromptText("Price Per Unit");

        TextField sumOfQuantityInout = new TextField();
        sumOfQuantityInout.setPromptText("Billing Account");

        Button addBtn = new Button("Add");
        addBtn.setOnAction(e -> addDetailedInvoice(cateIdInput.getText(),descInput.getText(),Integer.valueOf(quantityInput.getText()),Integer.valueOf(pericePerUnitInput.getText() ),Integer.valueOf(sumOfQuantityInout.getText()  )));

        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(e -> deleteInvoiceProductsFromTable());

        HBox bottomLayout1 = new HBox();
        HBox bottomLayout2 = new HBox();
        bottomLayout1.setPadding(new Insets(5));
        bottomLayout2.setPadding(new Insets(5));
        bottomLayout1.getChildren().addAll(cateIdInput,descInput,quantityInput );
        bottomLayout2.getChildren().addAll(pericePerUnitInput,sumOfQuantityInout,addBtn ,deleteBtn);

        VBox bottomContainer = new VBox();
        bottomContainer.getChildren().addAll(bottomLayout1, bottomLayout2);

        BorderPane mainBp = new BorderPane();
        mainBp.setTop(addInvoiceProductsToplayout() );
        mainBp.setCenter(allTables.getInvoiceProductsMiddleTableView() );
        mainBp.setBottom(bottomContainer);



        Scene fakturaScene = new Scene(mainBp,425,700);


        return fakturaScene;
    }

    public Scene getCustomerScene () {

        TextField customerIdInput = new TextField();
        customerIdInput.setPromptText("Customer Id");

        TextField customerNameInput = new TextField();
        customerNameInput.setPromptText("Customer Name");

        TextField addressInput = new TextField();
        addressInput.setPromptText("Address");

        TextField phoneNrInput = new TextField();
        phoneNrInput.setPromptText("Phone Number");

        TextField billingAccountInput = new TextField();
        billingAccountInput.setPromptText("Billing Account");

        CustomerObservable table;

        Button addBtn = new Button("Add");
        addBtn.setOnAction(e -> addCustomerFromTable(Integer.valueOf(customerIdInput.getText()),customerNameInput.getText(),Integer.valueOf(addressInput.getText()),phoneNrInput.getText(),billingAccountInput.getText() ));

        Button deleteBtn = new Button("Delete");
        deleteBtn.setOnAction(e -> deleteCustomerFromTable());

        HBox bottomLayout1 = new HBox();
        HBox bottomLayout2 = new HBox();
        bottomLayout1.setPadding(new Insets(5));
        bottomLayout2.setPadding(new Insets(5));
        bottomLayout1.getChildren().addAll(customerIdInput,customerNameInput,addressInput );
        bottomLayout2.getChildren().addAll(phoneNrInput,billingAccountInput,addBtn ,deleteBtn);

        VBox bottomContainer = new VBox();
        bottomContainer.getChildren().addAll(bottomLayout1, bottomLayout2);

        BorderPane mainBp = new BorderPane();
        mainBp.setCenter(allTables.getCustomerTableView() );
        mainBp.setBottom(bottomContainer);

        Scene customerScene = new Scene(mainBp,600,600);

        return customerScene;
    }

    private void addDetailedInvoice (String cId, String desc, int qty, int ppu, int sum) {
        InvoiceProducts detailedInvoice = new InvoiceProducts(cId,desc,qty,ppu,sum);
        allTables.addInvoiceProductObservableTable(detailedInvoice);

    }
    private void deleteInvoiceProductsFromTable() {
        TableView mainTable = allTables.getMainTable();
        ObservableList<InvoiceProducts> invoiceSelected, allInvoices;
        allInvoices = mainTable.getItems();
        invoiceSelected = mainTable.getSelectionModel().getSelectedItems();

        invoiceSelected.forEach(allInvoices::remove);

    }

    private void addCustomerFromTable(int cId, String cName,int add, String phone, String bAccount) {
        CustomerObservable c  = new CustomerObservable(cId,cName,add,phone,bAccount);
        adapter.insertCustomerIntoDatabase("customer",cId,cName,add, phone,bAccount);
        allTables.addToCustomerObservableTable(c);

    }

    private void deleteCustomerFromTable() {
        TableView mainTable = allTables.getMainTable();
        ObservableList<CustomerObservable> customerSelected, allCustomer;
        allCustomer = mainTable.getItems();
        customerSelected = mainTable.getSelectionModel().getSelectedItems();

        customerSelected.forEach(allCustomer::remove);

    }





    public Scene getAddressScene () {

        BorderPane mainBp = new BorderPane();
        mainBp.setCenter(allTables.getAddressTableView());


        Scene customerScene = new Scene(mainBp,400,600);

        return customerScene;
    }
//
//    private TableView getCustomerTableView () {
//        TableView customerTable = new TableView();
//        customerTable.setEditable(true);
//
//        TableColumn<CustomerObservable,Integer> customerIdCol = new TableColumn("Customer Id");
//        TableColumn customerNameCol = new TableColumn("Customer Name");
//        TableColumn<CustomerObservable,Integer>  addressCol = new TableColumn("Address");
//        TableColumn phoneCol = new TableColumn("Phone Nummer");
//        TableColumn billingAccountCol = new TableColumn("Billing Account");
//
//        customerIdCol.setCellValueFactory(
//                new PropertyValueFactory<CustomerObservable,Integer>("customerId")
//        );
//        // reimplement the table cell as a text field with the textFieldTableCell, setOnEditCommit process edit and update value of corresponding cell
//        //Provides a TextField that allows editing of the cell content when the cell is double-clicked
//        customerIdCol.setCellFactory(TextFieldTableCell.forTableColumn(
//                new StringConverter<Integer>() {
//                    @Override
//                    public String toString(Integer object) {
//                        return object.toString();
//                    }
//
//                    @Override
//                    public Integer fromString(String string) {
//                        return Integer.parseInt(string);
//                    }
//                }
//        ));
//        customerIdCol.setOnEditCommit(
//                new EventHandler<TableColumn.CellEditEvent<CustomerObservable,Integer>>() {
//                    @Override
//                    public void handle (TableColumn.CellEditEvent<CustomerObservable,Integer> t) {
//                        ( (CustomerObservable) t.getTableView().getItems().get( // The TableView control upon which this event occurred. //The position upon which this event occurred.
//                                t.getTablePosition().getRow())
//                        ).setCustomerId(t.getNewValue().intValue() ); // set new value input by user in the cell
//
//                        CustomerObservable c = t.getRowValue();
//                        c.setCustomerId(t.getNewValue());
//                        readSql.updataDatabaseFromTableView("customer_id", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));
//                    }
//                }
//        );
//
//        customerNameCol.setCellValueFactory(
//                new PropertyValueFactory<CustomerObservable,String>("customerName")
//        );
//        customerNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
//        customerNameCol.setOnEditCommit(
//                new EventHandler<TableColumn.CellEditEvent<CustomerObservable,String>>() {
//                    @Override
//                    public void handle (TableColumn.CellEditEvent<CustomerObservable,String> t) {
//                        ( (CustomerObservable) t.getTableView().getItems().get(
//                                t.getTablePosition().getRow())
//                        ).setCustomerName( t.getNewValue() );
//                    }
//                }
//        );
//        addressCol.setCellValueFactory(
//                new PropertyValueFactory<CustomerObservable,Integer>("addressId")
//        );
//        addressCol.setCellFactory(TextFieldTableCell.forTableColumn(
//                new StringConverter<Integer>() {
//                    @Override
//                    public String toString(Integer object) {
//                        return object.toString();
//                    }
//
//                    @Override
//                    public Integer fromString(String string) {
//                        return Integer.parseInt(string);
//                    }
//                }
//        ));
//        addressCol.setOnEditCommit(
//                new EventHandler<TableColumn.CellEditEvent<CustomerObservable,Integer>>() {
//                    @Override
//                    public void handle (TableColumn.CellEditEvent<CustomerObservable,Integer> t) {
//                        ( (CustomerObservable) t.getTableView().getItems().get(
//                                t.getTablePosition().getRow())
//                        ).setAddressId(t.getNewValue().intValue()  );
//                    }
//                }
//        );
//        phoneCol.setCellValueFactory(
//                new PropertyValueFactory<CustomerObservable,String>("phoneNumber")
//        );
//        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
//        phoneCol.setOnEditCommit(
//                new EventHandler<TableColumn.CellEditEvent<CustomerObservable,String>>() {
//                    @Override
//                    public void handle (TableColumn.CellEditEvent<CustomerObservable,String> t) {
//                        ( (CustomerObservable) t.getTableView().getItems().get(
//                                t.getTablePosition().getRow())
//                        ).setPhoneNumber( t.getNewValue() );
//                    }
//                }
//        );
//        billingAccountCol.setCellValueFactory(
//                new PropertyValueFactory<CustomerObservable,String>("billingAccount")
//        );
//        billingAccountCol.setCellFactory(TextFieldTableCell.forTableColumn() );
//        billingAccountCol.setOnEditCommit(
//                new EventHandler<TableColumn.CellEditEvent<CustomerObservable,String>>() {
//                    @Override
//                    public void handle (TableColumn.CellEditEvent<CustomerObservable,String> t) {
//                        ( (CustomerObservable) t.getTableView().getItems().get(
//                                t.getTablePosition().getRow())
//                        ).setBillingAccount( t.getNewValue() );
//                    }
//                }
//        );
//
//        customerTable.setItems(getCustomerObservableTable() );  //make method to put all values from database into observable list
//        customerTable.getColumns().addAll(customerIdCol, customerNameCol, addressCol, phoneCol, billingAccountCol );
//
//        return customerTable;
//    }


//    private TableView getInvoiceProductsMiddleTableView() {
//        TableView productTable = new TableView();
//        productTable.setEditable(true);
//
//
//        // table col names for the tableview
//        TableColumn categoryCol = new TableColumn("Category Id");
//        TableColumn descriptionCol = new TableColumn("Description");
//        TableColumn quantityCol = new TableColumn("Quantity");
//        TableColumn priceCol = new TableColumn("Price per unit");
//        TableColumn sumQuanityCol = new TableColumn("Sum of quantity");
//
//        //associate data with the table columns, through the properties defined for each data element. referencing to the methods of the InvoiceProducts
//        categoryCol.setCellValueFactory(
//                new PropertyValueFactory<InvoiceProducts,String>("categoryId")
//        );
//        categoryCol.setCellFactory(TextFieldTableCell.forTableColumn());
//        categoryCol.setOnEditCommit(
//                new EventHandler<TableColumn.CellEditEvent<InvoiceProducts,String>>() {
//                    @Override
//                    public void handle (TableColumn.CellEditEvent<InvoiceProducts,String> t) {
//                        ( (InvoiceProducts) t.getTableView().getItems().get(
//                                t.getTablePosition().getRow())
//                        ).setCategoryId( t.getNewValue() );
//                    }
//                }
//        );
//        descriptionCol.setCellValueFactory(
//                new PropertyValueFactory<InvoiceProducts,String>("description")
//        );
//        descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
//        descriptionCol.setOnEditCommit(
//                new EventHandler<TableColumn.CellEditEvent<InvoiceProducts,String>>() {
//                    @Override
//                    public void handle (TableColumn.CellEditEvent<InvoiceProducts,String> t) {
//                        ( (InvoiceProducts) t.getTableView().getItems().get(
//                                t.getTablePosition().getRow())
//                        ).setCategoryId( t.getNewValue() );
//                    }
//                }
//        );
//        quantityCol.setCellValueFactory(
//                new PropertyValueFactory<InvoiceProducts,Integer>("quantity")
//        );
//        quantityCol.setCellFactory(TextFieldTableCell.forTableColumn(
//                new StringConverter<Integer>() {
//                    @Override
//                    public String toString(Integer object) {
//                        return object.toString();
//                    }
//
//                    @Override
//                    public Integer fromString(String string) {
//                        return Integer.parseInt(string);
//                    }
//                }
//        ));
//        quantityCol.setOnEditCommit(
//                new EventHandler<TableColumn.CellEditEvent<InvoiceProducts,Integer>>() {
//                    @Override
//                    public void handle (TableColumn.CellEditEvent<InvoiceProducts,Integer> t) {
//                        ( (InvoiceProducts) t.getTableView().getItems().get(
//                                t.getTablePosition().getRow())
//                        ).setQuantity(t.getNewValue().intValue()  );
//                    }
//                }
//        );
//        priceCol.setCellValueFactory(
//                new PropertyValueFactory<InvoiceProducts,Integer>("pricePerUnit")
//        );
//
//        priceCol.setCellFactory(TextFieldTableCell.forTableColumn(
//                new StringConverter<Integer>() {
//                    @Override
//                    public String toString(Integer object) {
//                        return object.toString();
//                    }
//
//                    @Override
//                    public Integer fromString(String string) {
//                        return Integer.parseInt(string);
//                    }
//                }
//        ));
//        priceCol.setOnEditCommit(
//                new EventHandler<TableColumn.CellEditEvent<InvoiceProducts,Integer>>() {
//                    @Override
//                    public void handle (TableColumn.CellEditEvent<InvoiceProducts,Integer> t) {
//                        ( (InvoiceProducts) t.getTableView().getItems().get(
//                                t.getTablePosition().getRow())
//                        ).setPricePerUnit(t.getNewValue().intValue()  );
//                    }
//                }
//        );
//
//        sumQuanityCol.setMaxWidth(100);
//        sumQuanityCol.setCellValueFactory(
//                new PropertyValueFactory<InvoiceProducts,Integer>("sumQuantity")
//        );
//        sumQuanityCol.setCellFactory(TextFieldTableCell.forTableColumn(
//                new StringConverter<Integer>() {
//                    @Override
//                    public String toString(Integer object) {
//                        return object.toString();
//                    }
//
//                    @Override
//                    public Integer fromString(String string) {
//                        return Integer.parseInt(string);
//                    }
//                }
//        ));
//        sumQuanityCol.setOnEditCommit(
//                new EventHandler<TableColumn.CellEditEvent<InvoiceProducts,Integer>>() {
//                    @Override
//                    public void handle (TableColumn.CellEditEvent<InvoiceProducts,Integer> t) {
//                        ( (InvoiceProducts) t.getTableView().getItems().get(
//                                t.getTablePosition().getRow())
//                        ).setSumQuantity(t.getNewValue().intValue()  );
//                    }
//                }
//        );
//
//        //  data model is defined, and the data is added and associated with the columns, you can add the data to the table by using the setItems()
//        productTable.setItems(getInvoiceProductObservableTable() );
//        productTable.getColumns().addAll(categoryCol,descriptionCol,quantityCol,priceCol,sumQuanityCol);
//
//
//
//        return productTable;
//    }

//    public ObservableList<CustomerObservable> getCustomerObservableTable () {
//        ObservableList<CustomerObservable> table = FXCollections.observableArrayList(
//            new CustomerObservable(1,"test name",4,"0000 0000","test account")
//        );
//
//        return table;
//    }
//
//    public ObservableList<InvoiceProducts> getInvoiceProductObservableTable () {
//        ObservableList<InvoiceProducts> table = FXCollections.observableArrayList(
//            new InvoiceProducts("test cate","test desc", 3, 5, 15),
//            new InvoiceProducts("test cate","test desc", 5, 5, 25)
//
//        );
//
//
//        return table;
//    }


//    public InvoiceProducts getSqlData () {
//        Customer customer = readSql.getCustomerById(1);
//        Category category = readSql.getCategoryById(1);
//        Product product = readSql.getProductById(1);
//        Invoice invoice = readSql.getInvoiceById(customer.getCustomer_id() );
//        Invoice_items invoiceItems = readSql.getInvoiceItemsById(invoice.getInvoice_id());
//
//
//
////        return new InvoiceProducts();
//    }

    public GridPane addInvoiceProductsToplayout() {
//        Customer customer = readSql.getCustomerById(1);
//        Address address = readSql.getAddressById(customer.getAddress()); // 1
//        Invoice invoice = readSql.getInvoiceById(customer.getCustomer_id());
//        Product product = readSql.getProductById(customer.getAddress());

        CustomerDAO customerDAO = new CustomerDAO();
        AddressDao addressDao = new AddressDao();
        InvoiceDao invoiceDao = new InvoiceDao();
        ProductDao productDao = new ProductDao();

        Customer customer = customerDAO.getCustomerById(1);
        Address address = addressDao.getAddressById(customer.getCustomer_id() );


        VBox customerInfo = new VBox();
        customerInfo.setSpacing(5); // gap between nodes

        Label customerName = new Label(customer.getCustomer_name());

        Label customerStreet = new Label(address.getStreet_name()+ " " + address.getStreet_number() );
        Label customerPostal = new Label(address.getPostal_code()+ " " + address.getPostal_town() );

        Label phoneNumber = new Label(customer.getPhone_number());
        Label billingAccount = new Label(customer.getBilling_account());

        customerInfo.getChildren().addAll(customerName, customerStreet, customerPostal, phoneNumber, billingAccount);


        Invoice invoice = invoiceDao.getInvoiceById(customer.getCustomer_id() );

        VBox fakturaInfo = new VBox();
        fakturaInfo.setSpacing(5);

//        fakturaInfo.setAlignment(Pos.TOP_RIGHT);

//        String.format("%-5s %-5s","CustomerId: ", invoice.getCustomer());

//        Label customerId = new Label(printLeftAdjusted("CustomerId: ", String .valueOf(invoice.getCustomer()) ));
//        Label invoiceId = new Label(printLeftAdjusted("InvoiceId: ",String.valueOf(invoice.getInvoice_id()) ));
//        Label invoiceDate = new Label(printLeftAdjusted("InvoiceDate: ",invoice.getDato() ));
//
//        fakturaInfo.getChildren().addAll(customerId, invoiceId, invoiceDate);

        Label invoiceTitle = new Label("Invoice/Faktura");
        invoiceTitle.setFont(Font.font("Times New Roman",FontWeight.BOLD,14));

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

        fakturaInfo.getChildren().addAll(invoiceTitle, row1,row2, row3);

        Product product = productDao.getProductById(customer.getAddress() );

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
