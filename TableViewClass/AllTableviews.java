package Oblig3.TableViewClass;

import Oblig3.DAOs.ConnectionAdapter;
import Oblig3.SqlAndQueryFromFile;
import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;


public class AllTableviews {
//    TableView generalTableview;
//    static SqlAndQueryFromFile readSql = new SqlAndQueryFromFile();
    ConnectionAdapter sqlAdapter = new ConnectionAdapter();

//    static AllTableviews allTableviews = new AllTableviews();

//    public AllTableviews () {
//        generalTableview = new TableView();
//
//    }

    public TableView getAddressTableView () {
        String databaseTableName = "address";
        TableView addressTable = new TableView();
        addressTable.setEditable(true);

        TableColumn<CustomerObservable,Integer> customerIdCol = new TableColumn("Customer Id");
        TableColumn customerNameCol = new TableColumn("Customer Name");
        TableColumn<CustomerObservable,Integer>  addressCol = new TableColumn("Address");
        TableColumn phoneCol = new TableColumn("Phone Nummer");
        TableColumn billingAccountCol = new TableColumn("Billing Account");

        return addressTable;
    }
    public TableView getCategoryTableView () {
        String databaseTableName = "category";
        TableView categoryTable = new TableView();
        categoryTable.setEditable(true);

        TableColumn<CustomerObservable,Integer> customerIdCol = new TableColumn("Customer Id");
        TableColumn customerNameCol = new TableColumn("Customer Name");
        TableColumn<CustomerObservable,Integer>  addressCol = new TableColumn("Address");
        TableColumn phoneCol = new TableColumn("Phone Nummer");
        TableColumn billingAccountCol = new TableColumn("Billing Account");

        return categoryTable;
    }

