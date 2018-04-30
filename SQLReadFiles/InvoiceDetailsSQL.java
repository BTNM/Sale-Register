package Oblig3.SQLReadFiles;

public class InvoiceDetailsSQL {
    int categoryId;
    String description;
    String productName;
    int quantity;
    int pricePerUnit;
    int sumQuantity;

    public InvoiceDetailsSQL (int categoryId,String productName, String description, int quantity, int pricePerUnit, int sumQuantity) {
        this.categoryId = categoryId;
        this.productName = productName;
        this.description = description;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.sumQuantity = sumQuantity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public int getSumQuantity() {
        return sumQuantity;
    }

    public void setSumQuantity(int sumQuantity) {
        this.sumQuantity = sumQuantity;
    }
}
