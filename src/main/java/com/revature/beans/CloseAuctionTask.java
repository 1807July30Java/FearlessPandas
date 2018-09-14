package com.revature.beans;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.domain.Auction;
import com.revature.repository.AuctionRepository;

public class CloseAuctionTask implements Runnable {
	@Autowired
	private AuctionRepository auctionRepository;
    private Auction auction;

    public CloseAuctionTask(Auction auction) {
      this.auction = auction;
    }
    public Auction getAuction() {
    	return auction;
    }
    public void run() {
    	System.out.println("trying to close");
      auctionRepository.setClosed(auction);
    }

  }