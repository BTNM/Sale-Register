package Oblig3.Controllers;

import Oblig3.TableViewClass.AllTableviews;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addressController implements Initializable {
    AllTableviews allTables = new AllTableviews();

    @FXML BorderPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane.setCenter(allTables.getAddressTableView());

    }



}
