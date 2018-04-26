package Oblig3.TableViewClass;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProductObservable {
    private final SimpleIntegerProperty productId;
    private final SimpleStringProperty productName;
    private final SimpleStringProperty description;
    private final SimpleFloatProperty price;
    private final SimpleIntegerProperty categoryId;

    public ProductObservable(String produktId, String produktName, String description, String price, String categoryId) {
        this.productId = new SimpleIntegerProperty(Integer.valueOf(produktId) );
        this.productName = new SimpleStringProperty(produktName);
        this.description = new SimpleStringProperty(description);
        this.price = new SimpleFloatProperty(Float.valueOf(price) );
        this.categoryId = new SimpleIntegerProperty(Integer.valueOf(categoryId) );
    }

    public ProductObservable(int produktId, String produktName, String description, float price, int categoryId) {
        this.productId = new SimpleIntegerProperty(produktId);
        this.productName = new SimpleStringProperty(produktName);
        this.description = new SimpleStringProperty(description);
        this.price = new SimpleFloatProperty(price);
        this.categoryId = new SimpleIntegerProperty(categoryId);
    }

    public int getProductId() {
        return productId.get();
    }

    public void setProductId(int productId) {
        this.productId.set(productId);
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

    public float getPrice() {
        return price.get();
    }

    public void setPrice(float price) {
        this.price.set(price);
    }

    public int getCategoryId() {
        return categoryId.get();
    }

    public void setCategoryId(int categoryId) {
        this.categoryId.set(categoryId);
    }
}
