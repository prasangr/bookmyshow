package Repositories;

import Models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    @Override
    Optional<ShowSeat> findById(Long aLong);

    @Override
    ShowSeat save(ShowSeat showSeat);  //upsert operation---> Update + Insert
    // IF show seat object is not there in the db , then insert it .
    // else if its already there then update the existing one


}
