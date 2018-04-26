package Oblig3.Controllers;

import Oblig3.DAOs.ConnectionAdapter;
import Oblig3.DAOs.CustomerDAO;
import Oblig3.TableViewClass.AllTableviews;
import Oblig3.TableViewClass.CustomerObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class customerController implements Initializable {
    ConnectionAdapter sqlAdapter = new ConnectionAdapter();
    AllTableviews allTables = new AllTableviews();
    CustomerDAO customerDAO = new CustomerDAO();
    String databaseTableName = "customer";

    TableView centerTable;

    @FXML BorderPane mainPane;
    @FXML TableView customerTable;

    @FXML TableColumn<CustomerObservable, Integer> customerIdCol;
    @FXML TableColumn<CustomerObservable, String> customerNameCol;
    @FXML TableColumn<CustomerObservable, Integer> addressCol;
    @FXML TableColumn<CustomerObservable, String> phoneNumberCol;
    @FXML TableColumn<CustomerObservable, String> billingAccountCol;

    ObservableList<CustomerObservable> data = FXCollections.observableArrayList();

//    @FXML TextField customerIdInput = new TextField();
//    @FXML TextField customerNameInput = new TextField();
//    @FXML TextField addressInput = new TextField();
//    @FXML TextField phoneNrInput = new TextField();
//    @FXML TextField billingAccountInput = new TextField();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // solved by setting node directtly into the borderpane
//        centerTable = allTables.getCustomerTableView();
//        mainPane.setCenter(centerTable);

        // add observablelist to tableview
        customerTable.setItems(data);
        // get the data from the database added to observablelist
        fillTable(customerDAO.allCustomerObservableList() );

        customerTable.setEditable(true);
        setupCustomerIdCol();
        setupCustomerNameCol();
        setupAddressIdCol();
        setupPhoneNumberCol();
        setupBillingAccoount();

    }

    private void fillTable(ArrayList<CustomerObservable> customer) {
        customer.forEach(c -> data.add(new CustomerObservable(c)) );
    }

    private void setupCustomerIdCol () {
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
        customerIdCol.setOnEditCommit( t -> {
//                refresh the table to repopulate in cases the data sources has changed
                sqlAdapter.updataDatabaseFromTableView(databaseTableName ,"customer_id", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));

                ( (CustomerObservable) t.getTableView() // The TableView control upon which this event occurred.
                        .getItems()
                        .get( // Returns the element at the specified position in this list.
                                t.getTablePosition() //The position upon which this event occurred.
                                        .getRow() // The row that this TablePosition represents in the TableView.
                        )
                ).setCustomerId(t.getNewValue().intValue() ); // set new value input by user in the cell
            }

        );

    }

    private void setupCustomerNameCol () {
        customerNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        customerNameCol.setOnEditCommit( t -> {
            sqlAdapter.updataDatabaseFromTableView(databaseTableName ,"customer_name", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));

            ( (CustomerObservable) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCustomerName( t.getNewValue() );
        });
    }

    private void setupAddressIdCol () {
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
        addressCol.setOnEditCommit( t -> {
                sqlAdapter.updataDatabaseFromTableView(databaseTableName, "address", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue()));

                ((CustomerObservable) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setAddressId(t.getNewValue().intValue());
            });
    }

    private void setupPhoneNumberCol () {
        phoneNumberCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneNumberCol.setOnEditCommit( t -> {
            sqlAdapter.updataDatabaseFromTableView(databaseTableName ,"phone_number", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));

            ( (CustomerObservable) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setPhoneNumber( t.getNewValue() );
        });

    }

    private void setupBillingAccoount () {
        billingAccountCol.setCellFactory(TextFieldTableCell.forTableColumn() );
        billingAccountCol.setOnEditCommit( t -> {
            sqlAdapter.updataDatabaseFromTableView(databaseTableName ,"billing_account", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));

            ( (CustomerObservable) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setBillingAccount( t.getNewValue() );
        });
    }




}
