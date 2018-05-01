package Oblig3.Controllers;

import Oblig3.DAOs.ConnectionAdapter;
import Oblig3.DAOs.CustomerDAO;

import Oblig3.TableViewClass.CustomerObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.TextFieldTableCell;

import javafx.util.StringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class customerController implements Initializable {
    //    AllTableviews allTables = new AllTableviews();
    ConnectionAdapter sqlAdapter = new ConnectionAdapter();
    CustomerDAO customerDAO = new CustomerDAO();
    String databaseTableName = "customer";

//    @FXML BorderPane mainPane;
    @FXML TableView customerTable;

    @FXML TableColumn<CustomerObservable, Integer> customerIdCol;
    @FXML TableColumn<CustomerObservable, String> customerNameCol;
    @FXML TableColumn<CustomerObservable, Integer> addressCol;
    @FXML TableColumn<CustomerObservable, String> phoneNumberCol;
    @FXML TableColumn<CustomerObservable, String> billingAccountCol;

    ObservableList<CustomerObservable> data = FXCollections.observableArrayList();

    @FXML TextField customerIdInput = new TextField();
    @FXML TextField customerNameInput = new TextField();
    @FXML TextField addressInput = new TextField();
    @FXML TextField phoneNrInput = new TextField();
    @FXML TextField billingAccountInput = new TextField();
    @FXML Button addBtn;
    @FXML Button deleteBtn;

    @FXML TextField filterField;
    @FXML Label notice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // solved by setting node directtly into the borderpane
//        centerTable = allTables.getCustomerTableView();
//        mainPane.setCenter(centerTable);

        // add observablelist to tableview
//        customerTable.setItems(data);
        customerTable.setItems(filterFunction() );

        // get the data from the database added to observablelist
        fillTable(customerDAO.allCustomerObservableList() );
        customerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        customerTable.setEditable(true);
        setupCustomerIdCol();
        setupCustomerNameCol();
        setupAddressIdCol();
        setupPhoneNumberCol();
        setupBillingAccoount();

        addBtn.setOnAction(event -> addCustomer(customerIdInput.getText(),customerNameInput.getText(),addressInput.getText(),phoneNrInput.getText(),billingAccountInput.getText() ) );
        deleteBtn.setOnAction(event -> deleteCustomer());
    }

    private SortedList<CustomerObservable> filterFunction () {
        FilteredList<CustomerObservable> filteredList = new FilteredList<>(data, p ->true);

        filterField.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredList.setPredicate(obs -> {
                if (newValue == null || newValue.isEmpty() ) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                // first check id then name
                if ( String.valueOf(obs.getCustomerId()).contains(newValue)  ) {
                    return true;
                }
                if (obs.getCustomerName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }

                return false;
            });
        }));

        SortedList<CustomerObservable> sortedData = new SortedList<>(filteredList);
        sortedData.comparatorProperty().bind(customerTable.comparatorProperty() );

        return sortedData;
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
                ).setAddressId(t.getNewValue().intValue() );
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

    private void addCustomer(String cId, String cName,String add, String phone, String bAccount) {
        notice.setText("");
        if (allTextfieldsValid() ) {
            sqlAdapter.insertIntoDatabase("customer",cId,cName,add, phone,bAccount);

            CustomerObservable c  = new CustomerObservable(Integer.valueOf(cId),cName,Integer.valueOf(add),phone,bAccount);
            // have to write a check so that the unique primary keys dont overlap in the database
            data.add(c);
            customerIdInput.clear();
            customerNameInput.clear();
            addressInput.clear();
            phoneNrInput.clear();
            billingAccountInput.clear();
        } else {
            notice.setText("Invalid input, empty fields");
        }

    }

    private boolean allTextfieldsValid() {
        return !customerIdInput.getText().isEmpty() &&
                !customerNameInput.getText().isEmpty() &&
                !addressInput.getText().isEmpty() &&
                !phoneNrInput.getText().isEmpty() &&
                !billingAccountInput.getText().isEmpty();
    }

    private void deleteCustomer() {
        ObservableList<CustomerObservable> customerSelected, allCustomer;
        allCustomer = customerTable.getItems();
        customerSelected = customerTable.getSelectionModel().getSelectedItems();

        // have to delete from database, for now it only removes from tableview
        customerSelected.forEach(allCustomer::remove);

    }


}
