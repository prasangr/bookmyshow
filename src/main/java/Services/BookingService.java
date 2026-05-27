package Services;

import Exception.ShowNotFoundException;
import Exception.ShowSeatNotAvailableException;
import Exception.UserNotFoundException;
import Models.*;
import Repositories.BookingRepository;
import Repositories.ShowRepository;
import Repositories.ShowSeatRepository;
import Repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private BookingRepository bookingRepository;
    private PriceCalculatorService priceCalculatorService;

    public BookingService(UserRepository userRepository, ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,BookingRepository bookingRepository,
                          PriceCalculatorService priceCalculatorService) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository=bookingRepository;
        this.priceCalculatorService=priceCalculatorService;
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)  // making this method as transactional, and setting the isolation level to be SERIALIZABLE,
        // so that we can put a lock on the db level as well
     public Booking BookMovie(Long userId, Long showId, List<Long> showSeatsIds){

         /*
         STEPS:
          -------Take the lock ------------
         1. Get the user from user
         2. get the show from show id
         3. get the list of show seats from show seatids
         4. Check if all the showseats are available
         5. If all show seats are not available, then throw an exception
         6. If all are available , then change the status to be LOCKED
         7. Change the status in DB as well
         8. Create the Booking object, and store it in the db
         9. Return the booking object
            -------Release the lock --------
         **/

        //1. Get the user from user
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("Invalid User ID");
        }
        User bookedBy=userOptional.get();
        //2. get the show from show id
        Optional<Show> showOptional = showRepository.findById(showId);
        if (showOptional.isEmpty()){
            throw new ShowNotFoundException("Invalid Show ID");
        }
        Show show = showOptional.get();

        // 3. get the list of show seats from show seatids
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatsIds);

        //4.Check if all the showseats are available
        for(ShowSeat showSeat:showSeats){
            if (showSeat.getShowSeatStatus()!= Models.ShowSeatStatus.AVAILABLE){

            //5. If all show seats are not available, then throw an exception
            throw new ShowSeatNotAvailableException("Show Seat with id "+showSeat.getId()+" is not) available");
                }
            }

        //6.If all are available , then change the status to be LOCKED
        List<ShowSeat> lockedShowSeats=new ArrayList<>();
            for (ShowSeat showSeat:showSeats){
                showSeat.setShowSeatStatus(Models.ShowSeatStatus.LOCKED);
                // 7. Change the status in DB as well
                lockedShowSeats.add(showSeatRepository.save(showSeat));
            }

        //8. Create the Booking object, and store it in the db

            Booking booking=new Booking();
            // Booking Id will be set by the database automatically by incrementing the previous one by 1
            booking.setUser(bookedBy);
            booking.setBookingStatus(BookingStatus.IN_PROGRESS);
            booking.setPayments(new ArrayList<>());
            booking.setShowSeats(lockedShowSeats);
            booking.setCreatedAt(Date.from(new Date().toInstant()));
            booking.setAmount(priceCalculatorService.CalculateBookingPrice(showSeats, show));

            //9.Saving the booking object in the database
       return bookingRepository.save(booking);

        //-----------LOCK IS RELEASED FROM DATABASE--------------
     }
}
