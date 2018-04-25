package Oblig3.Controllers;


import Oblig3.TableViewClass.AllTableviews;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class introController implements Initializable{

    @FXML public Button homepage;
    @FXML public Button customers;
    @FXML public Button category;
    @FXML public Button invoice;
    @FXML public Button products;
    @FXML public Button invoiceItems;

    @FXML public BorderPane rootBp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AllTableviews allT = new AllTableviews();
//        TableView  table = allT.getCustomerTableView();
//        rootBp.setBottom(table);
    }





}
