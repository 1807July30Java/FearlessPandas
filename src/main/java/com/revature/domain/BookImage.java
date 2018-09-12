package com.revature.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "getBookImageByBookId", query = "from BookImage where book.bookId = :bookId"),
        @NamedQuery(name = "getBookImageById", query = "from BookImage where imageId = :imageId")
})

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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    /************************************************************************************************************/
    //Constructors
    public BookImage(int imageId, byte[] imageBlob, Book book) {
        super();
        this.imageId = imageId;
        this.imageBlob = imageBlob;
        this.book = book;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
/************************************************************************************************************/

}
