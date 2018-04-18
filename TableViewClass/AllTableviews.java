package Oblig3.TableViewClass;

import Oblig3.DAOs.AddressDao;
import Oblig3.DAOs.ConnectionAdapter;
import Oblig3.DAOs.CustomerDAO;
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
    ConnectionAdapter sqlAdapter = new ConnectionAdapter();
    TableView mainTable;

//    public AllTableviews () {
//        generalTableview = new TableView();
//
//    }

    public TableView getAddressTableView () {
        String databaseTableName = "address";
        mainTable = new TableView();
        mainTable.setEditable(true);

        TableColumn<AddressObservable,Integer> addressIdCol = new TableColumn("Address Id");
        TableColumn streetNumberCol = new TableColumn("Street Number");
        TableColumn streetNameCol = new TableColumn("Street Name");
        TableColumn postalCodeCol = new TableColumn("Postal Code");
        TableColumn postalTownCol = new TableColumn("Postal Town");

        addressIdCol.setCellValueFactory(
                new PropertyValueFactory<AddressObservable,Integer>("addressId")
        );
        addressIdCol.setCellFactory(TextFieldTableCell.forTableColumn(
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
        addressIdCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<AddressObservable,Integer>>() {
                    @Override
                    public void handle (TableColumn.CellEditEvent<AddressObservable,Integer> t) {
                        sqlAdapter.updataDatabaseFromTableView(databaseTableName ,"address_id", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));

                        ( (AddressObservable) t.getTableView() // The TableView control upon which this event occurred.
                                .getItems()
                                .get( // Returns the element at the specified position in this list.
                                        t.getTablePosition() //The position upon which this event occurred.
                                                .getRow() // The row that this TablePosition represents in the TableView.
                                )
                        ).setAddressId (t.getNewValue().intValue() ); // set new value input by user in the cell
                    }
                }
        );

        streetNumberCol.setCellValueFactory(
                new PropertyValueFactory<AddressObservable,String>("streetNumber")
        );
        streetNumberCol.setCellFactory(TextFieldTableCell.forTableColumn());
        streetNumberCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<AddressObservable,String>>() {
                    @Override
                    public void handle (TableColumn.CellEditEvent<AddressObservable,String> t) {
                        sqlAdapter.updataDatabaseFromTableView(databaseTableName ,"street_number", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));

                        ( (AddressObservable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setStreetNumber( t.getNewValue() );
                    }
                }
        );

        streetNameCol.setCellValueFactory(
                new PropertyValueFactory<AddressObservable,String>("streetName")
        );
        streetNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        streetNameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<AddressObservable,String>>() {
                    @Override
                    public void handle (TableColumn.CellEditEvent<AddressObservable,String> t) {
                        sqlAdapter.updataDatabaseFromTableView(databaseTableName ,"street_name", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));

                        ( (AddressObservable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setStreetName( t.getNewValue() );
                    }
                }
        );

        postalCodeCol.setCellValueFactory(
                new PropertyValueFactory<AddressObservable,String>("postalCode")
        );
        postalCodeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        postalCodeCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<AddressObservable,String>>() {
                    @Override
                    public void handle (TableColumn.CellEditEvent<AddressObservable,String> t) {
                        sqlAdapter.updataDatabaseFromTableView(databaseTableName ,"postal_code", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));

                        ( (AddressObservable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setPostalCode( t.getNewValue() );
                    }
                }
        );
        postalTownCol.setCellValueFactory(
                new PropertyValueFactory<AddressObservable,String>("postalTown")
        );
        postalTownCol.setCellFactory(TextFieldTableCell.forTableColumn());
        postalTownCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<AddressObservable,String>>() {
                    @Override
                    public void handle (TableColumn.CellEditEvent<AddressObservable,String> t) {
                        sqlAdapter.updataDatabaseFromTableView(databaseTableName ,"postal_town", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));

                        ( (AddressObservable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setPostalTown( t.getNewValue() );
                    }
                }
        );

        AddressDao addressDao = new AddressDao();

        mainTable.setItems(addressDao.allAddressObservableList() );
        mainTable.getColumns().addAll(addressIdCol, streetNumberCol, streetNameCol,postalCodeCol,postalTownCol );

       return mainTable;

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

        mainTable = new TableView();
        mainTable.setEditable(true);

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
                        sqlAdapter.updataDatabaseFromTableView(databaseTableName ,"customer_name", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));

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
                        sqlAdapter.updataDatabaseFromTableView(databaseTableName ,"address", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));

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
                        sqlAdapter.updataDatabaseFromTableView(databaseTableName ,"phone_number", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));

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
                        sqlAdapter.updataDatabaseFromTableView(databaseTableName ,"billing_account", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));

                        ( (CustomerObservable) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setBillingAccount( t.getNewValue() );
                    }
                }
        );

        CustomerDAO customerDAO = new CustomerDAO();

//        table.setItems(getCustomerObservableTable() );  //make method to put all values from database into observable list
        mainTable.setItems(customerDAO.allCustomerObservableList());
        mainTable.getColumns().addAll(customerIdCol, customerNameCol, addressCol, phoneCol, billingAccountCol );

        return mainTable;
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

        return productTable;
    }


    public TableView getInvoiceProductsMiddleTableView() {
        mainTable = new TableView();
        mainTable.setEditable(true);

        // table col names for the tableview
        TableColumn categoryCol = new TableColumn("Category");
        TableColumn descriptionCol = new TableColumn("Description");
        TableColumn<InvoiceProducts,Integer> quantityCol = new TableColumn("Quantity");
        TableColumn<InvoiceProducts,Integer> priceCol = new TableColumn("Price per unit");
        TableColumn<InvoiceProducts,Integer> sumQuanityCol = new TableColumn("Sum of quantity");

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
        mainTable.setItems(getTestInvoiceProductObservableTable() );
        mainTable.getColumns().addAll(categoryCol,descriptionCol,quantityCol,priceCol,sumQuanityCol);


        return mainTable;
    }


    public void addToCustomerObservableTable (CustomerObservable customer) {

        mainTable.getItems().add(customer);

//        ObservableList<CustomerObservable> table = FXCollections.observableArrayList(
//                new CustomerObservable(77,"test name",4,"0040 0000","test account")
//        );

//        return table;
    }

    public TableView getMainTable () {
        return mainTable;
    }

    public void addInvoiceProductObservableTable (InvoiceProducts invoiceProducts) {
        mainTable.getItems().add(invoiceProducts);
    }

    public ObservableList<InvoiceProducts> getTestInvoiceProductObservableTable () {
        ObservableList<InvoiceProducts> table = FXCollections.observableArrayList(
                new InvoiceProducts("test cate","test desc", 3, 5, 15),
                new InvoiceProducts("test cate","test desc", 5, 5, 25)

        );


        return table;
    }

}
