package Oblig3.TableViewClass;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AddressObservable {
    private final SimpleIntegerProperty addressId;
    private final SimpleStringProperty streetNumber;
    private final SimpleStringProperty streetName;
    private final SimpleStringProperty postalCode;
    private final SimpleStringProperty postalTown;

    public AddressObservable(AddressObservable addressObservable) {
        this.addressId = new SimpleIntegerProperty(addressObservable.getAddressId() );
        this.streetNumber = new SimpleStringProperty(addressObservable.getStreetNumber());
        this.streetName = new SimpleStringProperty(addressObservable.getStreetName());
        this.postalCode = new SimpleStringProperty(addressObservable.getPostalCode());
        this.postalTown = new SimpleStringProperty(addressObservable.getPostalTown());
    }

    public AddressObservable(int addressId, String streetNumber, String streetName, String postalCode, String postalTown) {
        this.addressId = new SimpleIntegerProperty(addressId);
        this.streetNumber = new SimpleStringProperty(streetNumber);
        this.streetName = new SimpleStringProperty(streetName);
        this.postalCode = new SimpleStringProperty(postalCode);
        this.postalTown = new SimpleStringProperty(postalTown);
    }

    public int getAddressId() {
        return addressId.get();
    }

    public void setAddressId(int addressId) {
        this.addressId.set(addressId);
    }

    public String getStreetNumber() {
        return streetNumber.get();
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber.set(streetNumber);
    }

    public String getStreetName() {
        return streetName.get();
    }

    public void setStreetName(String streetName) {
        this.streetName.set(streetName);
    }

    public String getPostalCode() {
        return postalCode.get();
    }

    public void setPostalCode(String postalCode) {
        this.postalCode.set(postalCode);
    }

    public String getPostalTown() {
        return postalTown.get();
    }

    public void setPostalTown(String postalTown) {
        this.postalTown.set(postalTown);
    }
}
