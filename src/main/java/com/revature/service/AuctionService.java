package com.revature.service;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.domain.Auction;

@Service(value = "auctionService")
@Scope("Prototype")
public class AuctionService {
	@Autowired
	AuctionService auctionService;
	public Auction saveAuction(Auction a) {
		return auctionService.saveAuction(a);
	}
	public Auction getAuctionById(int id) {
		return auctionService.getAuctionById(id);
	}
}
