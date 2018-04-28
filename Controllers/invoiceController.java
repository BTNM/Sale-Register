package Oblig3.Controllers;

import Oblig3.DAOs.ConnectionAdapter;
import Oblig3.DAOs.InvoiceDao;
import Oblig3.DAOs.InvoiceItemsDao;
import Oblig3.TableViewClass.InvoiceItemsObservable;
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
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class invoiceController implements Initializable {
    ConnectionAdapter sqlAdapter = new ConnectionAdapter();
    InvoiceDao dao = new InvoiceDao();
    InvoiceItemsDao daoII = new InvoiceItemsDao();
    String databaseTableName = "invoice";
    String databaseIITableName = "invoice_items";

    @FXML TableView invoiceTable;
    @FXML TableColumn<InvoiceObservable, Integer> invoiceIdCol;
    @FXML TableColumn<InvoiceObservable, Integer> customerCol;
    @FXML TableColumn<InvoiceObservable, String> datoCol;

    @FXML TextField invoiceIdInput;
    @FXML TextField customerInput;
    @FXML TextField datoInput;
    @FXML Button addInvoiceBtn;
    @FXML Button deleteInvoiceBtn;

    @FXML TableView invoiceItemsTable;
    @FXML TableColumn<InvoiceItemsObservable, Integer> IIInvoiceCol;
    @FXML TableColumn<InvoiceItemsObservable, Integer> IIProductCol;

    @FXML TextField IIInvoiceInput;
    @FXML TextField IIProductInput;
    @FXML Button addInvoiceItemsBtn;
    @FXML Button deleteInvoiceItemsBtn;

    ObservableList<InvoiceObservable> invoiceData = FXCollections.observableArrayList();
    ObservableList<InvoiceItemsObservable> invoiceItemsData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        invoiceTable.setItems(invoiceData);
        invoiceItemsTable.setItems(invoiceItemsData);

        fillTable(dao.allInvoiceObservableList() );
        fillTableII(daoII.allInvoiceItemsObservableList() );

        invoiceTable.setEditable(true);
        setupInvoiceIdCol();
        setupCustomerCol();
        setupDatoCol();

        invoiceItemsTable.setEditable(true);
        setupIIInvoiceCol();
        setupIIProductCol();

        invoiceTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        invoiceItemsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//        invoiceIdCol.setMaxWidth(1f * Integer.MAX_VALUE * 20);
//        customerCol.setMaxWidth(1f * Integer.MAX_VALUE * 20);
//        datoCol.setMaxWidth(1f * Integer.MAX_VALUE * 20);

        addInvoiceBtn.setOnAction(event -> addInvoice(invoiceIdInput.getText(), customerInput.getText(),datoInput.getText() ));
        deleteInvoiceBtn.setOnAction(event -> delete());

        addInvoiceItemsBtn.setOnAction(event -> addInvoiceItems(IIInvoiceInput.getText(), IIProductInput.getText()) );
        deleteInvoiceItemsBtn.setOnAction(event -> deleteII());
    }

    private void fillTableII(ArrayList<InvoiceItemsObservable> element) {
        element.forEach(e -> invoiceItemsData.add(new InvoiceItemsObservable(e)) );
    }

    private void setupIIInvoiceCol () {
        IIInvoiceCol.setCellFactory(TextFieldTableCell.forTableColumn(
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
        IIInvoiceCol.setOnEditCommit( t -> {
            sqlAdapter.updataDatabaseFromTableView(databaseIITableName, "invoice", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue()));

            ((InvoiceItemsObservable) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setInvoiceId(t.getNewValue().intValue() );
        });
    }

    private void setupIIProductCol () {
        IIProductCol.setCellFactory(TextFieldTableCell.forTableColumn(
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
        IIProductCol.setOnEditCommit( t -> {
            sqlAdapter.updataDatabaseFromTableView(databaseIITableName, "product", String.valueOf(t.getNewValue()), String.valueOf(t.getOldValue()));

            ((InvoiceItemsObservable) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setProductId(t.getNewValue().intValue() );
        });
    }

    private void addInvoiceItems(String col1, String col2) {
        sqlAdapter.insertIntoDatabase("invoice_items",col1,col2,"","","");

        InvoiceItemsObservable e  = new InvoiceItemsObservable(Integer.valueOf(col1),Integer.valueOf(col2) );
        // have to write a check so that the unique primary keys dont overlap in the database
        invoiceItemsData.add(e);
        IIInvoiceInput.clear();
        IIProductInput.clear();

    }

    private void deleteII() {
        ObservableList<InvoiceItemsObservable> selected, all;
        all = invoiceItemsTable.getItems();
        selected = invoiceItemsTable.getSelectionModel().getSelectedItems();

        // have to delete from database, for now it only removes from tableview
        selected.forEach(all::remove);

    }



    private void fillTable(ArrayList<InvoiceObservable> element) {
        element.forEach(e -> invoiceData.add(new InvoiceObservable(e)) );
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
        invoiceData.add(c);
        invoiceIdInput.clear();
        customerInput.clear();
        datoInput.clear();

    }

    private void delete() {
        ObservableList<InvoiceObservable> customerSelected, allCustomer;
        allCustomer = invoiceTable.getItems();
        customerSelected = invoiceTable.getSelectionModel().getSelectedItems();

        // have to delete from database, for now it only removes from tableview
        customerSelected.forEach(allCustomer::remove);

    }


    public void clickDetailInvoice(MouseEvent mouseEvent) {



    }
}
