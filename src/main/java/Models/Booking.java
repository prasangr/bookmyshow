package Models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Booking extends BaseModel {// ticket

    private List<ShowSeatType> showSeats;
    private User user;
    private double amount;
    private List<Payment> payments;
    private BookingStatus bookingStatus;


}
