package Oblig3.Controllers;


import Oblig3.TableViewClass.ProductObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class productController implements Initializable {

    public TableView productTable;

    public TableColumn pId;
    public TableColumn pName;
    public TableColumn desc;
    public TableColumn price;
    public TableColumn category;

    ObservableList<ProductObservable> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        pId.setCellValueFactory(
//                new PropertyValueFactory<ProductObservable,Integer>("productId")
//        );
//        pName.setCellValueFactory(
//                new PropertyValueFactory<ProductObservable,String>("productName")
//        );
//        desc.setCellValueFactory(
//                new PropertyValueFactory<ProductObservable,String>("description")
//        );
//        price.setCellValueFactory(
//                new PropertyValueFactory<ProductObservable,Float>("price")
//        );
//        category.setCellValueFactory(
//                new PropertyValueFactory<ProductObservable,Integer>("category")
//        );
//
//        data = FXCollections.observableArrayList();
//        productTable.setItems(data);

    }

//    public void testDataObservableList () {
//        data = FXCollections.observableArrayList(
//                new ProductObservable("3", "Math book","A new book about math for universities","385.90","4" ),
//                new ProductObservable("4","Geography book","A new book about geography for universities", "599.90","7")
//        );
//    }


}
