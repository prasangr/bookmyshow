package DTOs;

import Models.ShowSeat;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookMovieRequestDto {
    List<Long> showSeatIds;
    Long userId;
    Long showId;
}
