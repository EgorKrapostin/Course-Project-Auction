package com.example.courseprojectauction.service;

import com.example.courseprojectauction.model.Lot;

public interface AuctionService {
    void createLot(Lot lot);

    void startBidding(int id);

    void stopBidding(int id);
}
