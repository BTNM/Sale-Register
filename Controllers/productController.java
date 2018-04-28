package Oblig3.Controllers;


import Oblig3.DAOs.ConnectionAdapter;
import Oblig3.DAOs.ProductDao;
import Oblig3.TableViewClass.ProductObservable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.TextFieldTableCell;

import javafx.util.StringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class productController implements Initializable {
    ConnectionAdapter sqlAdapter = new ConnectionAdapter();
    ProductDao dao = new ProductDao();
    String databaseTableName = "product";

    @FXML TableView productTable;
    @FXML TableColumn<ProductObservable, Integer> productIdCol;
    @FXML TableColumn<ProductObservable, String> productNameCol;
    @FXML TableColumn<ProductObservable, String> descriptionCol;
    @FXML TableColumn<ProductObservable, Float> priceCol;
    @FXML TableColumn<ProductObservable, Integer> categoryCol;

    @FXML TextField productIdInput;
    @FXML TextField productNameInput;
    @FXML TextField descriptionInput;
    @FXML TextField priceInput;
    @FXML TextField categoryInput;
    @FXML Button addBtn;
    @FXML Button deleteBtn;

    ObservableList<ProductObservable> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productTable.setItems(data);

        fillTable(dao.allProductObservableList() );

        productTable.setEditable(true);
        setupProductIdCol();
        setupProductNameCol();
        setupDescriptionCol();
        setupPriceCol();
        setupCategoryCol();

        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        addBtn.setOnAction(event -> addProduct(productIdInput.getText(), productNameInput.getText(),descriptionInput.getText(), priceInput.getText(), categoryInput.getText() ));
        deleteBtn.setOnAction(event -> deleteCustomer());

    }

    private void fillTable(ArrayList<ProductObservable> element) {
        element.forEach(e -> data.add(new ProductObservable(e)) );
    }

    private void setupProductIdCol () {
        productIdCol.setCellFactory(TextFieldTableCell.forTableColumn(
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
        productIdCol.setOnEditCommit( t -> {
            sqlAdapter.updataDatabaseFromTableView(databaseTableName, "product_id", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue()));

            ((ProductObservable) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setProductId(t.getNewValue().intValue() );
        });

    }

    private void setupProductNameCol () {
        productNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        productNameCol.setOnEditCommit( t -> {
            sqlAdapter.updataDatabaseFromTableView(databaseTableName ,"product_name", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));

            ( (ProductObservable) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setProductName( t.getNewValue() );
        });
    }

    private void setupDescriptionCol () {
        descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionCol.setOnEditCommit( t -> {
            sqlAdapter.updataDatabaseFromTableView(databaseTableName ,"description", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));

            ( (ProductObservable) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setDescription( t.getNewValue() );
        });
    }

    private void setupPriceCol () {
        priceCol.setCellFactory(TextFieldTableCell.forTableColumn(
                new StringConverter<Float>() {
                    @Override
                    public String toString(Float object) {
                        return object.toString();
                    }

                    @Override
                    public Float fromString(String string) {
                        return Float.parseFloat(string);
                    }
                }
        ));
        priceCol.setOnEditCommit( t -> {
            sqlAdapter.updataDatabaseFromTableView(databaseTableName, "price", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue()));

            ((ProductObservable) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setPrice(t.getNewValue().floatValue() );
        });
    }

    private void setupCategoryCol() {
        categoryCol.setCellFactory(TextFieldTableCell.forTableColumn(
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
        categoryCol.setOnEditCommit( t -> {
            sqlAdapter.updataDatabaseFromTableView(databaseTableName, "category", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue()));

            ((ProductObservable) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCategoryId(t.getNewValue().intValue() );
        });

    }


    private void addProduct(String col1, String col2, String col3, String col4, String col5) {
        sqlAdapter.insertIntoDatabase("product",col1,col2,col3,col4,col5);

        ProductObservable e  = new ProductObservable(Integer.valueOf(col1),col2,col3,Float.valueOf(col4),Integer.valueOf(col5) );
        // have to write a check so that the unique primary keys dont overlap in the database
        data.add(e);

        productIdInput.clear();
        productNameInput.clear();
        descriptionInput.clear();
        priceInput.clear();
        categoryInput.clear();

    }

    private void deleteCustomer() {
        ObservableList<ProductObservable> customerSelected, allCustomer;
        allCustomer = productTable.getItems();
        customerSelected = productTable.getSelectionModel().getSelectedItems();

        // have to delete from database, for now it only removes from tableview
        customerSelected.forEach(allCustomer::remove);


    }



}
