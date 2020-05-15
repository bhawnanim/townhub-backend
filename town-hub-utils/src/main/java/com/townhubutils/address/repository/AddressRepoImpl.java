package com.townhubutils.address.repository;

import com.townhubutils.address.model.Address;
import com.townhubutils.address.model.mapper.AddressRowMapper;
import com.townhubutils.address.service.AddressProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository(value = "AddressRepo")
public class AddressRepoImpl implements AddressRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    AddressProperties serviceProperties;

    @Override
    public Integer saveAddress(Address address) throws Exception {
        KeyHolder holder=new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps=connection.prepareStatement(serviceProperties.getDbQueries().getInsertAddress(), Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,address.getCity());
                ps.setString(2,address.getState());
                ps.setString(3,address.getCountry());
                ps.setString(4,address.getArea());
                ps.setString(5,address.getAddressLine1());
                ps.setString(6,address.getAddressLine2());
                ps.setInt(7,address.getPinCode());
                return ps;
            }
        }, holder);
        int newAddressId=holder.getKey().intValue();
        return newAddressId;
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
