package DTOs;


import Models.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMovieResponseDto {


    private Long bookingId;
    private double amount;
    private ResponseStatus bookingReponse;
}
