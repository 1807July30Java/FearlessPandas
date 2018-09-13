package com.revature.aspect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.revature.repository.AuctionRepository;

@Component
public class AuctionCloseEventListener {
	@Autowired
	AuctionRepository ar;
	@EventListener(condition = "#auctionCloseEventListener.isClosed")
	public void handleAuctionCloseEvent(AuctionCloseEvent ace) {
		ar.setClosed(ace.getAuction());
	}
}
