package Oblig3.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class categoryController implements Initializable {

    public Button homepage;
    public Button customers;
    public Button productCategory;
    public Button invoice;
    public Button products;
    public Button invoiceItems;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void homeClicked(ActionEvent event) {
        try {
//            FXMLLoader loadFxml = new FXMLLoader(getClass().getResource("intro.fxml"));
//            Stage stage = (Stage) homepage.getScene().getWindow();
//            Scene scene = new Scene(loadFxml.load());
//
//            stage.setScene(scene);

            Parent homeParent = FXMLLoader.load(getClass().getResource("intro.fxml"));
            Scene homeScene = new Scene(homeParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(homeScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
