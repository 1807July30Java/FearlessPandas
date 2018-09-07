package com.revature.beans;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="BOOK")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookSequence")
	@SequenceGenerator(allocationSize = 1, name = "bookSequence", sequenceName = "SQ_BOOK_PK")
	@Column(name = "BOOK_ID")
	private int bookId;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "AUTHOR")
	private String author;
	@Column(name = "Description")
	private String description;
	@Column(name = "CONDITION_ID")
	private int conditionId;
	@Column(name = "PUBLISHER")
	private String publisher;
	@Column(name = "GENRE_ID")
	private int genreId;
	
/*******************************************************************************/
	//Many to many with genres	
	 @ManyToMany(cascade = {CascadeType.ALL})
	    @JoinTable(
	            name = "BOOK_GENRE",
	            joinColumns = {@JoinColumn(name = "BOOK_ID")},
	            inverseJoinColumns = {@JoinColumn(name = "GENRE_ID")}
	    )
	 private Set<Genre> genres = new HashSet<>();
/*******************************************************************************/	
	 //One to many book to book image
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BookImage> bookImages;
/*******************************************************************************/
	//One to many book to book condition
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BookCondition> bookConditions;

/*******************************************************************************/
	//One to many User --> Auction
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Auction> auctions; 
/*******************************************************************************/
	//Constructors 

	public Book(int bookId, String title, String author, String description, int conditionId, String publisher, int genreId,
			Set<Genre> genres, List<BookImage> bookImages, List<BookCondition> bookConditions, List<Auction> auctions) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.description = description;
		this.conditionId = conditionId;
		this.publisher = publisher;
		this.genreId = genreId;
		this.genres = genres;
		this.bookImages = bookImages;
		this.bookConditions = bookConditions;
		this.auctions = auctions;
	}
/*******************************************************************************/
	//Getters and Setter
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getConditionId() {
		return conditionId;
	}
	public void setConditionId(int conditionId) {
		this.conditionId = conditionId;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getGenreId() {
		return genreId;
	}
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	public Set<Genre> getGenres() {
		return genres;
	}
	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}
	public List<BookImage> getBookImages() {
		return bookImages;
	}
	public void setBookImages(List<BookImage> bookImages) {
		this.bookImages = bookImages;
	}
	public List<BookCondition> getBookConditions() {
		return bookConditions;
	}
	public void setBookConditions(List<BookCondition> bookConditions) {
		this.bookConditions = bookConditions;
	}
	public List<Auction> getAuctions() {
		return auctions;
	}
	public void setAuctions(List<Auction> auctions) {
		this.auctions = auctions;
	}
		
	
	
}
