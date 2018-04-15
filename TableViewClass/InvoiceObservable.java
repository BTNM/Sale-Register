package Oblig3.TableViewClass;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class InvoiceObservable {
    private final SimpleIntegerProperty invoiceId;
    private final SimpleIntegerProperty customerId;
    private final SimpleStringProperty dato;

    public InvoiceObservable(int invoiceId, int customerId, String dato) {
        this.invoiceId = new SimpleIntegerProperty(invoiceId);
        this.customerId = new SimpleIntegerProperty(customerId);
        this.dato = new SimpleStringProperty(dato);
    }

    public int getInvoiceId() {
        return invoiceId.get();
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId.set(invoiceId);
    }

    public int getCustomerId() {
        return customerId.get();
    }

    public void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }

    public String getDato() {
        return dato.get();
    }

    public void setDato(String dato) {
        this.dato.set(dato);
    }

}
