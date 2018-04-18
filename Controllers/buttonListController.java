package Oblig3.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class buttonListController  {

    public void changeScene(String fxmlPath, ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlPath));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void homeClicked(ActionEvent event) {
        changeScene("../FxmlFiles/intro.fxml", event);
    }

    public void customerClicked(ActionEvent event) {
        changeScene("../FxmlFiles/customer.fxml", event);
    }

    public void addressClicked(ActionEvent event) {
        changeScene("../FxmlFiles/address.fxml", event);
    }

    public void categoryClicked(ActionEvent event) {
        changeScene("../FxmlFiles/category.fxml", event);
//
//        try {
//            Parent categoryParent = FXMLLoader.load(getClass().getResource("../FxmlFiles/category.fxml"));
//            Scene categoryScene = new Scene(categoryParent);
//
//            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//
//            window.setScene(categoryScene);
//            window.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void invoiceClicked(ActionEvent event) {
        changeScene("../FxmlFiles/invoice.fxml", event);
    }

    public void productsClicked(ActionEvent event) {
        changeScene("../FxmlFiles/products.fxml", event);
    }
}
