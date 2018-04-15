package Oblig3.TableViewClass;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

import javax.swing.text.TableView;

public class AllTableviews {
    TableView generalTableview;

//    public AllTableviews () {
//        generalTableview = new TableView();
//
//    }








    public ObservableList<CustomerObservable> getCustomerObservableTable () {
        ObservableList<CustomerObservable> table = FXCollections.observableArrayList(
                new CustomerObservable(11,"test name",4,"0040 0000","test account")
        );

        return table;
    }



}
