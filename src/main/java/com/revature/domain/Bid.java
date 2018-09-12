package com.revature.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name ="BID")
public class Bid {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bidSequence")
	@SequenceGenerator(allocationSize = 1, name = "bidSequence", sequenceName = "SQ_BID_PK")
	@Column(name = "BID_ID")
	private int bidId;
	@Column(name = "AMOUNT")
	private int amount;
/*********************************************************************************/	
	//Many to one AppUser --> many Auction
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	private User user;	
/*********************************************************************************/	
	//Many to one AppUser --> many Auction
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUCTION_ID")
	private Auction auction;
/*********************************************************************************/
public int getBidId() {
	return bidId;
}
public void setBidId(int bidId) {
	this.bidId = bidId;
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}

public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Auction getAuction() {
	return auction;
}
public void setAuction(Auction auction) {
	this.auction = auction;
}	
public String toString() {
	return "" + this.amount + this.user + this.auction;
}

}
