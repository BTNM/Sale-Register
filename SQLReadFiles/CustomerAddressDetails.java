package Oblig3.SQLReadFiles;

public class CustomerAddressDetails {
    String customerName;
    String streetName;
    String streetNumber;
    String postalCode;
    String postalTown;
    String phoneNumber;
    String billingAccount;
    int customerId;
    int invoiceId;
    String invoiceDato;

    public CustomerAddressDetails(String customerName, String streetName, String streetNumber, String postalCode, String postalTown, String phoneNumber, String billingAccount, int customerId, int invoiceId, String invoiceDato) {
        this.customerName = customerName;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.postalTown = postalTown;
        this.phoneNumber = phoneNumber;
        this.billingAccount = billingAccount;
        this.customerId = customerId;
        this.invoiceId = invoiceId;
        this.invoiceDato = invoiceDato;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalTown() {
        return postalTown;
    }

    public void setPostalTown(String postalTown) {
        this.postalTown = postalTown;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBillingAccount() {
        return billingAccount;
    }

    public void setBillingAccount(String billingAccount) {
        this.billingAccount = billingAccount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceDato() {
        return invoiceDato;
    }

    public void setInvoiceDato(String invoiceDato) {
        this.invoiceDato = invoiceDato;
    }
}
