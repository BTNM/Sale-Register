package Oblig3.Controllers;

import Oblig3.TableViewClass.InvoiceDetails;
import Oblig3.TableViewClass.InvoiceObservable;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class invoiceDetailsController implements Initializable {

    @FXML TableView table;
    @FXML TableColumn<InvoiceDetails,String> categoryCol;
    @FXML TableColumn<InvoiceDetails,String> descriptionCol;
    @FXML TableColumn<InvoiceDetails,Integer> quantityCol;
    @FXML TableColumn<InvoiceDetails,Integer> priceCol;
    @FXML TableColumn<InvoiceDetails,Integer> sumQuantityCol;

    @FXML Label customerNameLabel;
    @FXML Label customerStreetLabel;
    @FXML Label customerPostalLabel;
    @FXML Label phoneNumberLabel;
    @FXML Label billingAccountLabel;

    @FXML Label customerIdLabel;
    @FXML Label invoiceIdLabel;
    @FXML Label invoiceDateLabel;


    @FXML Label cellID;
    int id;

//    private ObservableList<InvoiceObservable> test;
//
//    public invoiceDetailsController(int id, ObservableList<InvoiceObservable> test)
//    {
//        this.id=id;
//        this.test = test;
//    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        System.out.println(id);
//        System.out.println(test);


    }

    public void setId (int id) {
        this.id = id;
        System.out.println("id: "+id);
    }

//    public void setCellID (String id) {
//        cellID.setText(id);
//        System.out.println("cell id: "+id);
//    }


}
