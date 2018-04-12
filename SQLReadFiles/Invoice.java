package Oblig3.SQLReadFiles;

public class Invoice {
    int invoice_id;
    int customer;
    String dato;

    public Invoice(int invoice_id, int customer, String dato) {
        this.invoice_id = invoice_id;
        this.customer = customer;
        this.dato = dato;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }
}
