package Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Booking extends BaseModel {// ticket

    @ManyToMany
    private List<ShowSeatType> showSeats;
    @ManyToOne
    private User user;
    private double amount;
    @OneToMany
    private List<Payment> payments;
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;


    /*

        1   -->  M          one booking will have many show seats
      Booking   ShowSeat  => Many to many
        M   <-- 1           one showseat may belong to 2or more bookings if the previous bookings are cancelled

        1   --> 1
       Booking  User    => Many to one
        M   <-- 1

         1  -->  M
      Booking  Payment  => One to Many
         1 <-- 1


       Booking  bookingstatus

    * */




}
