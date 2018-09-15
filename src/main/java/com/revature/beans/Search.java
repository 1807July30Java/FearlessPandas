package com.revature.beans;

import java.sql.Date;

public class Search {
	private Date endDate;
	private String author;
	private String publisher;
	private int isbn;
	private int minimumPrice;
	private int buyItNow;
	private String title;
	
	
	
	public Search(Date endDate, String author, String publisher, int isbn, int minimumPrice, int buyItNow,
			String title) {
		super();
		this.endDate = endDate;
		this.author = author;
		this.publisher = publisher;
		this.isbn = isbn;
		this.minimumPrice = minimumPrice;
		this.buyItNow = buyItNow;
		this.title = title;
	}
	
	@Override
	public String toString() {
		return "Search [endDate=" + endDate + ", author=" + author + ", publisher=" + publisher + ", isbn=" + isbn
				+ ", minimumPrice=" + minimumPrice + ", buyItNow=" + buyItNow + ", title=" + title + "]";
	}

	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Search() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
