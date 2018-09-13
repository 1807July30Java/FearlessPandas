package com.revature.domain;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.sql.Date;

@NamedQueries({
        @NamedQuery(name = "getAllAuctions", query = "from Auction"),
        @NamedQuery(name = "getAllAuctionsBefore", query = "from Auction where endDate < :end_date"),
        @NamedQuery(name = "getUserAuctions", query = "from Auction where user.userId is :userId")
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
    @Column(name = "ISCLOSED")
    private boolean isClosed;
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

    public Book getBookId() {
        return book;
    }

    public void setBookId(Book book) {
        this.book = book;
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
    public boolean isClosed() {
        Long a = (System.currentTimeMillis() - this.endDate.getTime());
        return a > 0;
    }

    public void setClosed(boolean isClosed) {
        this.isClosed = this.endDate.getTime() < System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Auction [auctionId=" + auctionId + ", book=" + book + ", createDate=" + createDate + ", endDate="
                + endDate + ", minimumPrice=" + minimumPrice + ", buyItNow=" + buyItNow + ", user=" + user + ", bids=" + "]";
    }

}
