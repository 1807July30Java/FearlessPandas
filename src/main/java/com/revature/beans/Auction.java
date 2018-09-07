package com.revature.beans;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.orm.hibernate3.LocalDataSourceConnectionProvider;

@Entity
@Table(name = "AUCTION")
public class Auction {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auctionSequence")
	@SequenceGenerator(allocationSize = 1, name = "auctionSequence", sequenceName = "SQ_AUCTION_PK")
	@Column(name = "AUCTION_ID")
	private int auctionId;
	@Column(name = "BOOK_ID")
	private int bookId;
	@Column(name = "USER_ID")
	private int userId;
	@Column(name = "CREATE_DATE")
	private LocalDate createDate;
	@Column(name = "END_DATE")
	private LocalDate endDate;
	@Column(name = "MINIMUM_PRICE")
	private int minimumPrice;
	@Column(name = "BUY_IT_NOW")
	private int buyItNow;
/*********************************************************************************/	
	//Many to one AppUser --> many Auction
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	private AppUser user;
/*********************************************************************************/
	//Many to one Book --> many Auction
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BOOK_ID")
	private Book book;
/*******************************************************************************/	
	 //One to many auction --> bid
	@OneToMany(mappedBy = "auction", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Bid> bids;
/*********************************************************************************/	
	//Gnerating Constructor
public Auction(int auctionId, int bookId, int userId, LocalDate createDate, LocalDate endDate, int minimumPrice,
		int buyItNow, AppUser user, Book book) {
	super();
	this.auctionId = auctionId;
	this.bookId = bookId;
	this.userId = userId;
	this.createDate = createDate;
	this.endDate = endDate;
	this.minimumPrice = minimumPrice;
	this.buyItNow = buyItNow;
	this.user = user;
	this.book = book;
}
/*********************************************************************************/
	//Getters and Setters
public int getAuctionId() {
	return auctionId;
}
public void setAuctionId(int auctionId) {
	this.auctionId = auctionId;
}
public int getBookId() {
	return bookId;
}
public void setBookId(int bookId) {
	this.bookId = bookId;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
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
public AppUser getUser() {
	return user;
}
public void setUser(AppUser user) {
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
