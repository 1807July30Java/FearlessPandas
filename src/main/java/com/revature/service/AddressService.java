package com.revature.service;

import com.revature.domain.Address;
import com.revature.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "addressService")
@Scope("Prototype")
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

  public Address getAddressWithoutId(Address A) {
	  return addressRepository.getAddressWithoutId(A.getStreet(), A.getApartment(),A.getCity(), A.getState(), A.getZip());
  }
  public List<Address> getAddresses(){
	  return addressRepository.getAddresses();
  }
}
