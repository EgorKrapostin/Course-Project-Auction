package com.example.courseprojectauction.controler;

import com.example.courseprojectauction.DTO.BidDTO;
import com.example.courseprojectauction.DTO.BidderNameDTO;
import com.example.courseprojectauction.DTO.LotDTO;
import com.example.courseprojectauction.DTO.LotFullInfo;
import com.example.courseprojectauction.model.Bid;
import com.example.courseprojectauction.model.Lot;
import com.example.courseprojectauction.service.AuctionService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
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
        auctionService.makeBid(bid.fromDTO(), id);
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

    @GetMapping("/lot")
    public List<Lot> getLotsInPageFormat(@RequestParam(required = false, defaultValue = "0") int page, int status) {

        return auctionService.getLotsInPageFormat(page, status);
    }

    @GetMapping("/export")
    public ResponseEntity<Resource> getLotsInCSV() throws IOException {

        StringWriter writer = new StringWriter();

        String name = "lots.csv";
        String[] HEADERS = {"id", "title", "status", "description", "startPrice", "bidPrice"};

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .build();


        try (final CSVPrinter printer = new CSVPrinter(writer, csvFormat)) {
            List<Lot> list = auctionService.getLotsInCSV();
            list.forEach(lot -> {
                try {
                    printer.printRecord(lot);

                    printer.flush();
                    printer.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            });
        }

        Resource resource = new PathResource(name);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + name + "\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(resource);
    }
}
