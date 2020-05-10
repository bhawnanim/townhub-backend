package com.townhubutils.address.service;

import com.townhubresponse.exception.ResultException;
import com.townhubresponse.response.Result;
import com.townhubutils.address.model.Address;
import com.townhubutils.address.repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    AddressRepo addressRepo;

    public Result<Integer> saveAddress(Address address) throws Exception {
        int result = addressRepo.saveAddress(address);
        if (result > 0) {
            return new Result<>(201, "Record successfully saved.");
        } else {
            throw new ResultException(new Result<>(400, "Please record address again properly.",
                    new ArrayList<>(Arrays.asList(new Result.TownHubError(("").hashCode(),
                            "Please record address again!!!")))));
        }
    }

    public Result<Integer> updateAddress(int id, Address address) throws Exception {
        int result = addressRepo.updateAddress(id, address);
        if (result > 0) {
            return new Result<>(201, "Record successfully edited.");
        } else {
            throw new ResultException(new Result<>(400, "Please record address again.",
                    new ArrayList<>(Arrays.asList(new Result.TownHubError(("").hashCode(),
                            "Please record address again!!!")))));
        }
    }

    public Result<Address> getAddressById(int addressId) throws Exception {
        List<Address> list = addressRepo.getAddressById(addressId);
        if (list.size() > 0) {
            return new Result<>(200, list.get(0));
        }
        throw new ResultException(new Result<>(500, "Address not found" + addressId));
    }
}
