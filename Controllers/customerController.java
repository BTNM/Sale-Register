package Oblig3.Controllers;

import Oblig3.DAOs.CustomerDAO;
import Oblig3.TableViewClass.AllTableviews;
import Oblig3.TableViewClass.CustomerObservable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class customerController implements Initializable {
    AllTableviews allTables = new AllTableviews();

//    @FXML TableView customerTable;
//    @FXML FXCollections dataList;
    @FXML BorderPane root;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // solved by setting node directtly into the borderpane
        root.setCenter(allTables.getCustomerTableView());


//        customerTable = allTables.getCustomerTableView();
//        CustomerDAO c = new CustomerDAO();
//        customerTable = allTables.getCustomerTableView();
//        allTables.getCustomerTableView();

//        customerTable.setItems(c.allCustomerObservableList());
//        dataList = FXCollections.observableArrayList(c.allCustomerObservableList() ):

    }




}
