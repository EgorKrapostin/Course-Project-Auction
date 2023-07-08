package com.example.courseprojectauction.DTO;

import com.example.courseprojectauction.model.Bid;

import java.time.LocalDateTime;

public class BidderNameDTO {

    private String name;

    public BidderNameDTO() {
    }

    public static BidderNameDTO fromBid(Bid bid) {
        BidderNameDTO bidderNameDTO = new BidderNameDTO();
        bidderNameDTO.setName(bid.getName());
        return bidderNameDTO;
    }

    public Bid fromDTO() {
        Bid bid = new Bid();
        bid.setName(this.getName());
        bid.setLocalDateTime(LocalDateTime.now());
        return bid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
