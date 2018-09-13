package com.revature.service;


import java.sql.Date;
import java.util.List;

import org.hibernate.Session;

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
	public Auction saveAuctionWithBook(Auction a) throws Exception {
		if(a.getEndDate().getTime() - a.getCreateDate().getTime() > 0) {
			return auctionRepository.saveAuctionWithUserAndBookId(a);
		}else {
			throw new Exception("Ending date must be after start date");
		}
	}
	public Auction getAuctionById(int id) {
		return auctionRepository.getAuctionById(id);
	}
	public List<Auction> getAllAuctions() {
			return auctionRepository.getAllAuctions();
	}
	public List<Auction> getAllAuctionsBefore(Date d){
		return auctionRepository.getAllAuctionsBefore(d);
	}
	public boolean isClosed(int id) {
		Auction a = auctionRepository.getAuctionById(id);
		return auctionRepository.isClosed(a);
	}
}
