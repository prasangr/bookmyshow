package Repositories;

import Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {


    @Override
    Booking save(Booking booking);
}
