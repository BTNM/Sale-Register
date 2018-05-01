package Oblig3.Controllers;

import Oblig3.DAOs.ConnectionAdapter;
import Oblig3.DAOs.CustomerDAO;
import Oblig3.DAOs.InvoiceDao;
import Oblig3.SQLReadFiles.CustomerAddressDetails;
import Oblig3.TableViewClass.CategoryObservable;
import Oblig3.TableViewClass.InvoiceDetails;
import Oblig3.TableViewClass.InvoiceObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class invoiceDetailsController implements Initializable {
    ConnectionAdapter sqlAdapter = new ConnectionAdapter();
    InvoiceDao dao = new InvoiceDao();

    @FXML TableView table;
    @FXML TableColumn<InvoiceDetails,Integer> productIdCol;
    @FXML TableColumn<InvoiceDetails,String> productNameCol;
    @FXML TableColumn<InvoiceDetails,Integer> categoryCol;
    @FXML TableColumn<InvoiceDetails,String> descriptionCol;
    @FXML TableColumn<InvoiceDetails,Integer> quantityCol;
    @FXML TableColumn<InvoiceDetails,Integer> priceCol;
    @FXML TableColumn<InvoiceDetails,Integer> sumCol;

    @FXML Label customerNameLabel;
    @FXML Label customerStreetLabel;
    @FXML Label customerPostalLabel;
    @FXML Label phoneNumberLabel;
    @FXML Label billingAccountLabel;

    @FXML Label customerIdLabel;
    @FXML Label invoiceIdLabel;
    @FXML Label invoiceDateLabel;


    int invoiceId;
    @FXML Label sentId;

    CustomerAddressDetails details;

    ObservableList<InvoiceDetails> data = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setItems(data);

        fillTable(dao.allInvoiceDetails());
        table.setEditable(true);

        setupColumns();
        setupInvoiceDetails();

    }

    private void setupColumns() {
        productIdCol.setCellValueFactory(new PropertyValueFactory<InvoiceDetails,Integer>("productId") );

        productNameCol.setCellValueFactory(new PropertyValueFactory<InvoiceDetails,String>("productName"));

        categoryCol.setCellValueFactory(new PropertyValueFactory<InvoiceDetails,Integer>("categoryId") );

        descriptionCol.setCellValueFactory(new PropertyValueFactory<InvoiceDetails,String>("description"));

        quantityCol.setCellValueFactory(new PropertyValueFactory<InvoiceDetails,Integer>("quantity") );

        priceCol.setCellValueFactory(new PropertyValueFactory<InvoiceDetails,Integer>("pricePerUnit") );

        sumCol.setCellValueFactory(new PropertyValueFactory<InvoiceDetails,Integer>("sumQuantity") );
    }

    private void setupInvoiceDetails() {
//        details = dao.getCustomerAddressDetails(invoiceId);
        System.out.println("the id: "+invoiceId);
        details = dao.getCustomerAddressDetails(1);

        customerNameLabel.setText(details.getCustomerName());
        customerStreetLabel.setText(details.getStreetName()+" "+details.getStreetNumber() );
        customerPostalLabel.setText(details.getPostalCode()+" "+details.getPostalTown() );
        phoneNumberLabel.setText(details.getPhoneNumber() );
        billingAccountLabel.setText(details.getBillingAccount() );

        customerIdLabel.setText(String.valueOf(details.getCustomerId() ));
        invoiceIdLabel.setText(String.valueOf(details.getInvoiceId() ));
        invoiceDateLabel.setText(details.getInvoiceDato() );

    }

    public void setSentId (int id) {
        System.out.println("the id3: "+id);
//        sentId.setText(String.valueOf(id) );
        this.invoiceId = id;
    }

//    public void setId (int id) {
//        System.out.println("the id2: "+invoiceId);
//        this.invoiceId = id;
////        System.out.println("id: "+id);
//    }

    private void fillTable(ArrayList<InvoiceDetails> element) {
        element.forEach(e -> data.add(new InvoiceDetails(e)) );
    }


}
