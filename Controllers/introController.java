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
    @FXML public Button productCategory;
    @FXML public Button invoice;
    @FXML public Button products;
    @FXML public Button invoiceItems;

    @FXML public BorderPane rootBp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AllTableviews allT = new AllTableviews();
        TableView  table = allT.getCustomerTableView();
        rootBp.setBottom(table);
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
//            FXMLLoader loadFxml = new FXMLLoader(getClass().getResource("/FxmlFiles/category.fxml"));
//            Stage stage = (Stage) homepage.getScene().getWindow();
//            Scene scene = new Scene(loadFxml.load());
//            stage.setScene(scene);

//            Scene scene = productCategory.getScene();
//            Window window = scene.getWindow();
//            Stage stage = (Stage) window;
//
//            FXMLLoader loader = FXMLLoader.load(getClass().getResource("C:\\Users\\BaoThien\\Dropbox\\IdeaProjects\\src\\Oblig3\\FxmlFiles\\category.fxml"));
//            Stage stage = (Stage) productCategory.getScene().getWindow();
//            Scene scene = new Scene(loader.getRoot() );
//            stage.setScene(scene);
//            stage.show();



            Parent categoryParent = FXMLLoader.load(getClass().getResource("../FxmlFiles/category.fxml"));
            Scene categoryScene = new Scene(categoryParent);
//            Scene categoryScene = new Scene(loadFxml.load());

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(categoryScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
