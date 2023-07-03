package com.example.courseprojectauction.service;

import com.example.courseprojectauction.model.Lot;
import com.example.courseprojectauction.model.Status;
import com.example.courseprojectauction.repository.AuctionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;

    public AuctionServiceImpl(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    @Override
    public void createLot(Lot lot) {
        auctionRepository.save(lot);
    }

    @Override
    public void startBidding(int id) {
        Lot lot = new Lot();
        lot.setId(auctionRepository.findById(id).get().getId());
        lot.setTitle(auctionRepository.findById(id).get().getTitle());
        lot.setDescription(auctionRepository.findById(id).get().getDescription());
        lot.setStartPrice(auctionRepository.findById(id).get().getStartPrice());
        lot.setBidPrice(auctionRepository.findById(id).get().getBidPrice());
        lot.setStatus(Status.STARTED);
        auctionRepository.save(lot);
    }

    @Override
    public void stopBidding(int id) {
        Lot lot = new Lot();
        lot.setId(auctionRepository.findById(id).get().getId());
        lot.setTitle(auctionRepository.findById(id).get().getTitle());
        lot.setDescription(auctionRepository.findById(id).get().getDescription());
        lot.setStartPrice(auctionRepository.findById(id).get().getStartPrice());
        lot.setBidPrice(auctionRepository.findById(id).get().getBidPrice());
        lot.setStatus(Status.STOPPED);
        auctionRepository.save(lot);
    }
}
