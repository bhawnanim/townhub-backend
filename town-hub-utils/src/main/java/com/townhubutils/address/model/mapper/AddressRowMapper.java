package com.townhubutils.address.model.mapper;

import com.townhubutils.address.model.Address;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRowMapper implements RowMapper<Address> {

    @Override
    public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
        Address address = new Address();
        address.setAddressId(rs.getInt("addressId"));
        address.setCity(rs.getString("city"));
        address.setState(rs.getString("state"));
        address.setCountry(rs.getString("country"));
        address.setArea(rs.getString("area"));
        address.setAddressLine1(rs.getString("addressLine1"));
        address.setAddressLine2(rs.getString("addressLine2"));
        address.setPinCode(rs.getInt("pinCode"));
        return address;
    }
}
