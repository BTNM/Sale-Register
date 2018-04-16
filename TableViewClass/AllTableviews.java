package Oblig3.TableViewClass;

import Oblig3.DAOs.ConnectionAdapter;
import Oblig3.SqlAndQueryFromFile;
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

    static SqlAndQueryFromFile readSql = new SqlAndQueryFromFile();
    ConnectionAdapter sqlAdapter = new ConnectionAdapter();

//    static AllTableviews allTableviews = new AllTableviews();

//    public AllTableviews () {
//        generalTableview = new TableView();
//
//    }



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
                        sqlAdapter.updataDatabaseFromTableView(databaseTableName,"customer_id", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));

                        ( (CustomerObservable) t.getTableView().getItems().get( // The TableView control upon which this event occurred. //The position upon which this event occurred.
                                t.getTablePosition().getRow())
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







    public ObservableList<CustomerObservable> getCustomerObservableTable () {
        ObservableList<CustomerObservable> table = FXCollections.observableArrayList(
                new CustomerObservable(1,"test name",4,"0040 0000","test account")
        );

        return table;
    }



}
