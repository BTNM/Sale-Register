package Oblig3.DAOs;

import Oblig3.SQLReadFiles.Address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDao {
    String getAddressQuery = "SELECT * FROM main.address WHERE address_id = ?";





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
