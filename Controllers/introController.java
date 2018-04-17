package Oblig3.Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class introController implements Initializable{

    public Button homepage;
    public Button customers;
    public Button productCategory;
    public Button invoice;
    public Button products;
    public Button invoiceItems;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void homeClicked(MouseEvent mouseEvent) {
//        try {
////            FXMLLoader loadFxml = new FXMLLoader(getClass().getResource("intro.fxml"));
////            Stage stage = (Stage) homepage.getScene().getWindow();
////            Scene scene = new Scene(loadFxml.load());
////
////            stage.setScene(scene);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public void categoryClicked(ActionEvent event) {
        try {
//            FXMLLoader loadFxml = new FXMLLoader(getClass().getResource("category.fxml"));
//            Stage stage = (Stage) homepage.getScene().getWindow();
//            Scene scene = new Scene(loadFxml.load());
//            stage.setScene(scene);

//            Scene scene = productCategory.getScene();
//            Window window = scene.getWindow();
//            Stage stage = (Stage) window;

//            FXMLLoader loader = FXMLLoader.load(getClass().getResource("/category.fxml"));
//            Stage stage = (Stage) productCategory.getScene().getWindow();
//            Scene scene = new Scene(loader.getRoot() );
//            stage.setScene(scene);
//            stage.show();



            Parent categoryParent = FXMLLoader.load(getClass().getResource("category.fxml"));
            Scene categoryScene = new Scene(categoryParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(categoryScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
