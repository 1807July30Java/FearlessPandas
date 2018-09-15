package com.revature.service;


import com.revature.domain.Auction;
import com.revature.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service(value = "auctionService")
@Scope(value = "prototype")
public class AuctionService {
    @Autowired
    AuctionRepository auctionRepository;
    public Auction processAuction(Auction a, int userid) {
    	if(! (a.getUser().getUserId() == userid)) {
    		a.getUser().clearSensitiveData();
    	}
    	return a;
    }
    public List<Auction> processAuctions(List<Auction> auctions, int userId){
    	for(Auction a:auctions) {
    		if(! (a.getUser().getUserId() == userId)) {
        		a.getUser().clearSensitiveData();
        	}
    	}
    	return auctions;
    }
    public Auction saveAuction(Auction a) throws Exception {
        return auctionRepository.saveAuctionWithUserAndBook(a);
    }

    public Auction saveAuctionWithBook(Auction a) throws Exception {
        if (a.getEndDate().getTime() - a.getCreateDate().getTime() > 0) {
            return auctionRepository.saveAuctionWithUserAndBookId(a);
        } else {
            throw new Exception("Ending date must be after start date");
        }
    }

    public Auction getAuctionById(int id,int userId) {
        return processAuction(auctionRepository.getAuctionById(id),userId);
    }

    public List<Auction> getAllAuctions(int userId) {
        return processAuctions(auctionRepository.getAllAuctions(),userId);
    }

    public List<Auction> getAllAuctionsBefore(Date d,int userId) {
        return processAuctions(auctionRepository.getAllAuctionsBefore(d), userId);
    }

    public List<Auction> getAllUserAuctions(int id) {
        return auctionRepository.getAllUserAuctions(id);
    }

    public int isClosed(int id) {
        Auction a = auctionRepository.getAuctionById(id);
        return auctionRepository.isClosed(a);
    }
    public List<Auction> getClosedAuctions(int userId){
    	return processAuctions(auctionRepository.getClosed(),userId);
    }
}
