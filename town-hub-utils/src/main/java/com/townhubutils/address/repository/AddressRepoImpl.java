package com.townhubutils.address.repository;

import com.townhubutils.address.model.Address;
import com.townhubutils.address.model.mapper.AddressRowMapper;
import com.townhubutils.address.service.AddressProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "AddressRepo")
public class AddressRepoImpl implements AddressRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    AddressProperties serviceProperties;

    @Override
    public Integer saveAddress(Address address) throws Exception {
        return jdbcTemplate.update(serviceProperties.getDbQueries().getInsertAddress(), address.getCity(), address.getState(), address.getCountry(), address.getArea(), address.getAddressLine1(), address.getAddressLine2(), address.getPinCode());
    }

    @Override
    public Integer updateAddress(int addressId, Address address) throws Exception {
        return jdbcTemplate.update(serviceProperties.getDbQueries().getUpdateAddress(), address.getCity(), address.getState(), address.getCountry(), address.getArea(), address.getAddressLine1(), address.getAddressLine2(), address.getPinCode(), addressId);
    }

    @Override
    public List<Address> getAddressById(int addressId) throws Exception {
        return jdbcTemplate.query(serviceProperties.getDbQueries().getGetAddressById(), new AddressRowMapper(), addressId);
    }
}
