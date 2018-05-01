package Oblig3.Controllers;

import Oblig3.DAOs.AddressDao;
import Oblig3.DAOs.ConnectionAdapter;
import Oblig3.TableViewClass.AddressObservable;
import Oblig3.TableViewClass.InvoiceObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class addressController implements Initializable {
    ConnectionAdapter sqlAdapter = new ConnectionAdapter();
    AddressDao dao = new AddressDao();
    String databaseTableName = "address";

    @FXML BorderPane mainPane;
    @FXML TableView addressTable;
    @FXML TableColumn<AddressObservable, Integer> addressIdCol;
    @FXML TableColumn<AddressObservable, String> streetNumberCol;
    @FXML TableColumn<AddressObservable, String> streetNameCol;
    @FXML TableColumn<AddressObservable, String> postalCodeCol;
    @FXML TableColumn<AddressObservable, String> postalTownCol;

    @FXML TextField addressInput;
    @FXML TextField streetNumberInput;
    @FXML TextField streetNameInput;
    @FXML TextField postalCodeInput;
    @FXML TextField postalTownInput;

    @FXML Button addBtn;
    @FXML Button deleteBtn;

    @FXML TextField filterField;
    @FXML Label notice;

    ObservableList<AddressObservable> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        addressTable.setItems(data);
        addressTable.setItems(filterFunction() );

        fillTable(dao.allAddressObservableList() );

        addressTable.setEditable(true);
        setupAddressIdCol();
        setupStreetNumberCol();
        setupStreetNameCol();
        setupPostalCodeCol();
        setupPostalTownCol();

        addressTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        addBtn.setOnAction(event -> addAddress(addressInput.getText(), streetNumberInput.getText(), streetNameInput.getText(), postalCodeInput.getText(), postalTownInput.getText() ));
        deleteBtn.setOnAction(event -> delete());

    }

    private SortedList<AddressObservable> filterFunction () {
        FilteredList<AddressObservable> filteredList = new FilteredList<>(data, p ->true);

        filterField.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredList.setPredicate(obs -> {
                if (newValue == null || newValue.isEmpty() ) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                // first check id then name
                if ( String.valueOf(obs.getAddressId()).contains(newValue)  ) {
                    return true;
                }
                if (obs.getStreetName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }

                return false;
            });
        }));

        SortedList<AddressObservable> sortedData = new SortedList<>(filteredList);
        sortedData.comparatorProperty().bind(addressTable.comparatorProperty() );

        return sortedData;
    }

    private void fillTable(ArrayList<AddressObservable> element) {
        element.forEach(e -> data.add(new AddressObservable(e)) );
    }

    private void setupAddressIdCol () {
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
        addressIdCol.setOnEditCommit( t -> {
            sqlAdapter.updataDatabaseFromTableView(databaseTableName, "address_id", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue()));

            ((AddressObservable) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setAddressId(t.getNewValue().intValue() );
        });

    }

    private void setupStreetNumberCol () {
        streetNumberCol.setCellFactory(TextFieldTableCell.forTableColumn());
        streetNumberCol.setOnEditCommit( t -> {
            sqlAdapter.updataDatabaseFromTableView(databaseTableName ,"street_number", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));

            ( (AddressObservable) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setStreetNumber( t.getNewValue() );
        });
    }

    private void setupStreetNameCol () {
        streetNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        streetNameCol.setOnEditCommit( t -> {
            sqlAdapter.updataDatabaseFromTableView(databaseTableName ,"street_name", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));

            ( (AddressObservable) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setStreetName( t.getNewValue() );
        });
    }

    private void setupPostalCodeCol () {
        postalCodeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        postalCodeCol.setOnEditCommit( t -> {
            sqlAdapter.updataDatabaseFromTableView(databaseTableName ,"postal_code", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));

            ( (AddressObservable) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setPostalCode( t.getNewValue() );
        });
    }
    private void setupPostalTownCol () {
        postalTownCol.setCellFactory(TextFieldTableCell.forTableColumn());
        postalTownCol.setOnEditCommit( t -> {
            sqlAdapter.updataDatabaseFromTableView(databaseTableName ,"postal_town", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));

            ( (AddressObservable) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setPostalTown( t.getNewValue() );
        });
    }



    private void addAddress(String col1, String col2, String col3, String col4, String col5) {
        notice.setText("");
        if (allTextfieldsValid() ) {
            sqlAdapter.insertIntoDatabase("address",col1,col2,col3,col4,col5);

            AddressObservable c  = new AddressObservable(Integer.valueOf(col1),col2, col3, col4, col5);

            data.add(c);
            addressInput.clear();
            streetNumberInput.clear();
            streetNameInput.clear();
            postalCodeInput.clear();
            postalTownInput.clear();

        } else {
            notice.setText("Invalid input, empty fields");
        }

    }

    private boolean allTextfieldsValid() {
        return !addressInput.getText().isEmpty() &&
                !streetNumberInput.getText().isEmpty() &&
                !streetNameInput.getText().isEmpty() &&
                !postalCodeInput.getText().isEmpty() &&
                !postalTownInput.getText().isEmpty();
    }

    private void delete() {
        ObservableList<AddressObservable> selected, all;
        all = addressTable.getItems();
        selected = addressTable.getSelectionModel().getSelectedItems();

        // have to delete from database, for now it only removes from tableview
        selected.forEach(all::remove);


    }


}
