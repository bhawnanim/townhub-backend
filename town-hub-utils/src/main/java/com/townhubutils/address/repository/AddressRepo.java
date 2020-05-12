package com.townhubutils.address.repository;

import com.townhubutils.address.model.Address;

import java.util.List;

public interface AddressRepo {
    Integer saveAddress(Address address) throws Exception;

    Integer updateAddress(int addressId, Address address) throws Exception;

    List<Address> getAddressById(int addressId) throws Exception;
}
