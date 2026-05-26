package Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeatType extends BaseModel{
    @ManyToOne
    private Show show;

    @Enumerated
    private SeatType seatType;
    private int price;


    /*

         1    --> 1
    ShowSeatType  show  => Many to one
        M   <-- 1

        S



    */

}
