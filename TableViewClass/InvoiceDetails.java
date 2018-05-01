package Oblig3.TableViewClass;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class InvoiceDetails { // creates a data model for the product info in the invoice
    private final SimpleIntegerProperty productId;
    private final SimpleStringProperty productName;
    private final SimpleIntegerProperty categoryId;
    private final SimpleStringProperty description;
    private final SimpleIntegerProperty quantity;
    private final SimpleIntegerProperty pricePerUnit;
    private final SimpleIntegerProperty sumQuantity;

    public InvoiceDetails(InvoiceDetails invoiceDetails) {
        this.productId = new SimpleIntegerProperty(invoiceDetails.getProductId() );
        this.productName = new SimpleStringProperty(invoiceDetails.getProductName());
        this.categoryId = new SimpleIntegerProperty(invoiceDetails.getCategoryId() );
        this.description = new SimpleStringProperty(invoiceDetails.getDescription());
        this.quantity = new SimpleIntegerProperty(invoiceDetails.getQuantity());
        this.pricePerUnit = new SimpleIntegerProperty(invoiceDetails.getPricePerUnit());
        this.sumQuantity = new SimpleIntegerProperty(invoiceDetails.getSumQuantity());
    }

    public InvoiceDetails(int productId, String productName, int categoryId, String description, int quantity, int pricePerUnit, int sumQuantity) {
        this.productId = new SimpleIntegerProperty(productId );
        this.productName = new SimpleStringProperty(productName);
        this.categoryId = new SimpleIntegerProperty(categoryId );
        this.description = new SimpleStringProperty(description );
        this.quantity = new SimpleIntegerProperty(quantity );
        this.pricePerUnit = new SimpleIntegerProperty(pricePerUnit );
        this.sumQuantity = new SimpleIntegerProperty(sumQuantity);
    }

    public int getProductId() {
        return productId.get();
    }

    public void setProductId(int productId) {
        this.productId.set(productId);
    }

    public int getCategoryId() {
        return categoryId.get();
    }

    public void setCategoryId(int categoryId) {
        this.categoryId.set(categoryId);
    }

    public String getProductName() {
        return productName.get();
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public int getPricePerUnit() {
        return pricePerUnit.get();
    }

    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit.set(pricePerUnit);
    }

    public int getSumQuantity() {
        return sumQuantity.get();
    }


    public void setSumQuantity(int sumQuantity) {
        this.sumQuantity.set(sumQuantity);
    }

}
