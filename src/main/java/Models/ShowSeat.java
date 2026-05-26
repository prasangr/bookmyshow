package Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class ShowSeat extends BaseModel{

    @ManyToOne
    private Seat seat;

    @ManyToOne
    private Show show;
    private ShowSeatStatus showSeatStatus;
/*
      1    --> 1
    Showseat  show  => Many to one
        M   <-- 1

        1  --> 1
    ShowSeat  Seat  => Many to one
        M  <-- 1

 */

}
