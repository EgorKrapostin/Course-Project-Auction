package com.example.courseprojectauction.controler;

import com.example.courseprojectauction.DTO.BidDTO;
import com.example.courseprojectauction.DTO.BidderNameDTO;
import com.example.courseprojectauction.DTO.LotDTO;
import com.example.courseprojectauction.DTO.LotFullInfo;
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
    public void createLot(@RequestBody LotDTO lot) {
        auctionService.createLot(lot.fromDTO());
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
    public void makeBid(@RequestBody BidderNameDTO bid,
                        @PathVariable int id) {
        auctionService.makeBid(bid.fromDTO(),id);
    }

    @GetMapping("/first/{id}")
    public Optional<BidDTO> getFirstBid(@PathVariable int id) {

        return auctionService.getFirtsBid(id);
    }

    @GetMapping("/frequent/{id}")
    public Optional<BidDTO> getMostFrequent(@PathVariable int id) {

        return auctionService.getMostFrequent(id);
    }

    @GetMapping("/fullInfo/{id}")
    public Optional<LotFullInfo> getLotFullInfoById(@PathVariable int id) {

        return auctionService.getLotFullInfoById(id);
    }
}
