package com.example.courseprojectauction.service;

import com.example.courseprojectauction.DTO.BidDTO;
import com.example.courseprojectauction.model.Bid;
import com.example.courseprojectauction.model.Lot;
import com.example.courseprojectauction.model.Status;
import com.example.courseprojectauction.repository.AuctionRepository;
import com.example.courseprojectauction.repository.BidRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;
    private final BidRepository bidRepository;

    public AuctionServiceImpl(AuctionRepository auctionRepository, BidRepository bidRepository) {
        this.auctionRepository = auctionRepository;
        this.bidRepository = bidRepository;
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

    @Override
    public void makeBid(Bid bid, int id) {
        Lot lot = new Lot();
        lot.setId(id);
        bid.setLot(lot);
        bidRepository.save(bid);
    }

    @Override
    public Optional<BidDTO> getFirtsBid(int id) {
        return Optional.ofNullable(
                BidDTO.fromBid(bidRepository.getFirstBid(id)));
    }

    @Override
    public Optional<BidDTO> getMostFrequent(int id) {
        return Optional.ofNullable(BidDTO.fromBid(bidRepository.getMostFrequent(id)));
    }
}
