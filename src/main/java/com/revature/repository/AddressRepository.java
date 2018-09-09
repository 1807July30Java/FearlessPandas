package com.revature.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.revature.domain.Address;

@Repository(value = "addressRepository")
@Transactional
@EnableTransactionManagement
public class AddressRepository {
    @Autowired
    private SessionFactory sessionFactory;
    public Address getAddressWithoutId(String street,String apartment,String city, String state, int zip ) {
    	Session s = sessionFactory.getCurrentSession();
    	Query q = s.getNamedQuery("getAddressWithoutId");
    	q.setString("street", street);
    	q.setString("apartment", apartment);
    	q.setString("city", city);
    	q.setString("state", state);
    	q.setInteger("zip", zip);
    	return (Address) q.uniqueResult();
    }
    public List<Address> getAddresses(){
    	Session s = sessionFactory.getCurrentSession();
    	Query q = s.getNamedQuery("getAddresses");
    	return q.list();
    }
}
