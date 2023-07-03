package com.example.courseprojectauction.repository;

import com.example.courseprojectauction.model.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends JpaRepository<Lot,Integer> {

}
