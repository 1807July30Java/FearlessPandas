package com.revature.beans;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "APP_USER")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequence")
    @SequenceGenerator(allocationSize = 1, name = "userSequence", sequenceName = "SQ_USER_PK")
    @Column(name = "USER_ID")
    private int userId;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private int password;
    @Column(name = "F_NAME")
    private String fName;
    @Column(name = "L_NAME")
    private String lName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "BIRTHDATE")
    private LocalDate birthdate;
    @Column(name = "PAY_EMAIL")
    private String payEmail;
/****************************************************************************/
    // Many to many AppUser --> Address
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "USER_ADDRESS",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ADDRESS_ID")}
    )
    private Set<Address> addresses = new HashSet<>();
/****************************************************************************/
    //One to many User --> Auction
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Auction> auctions;  
/*******************************************************************************/	
	 //One to many book to book image
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Bid> bids;
/****************************************************************************/ 
    
    public AppUser() {
    }

    public AppUser(String username, int password, String fName, String lName, String email, LocalDate birthdate, String payEmail) {
        this.username = username;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.birthdate = birthdate;
        this.payEmail = payEmail;
    }

    public AppUser(String username, String password, String fName, String lName, String email, LocalDate birthdate, String payEmail) {
        this.username = username;
        this.password = password.hashCode();
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.birthdate = birthdate;
        this.payEmail = payEmail;
    }

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password.hashCode();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.hashCode();
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getPayEmail() {
        return payEmail;
    }

    public void setPayEmail(String payEmail) {
        this.payEmail = payEmail;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password=" + password +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", birthdate=" + birthdate +
                ", payEmail='" + payEmail + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
