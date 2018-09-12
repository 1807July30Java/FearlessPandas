package com.revature.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@NamedQueries({
	@NamedQuery(name = "getAllAuctions",query = "from Auction"),
	@NamedQuery(name = "getAllAuctionsBefore", query = "from Auction where endDate < :end_date")
	
})
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
	private Date createDate;
	@Column(name = "END_DATE")
	private Date endDate;
	@Column(name = "MINIMUM_PRICE")
	private int minimumPrice;
	@Column(name = "BUY_IT_NOW")
	private int buyItNow;
/*********************************************************************************/	
	//Many to one User --> many Auction
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USER_ID")
	private User user;
/*********************************************************************************/

/*******************************************************************************/	
	 //One to many auction --> bid
	@OneToMany(mappedBy = "auction", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Bid> bids;
/*********************************************************************************/	
public Auction() {
	
}
public Auction(int auctionId, Book book, int minimumPrice,int buyItNow) {
	super();
	this.auctionId = auctionId;
	this.minimumPrice = minimumPrice;
	this.buyItNow = buyItNow;
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
public Date getCreateDate() {
	return createDate;
}
public void setCreateDate(Long createDate) {
	this.createDate =  new Date(createDate);
}
public Date getEndDate() {
	return endDate;
}
public void setEndDate(Long endDate) {
	this.endDate = new Date(endDate);
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
