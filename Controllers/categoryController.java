package Oblig3.Controllers;

import Oblig3.DAOs.CategoryDao;
import Oblig3.DAOs.ConnectionAdapter;
import Oblig3.TableViewClass.CategoryObservable;
import Oblig3.TableViewClass.CustomerObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class categoryController implements Initializable {
    ConnectionAdapter sqlAdapter = new ConnectionAdapter();
    CategoryDao categoryDao = new CategoryDao();
    String databaseTableName = "customer";

    @FXML TableView categoryTable;

    @FXML TableColumn<CategoryObservable, Integer> categoryIdCol;
    @FXML TableColumn<CategoryObservable, String> categoryNameCol;

    @FXML TextField categoryIdInput;
    @FXML TextField categoryNameInput;

    @FXML Button addBtn;
    @FXML Button deleteBtn;

    ObservableList<CategoryObservable> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoryTable.setItems(data);

        fillTable(categoryDao.allCategoryObservableList() );

        categoryTable.setEditable(true);
        setupCategoryIdCol();
        setupCategoryNameCol();

        categoryTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        addBtn.setOnAction(event -> addCategory(categoryIdInput.getText(),categoryNameInput.getText() ) );
        deleteBtn.setOnAction(event -> deleteCustomer());

    }

    private void fillTable(ArrayList<CategoryObservable> element) {
        element.forEach(e -> data.add(new CategoryObservable(e)) );
    }

    private void setupCategoryIdCol () {
        categoryIdCol.setCellFactory(TextFieldTableCell.forTableColumn(
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
        categoryIdCol.setOnEditCommit( t -> {
            sqlAdapter.updataDatabaseFromTableView(databaseTableName, "category_id", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue()));

            ((CategoryObservable) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCategoryId(t.getNewValue().intValue() );
        });

    }

    private void setupCategoryNameCol () {
        categoryNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        categoryNameCol.setOnEditCommit( t -> {
            sqlAdapter.updataDatabaseFromTableView(databaseTableName ,"category_name", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));

            ( (CategoryObservable) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCategoryName( t.getNewValue() );
        });
    }

    private void addCategory(String col1, String col2) {
        sqlAdapter.insertIntoDatabase("category",col1,col2,"","","");

        CategoryObservable c  = new CategoryObservable(Integer.valueOf(col1),col2);
        // have to write a check so that the unique primary keys dont overlap in the database
        data.add(c);
        categoryIdInput.clear();
        categoryNameInput.clear();


//        allTables.addToCustomerObservableTable(c);

    }

    private void deleteCustomer() {
        ObservableList<CustomerObservable> customerSelected, allCustomer;
        allCustomer = categoryTable.getItems();
        customerSelected = categoryTable.getSelectionModel().getSelectedItems();

        // have to delete from database, for now it only removes from tableview
        customerSelected.forEach(allCustomer::remove);


    }


}
