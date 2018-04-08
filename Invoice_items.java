package Oblig3;

public class Invoice_items {
    int invoice;
    int product;

    public Invoice_items(int invoice, int product) {
        this.invoice = invoice;
        this.product = product;
    }

    public int getInvoice() {
        return invoice;
    }

    public void setInvoice(int invoice) {
        this.invoice = invoice;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }
}
