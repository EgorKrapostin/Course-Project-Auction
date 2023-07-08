package com.example.courseprojectauction.repository;

import com.example.courseprojectauction.DTO.LotFullInfo;
import com.example.courseprojectauction.model.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuctionRepository extends JpaRepository<Lot,Integer> {


//    @Query(value = "Select *,(start_price + bid_price * sum(bid.lot_id))  " +
//            "from lot " +
//            "         join bid " +
//            "              on bid.lot_id = lot.id and bid.lot_id= :id " +
//            "group by lot.id,bid.id " +
//            "order by bid.date desc " +
//            "limit 1",
//    nativeQuery = true)
    @Query(value = "select new com.example.courseprojectauction.DTO.LotFullInfo( " +
            "l.id, " +
            "l.title, " +
            "l.status ," +
            "l.description, " +
            "l.start_price, " +
            "l.bid_price, " +
            "l.current_price+(l.start_price+l.bid_price * count(b.lot_id))," +
            "b.name, " +
            "b.localDateTime " +
            "from Lot l join fetch Bid b " +
            "where l.id = b.lot_id and b.lot_id = :id " +
            "group by l.id, b.id " +
            "order by b.date desc " +
            "limit 1")
    Optional<LotFullInfo> getLotFullInfoById(int id);
}
