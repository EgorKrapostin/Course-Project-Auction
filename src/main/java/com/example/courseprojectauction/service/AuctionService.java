package com.example.courseprojectauction.service;

import com.example.courseprojectauction.DTO.BidDTO;
import com.example.courseprojectauction.model.Bid;
import com.example.courseprojectauction.model.Lot;

import java.util.List;
import java.util.Optional;

public interface AuctionService {
    void createLot(Lot lot);

    void startBidding(int id);

    void stopBidding(int id);

    void makeBid(Bid bid, int id);

    Optional<BidDTO> getFirtsBid(int id);

    Optional<BidDTO> getMostFrequent(int id);
}
