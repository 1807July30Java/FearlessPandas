package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.domain.Auction;
import com.revature.domain.Bid;
import com.revature.repository.BidRepository;
import com.revature.repository.UserRepository;

@Service("bidService")
public class BidService {
	@Autowired
	BidRepository bidRepository;
	public Bid addBidToAuction(Bid b) throws Exception {
		return bidRepository.addbidToAuction(b);
	}
	public List<Bid> getBidsByAuction(Auction a){
		return bidRepository.getBidsByAuction(a);
	}
}
