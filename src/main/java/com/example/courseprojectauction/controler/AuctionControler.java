package com.example.courseprojectauction.controler;

import com.example.courseprojectauction.DTO.BidDTO;
import com.example.courseprojectauction.model.Bid;
import com.example.courseprojectauction.model.Lot;
import com.example.courseprojectauction.service.AuctionService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("auction")
public class AuctionControler {

    private final AuctionService auctionService;

    public AuctionControler(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @PostMapping("/create")
    public void createLot(@RequestBody Lot lot) {
        auctionService.createLot(lot);
    }

    @PostMapping("/start/{id}")
    public void startBidding(@PathVariable int id) {
        auctionService.startBidding(id);
    }

    @PostMapping("/stop/{id}")
    public void stopBidding(@PathVariable int id) {
        auctionService.stopBidding(id);
    }

    @PostMapping("/bid/{id}")
    public void makeBid(@RequestBody Bid bid,
                        @PathVariable int id) {
        auctionService.makeBid(bid,id);
    }

    @GetMapping("/first/{id}")
    public Optional<BidDTO> getFirstBid(@PathVariable int id) {

        return auctionService.getFirtsBid(id);
    }

    @GetMapping("/frequent/{id}")
    public Optional<BidDTO> getMostFrequent(@PathVariable int id) {

        return auctionService.getMostFrequent(id);
    }
}
