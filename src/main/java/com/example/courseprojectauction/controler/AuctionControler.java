package com.example.courseprojectauction.controler;

import com.example.courseprojectauction.model.Lot;
import com.example.courseprojectauction.service.AuctionService;
import org.springframework.web.bind.annotation.*;

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
    public void startBidding(@RequestParam int id) {
        auctionService.startBidding(id);
    }
    @PostMapping("/stop/{id}")
    public void stopBidding(@RequestParam int id) {
        auctionService.stopBidding(id);
    }
}
