package com.revature.domain;

import javax.persistence.*;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import java.util.HashSet;
import java.util.Set;

@NamedQueries({
	@NamedQuery(name = "getUserById",query = "from User where id = :uid"),
	
	@NamedQuery(name = "getUsers",query = "from User"),
	
	@NamedQuery(name = "getUserByLogin", query = "from User where username = :username and password = :password"),
	
	@NamedQuery(name = "getUserByName", query = "from User where username = :username")
})
@Component(value = "User")
@Scope("Prototype")
@Entity
@Table(name = "APP_USER")
public class User {
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
    @Column(name = "PAY_EMAIL")
    private String payEmail;
    @Column(name = "USER_ROLE", columnDefinition = "varchar2(10) default 'USER'")
    private String userrole;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ADDRESS",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ADDRESS_ID")}
    )
    private Set<Address> addresses = new HashSet<>();

    public User() {
    }


    public User(String username, String password, String fName, String lName, String email, String payEmail) {
        this.username = username;
        this.password = password.hashCode();
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.payEmail = payEmail;
    }
    public User(String username, String password) {
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
                ", payEmail='" + payEmail + '\'' +
                ", addresses=" + addresses +
                '}';
    }

    public void customUserAspect(String a) {
    	System.out.println("a");
    }

}
