package com.revature.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;

@NamedQueries({
        @NamedQuery(name = "getAllAuctions", query = "from Auction"),


        @NamedQuery(name = "getAllAuctionsBefore", query = "from Auction where endDate < :endDate"),
        @NamedQuery(name = "getBookAuctionsBefore", query = "from Auction where endDate <= :endDate and book.bookId is :bookId"),
       
        @NamedQuery(name ="getAuctionByBookId", query = "from Auction where book.bookId is :bookId"),
        @NamedQuery(name = "getAuctionByBookIdAndMinPrice", query = "from Auction where book.bookId is :bookId and minimumPrice is :minimumPrice"),
        @NamedQuery(name = "getAuctionByBookIdAndBuyNowPrice", query = "from Auction where book.bookId is :bookId and buyItNow is :buyItNow"),
        @NamedQuery(name = "getAuctionByBookIdBuyNowMinPrice", query = "from Auction where book.bookId is :bookId and buyItNow is :buyItNow and minimumPrice is :minimumPrice and endDate <= :endDate"),
        @NamedQuery(name = "getAuctionByGeneralBook", query = "from Auction where book.bookId is :bookId or buyItNow is :buyItNow or minimumPrice is :minimumPrice or endDate <= :endDate")
        

       
        @NamedQuery(name = "getUserAuctions", query = "from Auction where user.userId is :userId order by createDate desc")


})
@Component
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
    private double minimumPrice;
    @Column(name = "BUY_IT_NOW")
    private double buyItNow;
    @Column(name = "ISCLOSED", columnDefinition = "Number(1,0) default 0")
    private int isclosed;
    /*********************************************************************************/
    //Many to one User --> many Auction
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private User user;
    /*********************************************************************************/

    /*********************************************************************************/

    /*********************************************************************************/
    public Auction() {

    }

    public Auction(int auctionId, Book book, int minimumPrice, int buyItNow) {
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(double minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public double getBuyItNow() {
        return buyItNow;
    }

    public void setBuyItNow(double buyItNow) {
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
    public int isClosed() {
        Long a = (System.currentTimeMillis() - this.endDate.getTime());
        System.out.println("Auction is closed?" + a + " end date " + this.endDate.getTime());
        if(a > 14400000) {
        	System.out.println("Auction Is Closed");
        	return 1  ;
        }else {
        	System.out.println("Auction Is Not Closed");
        	return 0;
        }
    }

    public void setClosed() { 	
        if(this.endDate.getTime() + 14400000 < System.currentTimeMillis()) {
        	this.isclosed =  1;
        }else {
        	this.isclosed =  0;
        }
    }
    public void setClosed(int isclosed) {
        this.isclosed = isclosed;
    }

    @Override
    public String toString() {
        return "Auction [auctionId=" + auctionId + ", book=" + book + ", createDate=" + createDate + ", endDate="
                + endDate + ", minimumPrice=" + minimumPrice + ", buyItNow=" + buyItNow + ", user=" + user+ "]";
    }

}
