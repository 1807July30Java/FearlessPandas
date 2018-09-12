package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.domain.Auction;
import com.revature.repository.AuctionRepository;

@Service(value = "auctionService")
@Scope(value = "prototype")
public class AuctionService {
	@Autowired
	AuctionRepository auctionRepository;
	public Auction saveAuction(Auction a) throws Exception {
		return auctionRepository.saveAuctionWithUserAndBook(a);
	}
	public Auction getAuctionById(int id) {
		return auctionRepository.getAuctionById(id);
	}
}
