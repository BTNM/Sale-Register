package Oblig3.TableViewClass;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CustomerObservable {
    private final SimpleIntegerProperty customerId;
    private final SimpleStringProperty customerName;
    private final SimpleIntegerProperty addressId;
    private final SimpleStringProperty phoneNumber;
    private final SimpleStringProperty billingAccount;


    public CustomerObservable(int customerId, String customerName, int addressId, String phoneNumber, String billingAccount) {
        this.customerId = new SimpleIntegerProperty(customerId );
        this.customerName = new SimpleStringProperty(customerName );
        this.addressId = new SimpleIntegerProperty(addressId);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.billingAccount = new SimpleStringProperty(billingAccount );
    }

    public int getCustomerId() {
        return customerId.get();
    }

    public void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    public int getAddressId() {
        return addressId.get();
    }

    public void setAddressId(int addressId) {
        this.addressId.set(addressId);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getBillingAccount() {
        return billingAccount.get();
    }

    public void setBillingAccount(String billingAccount) {
        this.billingAccount.set(billingAccount);
    }

}
