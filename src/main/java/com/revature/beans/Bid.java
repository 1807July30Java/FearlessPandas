package com.revature.beans;

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
	@Column(name = "USER_ID")
	private int userId;
	@Column(name = "AUCTION_ID")
	private int auctionId;
	@Column(name = "AMOUNT")
	private int amount;
	@Column(name = "DATE")
	private LocalDate date;
/*********************************************************************************/	
	//Many to one AppUser --> many Auction
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	private AppUser user;	
/*********************************************************************************/	
	//Many to one AppUser --> many Auction
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AUCTION_ID")
	private Auction auction;
/*********************************************************************************/	

}
