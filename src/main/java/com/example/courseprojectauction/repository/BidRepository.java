package com.example.courseprojectauction.repository;

import com.example.courseprojectauction.DTO.LotFullInfo;
import com.example.courseprojectauction.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid,Integer> {

    @Query(value = "SELECT * FROM bid " +
            "WHERE lot_id = :id " +
            "LIMIT 1",
            nativeQuery = true)
    Bid getFirstBid(int id);

    @Query(value = "SELECT * FROM bid " +
            "WHERE lot_id = :id " +
            "GROUP BY bid.id " +
            "ORDER BY count(name),name desc " +
            "LIMIT 1",
            nativeQuery = true)
    Bid getMostFrequent(int id);

}
