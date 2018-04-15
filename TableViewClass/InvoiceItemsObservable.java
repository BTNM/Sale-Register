package Oblig3.TableViewClass;

import javafx.beans.property.SimpleIntegerProperty;

public class InvoiceItemsObservable {
    private final SimpleIntegerProperty invoiceId;
    private final SimpleIntegerProperty productId;


    public InvoiceItemsObservable(int invoiceId, int productId) {
        this.invoiceId = new SimpleIntegerProperty(invoiceId);
        this.productId = new SimpleIntegerProperty(productId);
    }

    public int getInvoiceId() {
        return invoiceId.get();
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId.set(invoiceId);
    }

    public int getProductId() {
        return productId.get();
    }

    public void setProductId(int productId) {
        this.productId.set(productId);
    }
}
