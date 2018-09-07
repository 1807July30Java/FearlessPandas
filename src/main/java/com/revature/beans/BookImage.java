package com.revature.beans;

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
@Table(name = "BOOK_IMAGE")
public class BookImage {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookImageSequence")
	@SequenceGenerator(allocationSize = 1, name = "bookImageSequence", sequenceName = "SQ_BOOK_IMAGE_PK")
	@Column(name = "IMAGE_ID")
	private int imageId;
	@Column(name = "IMAGE_BLOB")
	private byte[] imageBlob;
	@Column(name = "BOOK_ID")
	private int bookId;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BOOK_ID")
	private Book book;
/************************************************************************************************************/
	//Constructors

public BookImage(int imageId, byte[] imageBlob, int bookId) {
	super();
	this.imageId = imageId;
	this.imageBlob = imageBlob;
	this.bookId = bookId;
}
public BookImage() {
	super();
	// TODO Auto-generated constructor stub
}
/************************************************************************************************************/	
	//Getters and Setters
public int getImageId() {
	return imageId;
}
public void setImageId(int imageId) {
	this.imageId = imageId;
}
public byte[] getImageBlob() {
	return imageBlob;
}
public void setImageBlob(byte[] imageBlob) {
	this.imageBlob = imageBlob;
}
public int getBookId() {
	return bookId;
}
public void setBookId(int bookId) {
	this.bookId = bookId;
}
public Book getBook() {
	return book;
}
public void setBook(Book book) {
	this.book = book;
}
/************************************************************************************************************/

}
