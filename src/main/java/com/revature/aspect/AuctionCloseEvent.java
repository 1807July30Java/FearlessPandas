package com.revature.aspect;

import com.revature.domain.Auction;
import org.springframework.context.ApplicationEvent;

public class AuctionCloseEvent extends ApplicationEvent {

    private Auction auction;
    private final int isClosed;
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public AuctionCloseEvent(Object source, Auction auction) {
        super(source);
        this.auction = auction;
        this.isClosed = auction.isClosed();
    }

    public Auction getAuction() {
        return auction;
    }

    public int isClosed() {
        return isClosed;
    }
}