    public TableView getCustomerTableView () {
        String databaseTableName = "customer";
        TableView customerTable = new TableView();
        customerTable.setEditable(true);

        TableColumn<CustomerObservable,Integer> customerIdCol = new TableColumn("Customer Id");
        TableColumn customerNameCol = new TableColumn("Customer Name");
        TableColumn<CustomerObservable,Integer>  addressCol = new TableColumn("Address");
        TableColumn phoneCol = new TableColumn("Phone Nummer");
        TableColumn billingAccountCol = new TableColumn("Billing Account");

        customerIdCol.setCellValueFactory(
                new PropertyValueFactory<CustomerObservable,Integer>("customerId")
        );
        // reimplement the table cell as a text field with the textFieldTableCell, setOnEditCommit process edit and update value of corresponding cell
        //Provides a TextField that allows editing of the cell content when the cell is double-clicked
        customerIdCol.setCellFactory(TextFieldTableCell.forTableColumn(
                new StringConverter<Integer>() {
                    @Override
                    public String toString(Integer object) {
                        return object.toString();
                    }

                    @Override
                    public Integer fromString(String string) {
                        return Integer.parseInt(string);
                    }
                }
        ));
        customerIdCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<CustomerObservable,Integer>>() {
                    @Override
                    public void handle (TableColumn.CellEditEvent<CustomerObservable,Integer> t) {
                        sqlAdapter.updataDatabaseFromTableView(databaseTableName ,"customer_id", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));

                        ( (CustomerObservable) t.getTableView() // The TableView control upon which this event occurred.
                                .getItems()
                                .get( // Returns the element at the specified position in this list.
                                        t.getTablePosition() //The position upon which this event occurred.
                                            .getRow() // The row that this TablePosition represents in the TableView.
                                    )
                        ).setCustomerId(t.getNewValue().intValue() ); // set new value input by user in the cell

                    }
                }
        );

        customerNameCol.setCellValueFactory(
                new PropertyValueFactory<CustomerObservable,String>("customerName")
        );
        customerNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        customerNameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<CustomerObservable,String>>() {
                    @Override
                    public void handle (TableColumn.CellEditEvent<CustomerObservable,String> t) {
                        ( (CustomerObservable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setCustomerName( t.getNewValue() );
                    }
                }
        );
        addressCol.setCellValueFactory(
                new PropertyValueFactory<CustomerObservable,Integer>("addressId")
        );
        addressCol.setCellFactory(TextFieldTableCell.forTableColumn(
                new StringConverter<Integer>() {
                    @Override
                    public String toString(Integer object) {
                        return object.toString();
                    }

                    @Override
                    public Integer fromString(String string) {
                        return Integer.parseInt(string);
                    }
                }
        ));
        addressCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<CustomerObservable,Integer>>() {
                    @Override
                    public void handle (TableColumn.CellEditEvent<CustomerObservable,Integer> t) {
                        ( (CustomerObservable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setAddressId(t.getNewValue().intValue()  );
                    }
                }
        );
        phoneCol.setCellValueFactory(
                new PropertyValueFactory<CustomerObservable,String>("phoneNumber")
        );
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<CustomerObservable,String>>() {
                    @Override
                    public void handle (TableColumn.CellEditEvent<CustomerObservable,String> t) {
                        ( (CustomerObservable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setPhoneNumber( t.getNewValue() );
                    }
                }
        );
        billingAccountCol.setCellValueFactory(
                new PropertyValueFactory<CustomerObservable,String>("billingAccount")
        );
        billingAccountCol.setCellFactory(TextFieldTableCell.forTableColumn() );
        billingAccountCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<CustomerObservable,String>>() {
                    @Override
                    public void handle (TableColumn.CellEditEvent<CustomerObservable,String> t) {
                        ( (CustomerObservable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setBillingAccount( t.getNewValue() );
                    }
                }
        );

        customerTable.setItems(getCustomerObservableTable() );  //make method to put all values from database into observable list
        customerTable.getColumns().addAll(customerIdCol, customerNameCol, addressCol, phoneCol, billingAccountCol );

        return customerTable;
    }

    public TableView getInvoiceTableView () {
        String databaseTableName = "invoice";
        TableView invoiceTable = new TableView();
        invoiceTable.setEditable(true);

        TableColumn<CustomerObservable,Integer> customerIdCol = new TableColumn("Customer Id");
        TableColumn customerNameCol = new TableColumn("Customer Name");
        TableColumn<CustomerObservable,Integer>  addressCol = new TableColumn("Address");
        TableColumn phoneCol = new TableColumn("Phone Nummer");
        TableColumn billingAccountCol = new TableColumn("Billing Account");

        return invoiceTable;
    }

    public TableView getInvoiceItemsTableView () {
        String databaseTableName = "invoice_items";
        TableView invoiceItemsTable = new TableView();
        invoiceItemsTable.setEditable(true);

        TableColumn<CustomerObservable,Integer> customerIdCol = new TableColumn("Customer Id");
        TableColumn customerNameCol = new TableColumn("Customer Name");
        TableColumn<CustomerObservable,Integer>  addressCol = new TableColumn("Address");
        TableColumn phoneCol = new TableColumn("Phone Nummer");
        TableColumn billingAccountCol = new TableColumn("Billing Account");

        return invoiceItemsTable;
    }

    public TableView getProductTableView () {
        String databaseTableName = "product";
        TableView productTable = new TableView();
        productTable.setEditable(true);

        TableColumn<CustomerObservable,Integer> customerIdCol = new TableColumn("Customer Id");
        TableColumn customerNameCol = new TableColumn("Customer Name");
        TableColumn<CustomerObservable,Integer>  addressCol = new TableColumn("Address");
        TableColumn phoneCol = new TableColumn("Phone Nummer");
        TableColumn billingAccountCol = new TableColumn("Billing Account");

        return pr
    }


    public TableView getInvoiceProductsMiddleTableView() {
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
        categoryCol.setCellFactory(TextFieldTableCell.forTableColumn());
        categoryCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<InvoiceProducts,String>>() {
                    @Override
                    public void handle (TableColumn.CellEditEvent<InvoiceProducts,String> t) {
                        ( (InvoiceProducts) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setCategoryId( t.getNewValue() );
                    }
                }
        );
        descriptionCol.setCellValueFactory(
                new PropertyValueFactory<InvoiceProducts,String>("description")
        );
        descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<InvoiceProducts,String>>() {
                    @Override
                    public void handle (TableColumn.CellEditEvent<InvoiceProducts,String> t) {
                        ( (InvoiceProducts) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setCategoryId( t.getNewValue() );
                    }
                }
        );
        quantityCol.setCellValueFactory(
                new PropertyValueFactory<InvoiceProducts,Integer>("quantity")
        );
        quantityCol.setCellFactory(TextFieldTableCell.forTableColumn(
                new StringConverter<Integer>() {
                    @Override
                    public String toString(Integer object) {
                        return object.toString();
                    }

                    @Override
                    public Integer fromString(String string) {
                        return Integer.parseInt(string);
                    }
                }
        ));
        quantityCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<InvoiceProducts,Integer>>() {
                    @Override
                    public void handle (TableColumn.CellEditEvent<InvoiceProducts,Integer> t) {
                        ( (InvoiceProducts) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setQuantity(t.getNewValue().intValue()  );
                    }
                }
        );
        priceCol.setCellValueFactory(
                new PropertyValueFactory<InvoiceProducts,Integer>("pricePerUnit")
        );

        priceCol.setCellFactory(TextFieldTableCell.forTableColumn(
                new StringConverter<Integer>() {
                    @Override
                    public String toString(Integer object) {
                        return object.toString();
                    }

                    @Override
                    public Integer fromString(String string) {
                        return Integer.parseInt(string);
                    }
                }
        ));
        priceCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<InvoiceProducts,Integer>>() {
                    @Override
                    public void handle (TableColumn.CellEditEvent<InvoiceProducts,Integer> t) {
                        ( (InvoiceProducts) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setPricePerUnit(t.getNewValue().intValue()  );
                    }
                }
        );

        sumQuanityCol.setMaxWidth(100);
        sumQuanityCol.setCellValueFactory(
                new PropertyValueFactory<InvoiceProducts,Integer>("sumQuantity")
        );
        sumQuanityCol.setCellFactory(TextFieldTableCell.forTableColumn(
                new StringConverter<Integer>() {
                    @Override
                    public String toString(Integer object) {
                        return object.toString();
                    }

                    @Override
                    public Integer fromString(String string) {
                        return Integer.parseInt(string);
                    }
                }
        ));
        sumQuanityCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<InvoiceProducts,Integer>>() {
                    @Override
                    public void handle (TableColumn.CellEditEvent<InvoiceProducts,Integer> t) {
                        ( (InvoiceProducts) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setSumQuantity(t.getNewValue().intValue()  );
                    }
                }
        );

        //  data model is defined, and the data is added and associated with the columns, you can add the data to the table by using the setItems()
        productTable.setItems(getInvoiceProductObservableTable() );
        productTable.getColumns().addAll(categoryCol,descriptionCol,quantityCol,priceCol,sumQuanityCol);



        return productTable;
    }




    public ObservableList<CustomerObservable> getCustomerObservableTable () {
        ObservableList<CustomerObservable> table = FXCollections.observableArrayList(
                new CustomerObservable(77,"test name",4,"0040 0000","test account")
        );

        return table;
    }

    public ObservableList<InvoiceProducts> getInvoiceProductObservableTable () {
        ObservableList<InvoiceProducts> table = FXCollections.observableArrayList(
                new InvoiceProducts("test cate","test desc", 3, 5, 15),
                new InvoiceProducts("test cate","test desc", 5, 5, 25)

        );


        return table;
    }


}
