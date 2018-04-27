package Oblig3.Controllers;

import Oblig3.DAOs.ConnectionAdapter;
import Oblig3.DAOs.InvoiceDao;
import Oblig3.TableViewClass.InvoiceObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class invoiceController implements Initializable {
    ConnectionAdapter sqlAdapter = new ConnectionAdapter();
    InvoiceDao dao = new InvoiceDao();
    String databaseTableName = "invoice";

    @FXML TableView invoiceTable;
    @FXML TableColumn<InvoiceObservable, Integer> invoiceIdCol;
    @FXML TableColumn<InvoiceObservable, Integer> customerCol;
    @FXML TableColumn<InvoiceObservable, String> datoCol;

    @FXML TextField invoiceIdInput;
    @FXML TextField customerInput;
    @FXML TextField datoInput;
    @FXML Button addBtn;
    @FXML Button deleteBtn;

    ObservableList<InvoiceObservable> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        invoiceTable.setItems(data);

        fillTable(dao.allInvoiceObservableList() );

        invoiceTable.setEditable(true);
        setupInvoiceIdCol();
        setupCustomerCol();
        setupDatoCol();

        addBtn.setOnAction(event -> addInvoice(invoiceIdInput.getText(), customerInput.getText(),datoInput.getText() ));
        deleteBtn.setOnAction(event -> deleteCustomer());

    }

    private void fillTable(ArrayList<InvoiceObservable> element) {
        element.forEach(e -> data.add(new InvoiceObservable(e)) );
    }

    private void setupInvoiceIdCol () {
        invoiceIdCol.setCellFactory(TextFieldTableCell.forTableColumn(
                new StringConverter<Integer>() {
                    @Override
                    public String toString(Integer object) {
                        return object.toString();
                    }

                    @Override
                    public Integer fromString(String string) {
                        return Integer.parseInt(string);
                    }
                }
        ));
        invoiceIdCol.setOnEditCommit( t -> {
            sqlAdapter.updataDatabaseFromTableView(databaseTableName, "invoice_id", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue()));

            ((InvoiceObservable) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setInvoiceId(t.getNewValue().intValue() );
        });

    }

    private void setupCustomerCol () {
        customerCol.setCellFactory(TextFieldTableCell.forTableColumn(
                new StringConverter<Integer>() {
                    @Override
                    public String toString(Integer object) {
                        return object.toString();
                    }

                    @Override
                    public Integer fromString(String string) {
                        return Integer.parseInt(string);
                    }
                }
        ));
        customerCol.setOnEditCommit( t -> {
            sqlAdapter.updataDatabaseFromTableView(databaseTableName, "customer", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue()));

            ((InvoiceObservable) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCustomerId(t.getNewValue().intValue() );
        });

    }

    private void setupDatoCol () {
        datoCol.setCellFactory(TextFieldTableCell.forTableColumn());
        datoCol.setOnEditCommit( t -> {
            sqlAdapter.updataDatabaseFromTableView(databaseTableName ,"dato", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue() ));

            ( (InvoiceObservable) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setDato( t.getNewValue() );
        });
    }

    private void addInvoice(String col1, String col2, String col3) {
        sqlAdapter.insertIntoDatabase("invoice",col1,col2,col3,"","");

        InvoiceObservable c  = new InvoiceObservable(Integer.valueOf(col1),Integer.valueOf(col2), col3);
        // have to write a check so that the unique primary keys dont overlap in the database
        data.add(c);
        invoiceIdInput.clear();
        customerInput.clear();
        datoInput.clear();


//        allTables.addToCustomerObservableTable(c);

    }

    private void deleteCustomer() {
        ObservableList<InvoiceObservable> customerSelected, allCustomer;
        allCustomer = invoiceTable.getItems();
        customerSelected = invoiceTable.getSelectionModel().getSelectedItems();

        // have to delete from database, for now it only removes from tableview
        customerSelected.forEach(allCustomer::remove);


    }


}
