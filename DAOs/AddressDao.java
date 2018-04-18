package Oblig3.DAOs;

import Oblig3.SQLReadFiles.Address;
import Oblig3.TableViewClass.AddressObservable;
import Oblig3.TableViewClass.CustomerObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDao {
    String getAddressQuery = "SELECT * FROM main.address WHERE address_id = ?";
    PreparedStatement customerById;
    ConnectionAdapter connectionAdapter = new ConnectionAdapter();

    public AddressDao() {
        connectionAdapter.startConnect();

        try {
            customerById = connectionAdapter
                    .getConnection()
                    .prepareStatement(getAddressQuery);

        } catch (SQLException e) {
            System.out.println(e.getMessage() );
        }
//        connectionAdapter.stopConnect();
    }


    public ObservableList<AddressObservable> allAddressObservableList() {
        ObservableList<AddressObservable> list = FXCollections.observableArrayList();
        PreparedStatement statement = null;
        connectionAdapter.startConnect();

        try {
            String query = "Select * From address";
            statement = connectionAdapter.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next() ) {
                AddressObservable observable = new AddressObservable(
                        rs.getInt("address_id"),
                        rs.getString("street_number"),
                        rs.getString("street_name"),
                        rs.getString("postal_code"),
                        rs.getString("postal_town")
                );

                list.add(observable);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() );
        }
        connectionAdapter.stopConnect();

        return list;
    }

    public Address getAddressById (int address_id) {
        ConnectionAdapter connectionAdapter = new ConnectionAdapter();
        connectionAdapter.startConnect();
        Address tempAddress = null;

        try {
            PreparedStatement addressById = connectionAdapter
                    .getConnection()
                    .prepareStatement(getAddressQuery);

            addressById.setInt(1, address_id);
            ResultSet rs = addressById.executeQuery();

            if (rs.next()) {
                tempAddress = new Address(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            } else {
                System.out.println("Couldn't find any from address database");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return tempAddress;
    }

}
