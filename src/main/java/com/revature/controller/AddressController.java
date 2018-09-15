package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.Address;
import com.revature.service.AddressService;

@RestController("addressController")
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private AddressService addressService;
	@GetMapping("/all")
	public ResponseEntity<List<Address>> getAddresses(){
		return new ResponseEntity<>(addressService.getAddresses(),HttpStatus.OK);
	}
}
