package com.revature.beans;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.revature.domain.Auction;
import com.revature.repository.AuctionRepository;
@Component("closeAuctionTask")
@Scope(value = "prototype")
@Transactional
@EnableTransactionManagement
public class CloseAuctionTask implements Runnable {
	private AuctionRepository auctionRepository;
	
    private Auction auction;

    public CloseAuctionTask(Auction auction, AuctionRepository ar){
      this.auction = auction;
      this.auctionRepository = ar;
    }
    public Auction getAuction() {
    	return auction;
    }
    public void run() {
    	System.out.println("trying to close");
    	System.out.println(auctionRepository == null);
    	Auction a = auctionRepository.getAuctionById(auction.getAuctionId());
    	System.out.println(a);
    	
    	System.out.println(auction == null);
    	auction = auctionRepository.getAuctionById(auction.getAuctionId());
    	auctionRepository.setClosed(auction);
    	
    	System.out.println("Is auction closed?????????? " + auctionRepository.isClosed(auction));
    	Auction b = auctionRepository.getAuctionById(79);
    	System.out.println("Is old auction closed?????????? " + auctionRepository.isClosed(b));
    	
    }

  }