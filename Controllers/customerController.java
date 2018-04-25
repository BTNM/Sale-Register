package Oblig3.Controllers;

import Oblig3.TableViewClass.AllTableviews;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class customerController implements Initializable {
    AllTableviews allTables = new AllTableviews();

//    @FXML TableView customerTable;
//    @FXML FXCollections dataList;
    @FXML BorderPane mainPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // solved by setting node directtly into the borderpane
        mainPane.setCenter(allTables.getCustomerTableView());


//        CustomerDAO c = new CustomerDAO();

//        customerTable.setItems(c.allCustomerObservableList());
//        dataList = FXCollections.observableArrayList(c.allCustomerObservableList() ):

    }




}
