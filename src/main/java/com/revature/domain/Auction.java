package com.revature.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "AUCTION")
public class Auction {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auctionSequence")
	@SequenceGenerator(allocationSize = 1, name = "auctionSequence", sequenceName = "SQ_AUCTION_PK")
	@JoinColumn(name = "AUCTION_ID")
	private int auctionId;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "BOOK_ID")
	private Book book;
	@Column(name = "CREATE_DATE")
	private LocalDate createDate;
	@Column(name = "END_DATE")
	private LocalDate endDate;
	@Column(name = "MINIMUM_PRICE")
	private int minimumPrice;
	@Column(name = "BUY_IT_NOW")
	private int buyItNow;
/*********************************************************************************/	
	//Many to one User --> many Auction
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private User user;
/*********************************************************************************/

/*******************************************************************************/	
	 //One to many auction --> bid
	@OneToMany(mappedBy = "auction", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Bid> bids;
/*********************************************************************************/	
	//Gnerating Constructor
public Auction(int auctionId, Book book, LocalDate createDate, LocalDate endDate, int minimumPrice,
		int buyItNow, User user) {
	super();
	this.auctionId = auctionId;
	this.book = book;
	this.createDate = createDate;
	this.endDate = endDate;
	this.minimumPrice = minimumPrice;
	this.buyItNow = buyItNow;
	this.user = user;
}
/*********************************************************************************/
	//Getters and Setters
public int getAuctionId() {
	return auctionId;
}
public void setAuctionId(int auctionId) {
	this.auctionId = auctionId;
}
public Book getBookId() {
	return book;
}
public void setBookId(Book book) {
	this.book = book;
}
public LocalDate getCreateDate() {
	return createDate;
}
public void setCreateDate(LocalDate createDate) {
	this.createDate = createDate;
}
public LocalDate getEndDate() {
	return endDate;
}
public void setEndDate(LocalDate endDate) {
	this.endDate = endDate;
}
public int getMinimumPrice() {
	return minimumPrice;
}
public void setMinimumPrice(int minimumPrice) {
	this.minimumPrice = minimumPrice;
}
public int getBuyItNow() {
	return buyItNow;
}
public void setBuyItNow(int buyItNow) {
	this.buyItNow = buyItNow;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Book getBook() {
	return book;
}
public void setBook(Book book) {
	this.book = book;
}
/*********************************************************************************/
	
}
