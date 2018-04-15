package Oblig3.TableViewClass;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AddressObservable {
    private final SimpleIntegerProperty addressId;
    private final SimpleStringProperty streetnumber;
    private final SimpleStringProperty streetName;
    private final SimpleStringProperty postalCode;
    private final SimpleStringProperty postalTown;


    public AddressObservable(int addressId, String streetNumber, String streetName, String postalCode, String postalTown) {
        this.addressId = new SimpleIntegerProperty(addressId);
        this.streetnumber = new SimpleStringProperty(streetNumber);
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

    public String getStreetnumber() {
        return streetnumber.get();
    }

    public void setStreetnumber(String streetnumber) {
        this.streetnumber.set(streetnumber);
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