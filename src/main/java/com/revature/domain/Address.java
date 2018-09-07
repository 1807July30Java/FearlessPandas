package com.revature.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ADDRESS")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressSequence")
    @SequenceGenerator(allocationSize = 1, name = "addressSequence", sequenceName = "SQ_ADDRESS_PK")
    @Column(name = "ADDRESS_ID")
    private int addressId;
    @Column(name = "STREET")
    private String street;
    @Column(name = "APARTMENT")
    private String apartment;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STATE")
    private String state;
    @Column(name = "ZIP")
    private int zip;

    public Address() {
    }

    public Address(String street, String apartment, String city, String state, int zip, Set<User> users) {
        this.street = street;
        this.apartment = apartment;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public Address(String street, String apartment, String city, String state, int zip) {
        this.street = street;
        this.apartment = apartment;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

}
