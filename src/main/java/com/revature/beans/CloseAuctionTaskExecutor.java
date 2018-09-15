package com.revature.beans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import com.revature.domain.Auction;
import com.revature.repository.AuctionRepository;


public class CloseAuctionTaskExecutor {
	@Autowired
	private AuctionRepository auctionRepository;
  private class AuctionCloseTask implements Runnable {
	@Autowired
	private AuctionRepository auctionRepository;
    private Auction auction;

    public AuctionCloseTask(Auction auction) {
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

  private TaskExecutor taskExecutor;

  public CloseAuctionTaskExecutor(TaskExecutor taskExecutor) {
    this.taskExecutor = taskExecutor;
  }

  public void printMessages() {
    for(Auction a: auctionRepository.getAllAuctions()) {
      taskExecutor.execute(new AuctionCloseTask(a));
    }
  }
}