package Services;

import Models.Show;
import Models.ShowSeat;
import Models.ShowSeatType;
import Repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class PriceCalculatorService {
    private ShowSeatTypeRepository showSeatTypeRepository;

    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int CalculateBookingPrice(List<ShowSeat> showSeats, Show show) {
        int amount = 0;
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);

        for(ShowSeat showSeat : showSeats) {
            for(ShowSeatType showSeatType: showSeatTypes){
            if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
                amount += showSeatType.getPrice();
            }
        }
        }
        return amount;

}

}
