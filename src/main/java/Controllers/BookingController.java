package Controllers;

import DTOs.BookMovieRequestDto;
import DTOs.BookMovieResponseDto;
import Models.Booking;
import Models.ResponseStatus;
import Services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    @Autowired
    BookingService bookingService;


    public BookMovieResponseDto bookMovie(BookMovieRequestDto bookMovieRequestDto) {
        BookMovieResponseDto bookMovieResponseDto=new BookMovieResponseDto();
        try {
               Booking booking= bookingService.BookMovie(bookMovieRequestDto.getUserId(),
                bookMovieRequestDto.getShowId(),
                bookMovieRequestDto.getShowSeatIds());

                // convert the booking object to booking response dto, and return it

            bookMovieResponseDto.setBookingId(booking.getId());
            bookMovieResponseDto.setBookingReponse(ResponseStatus.SUCCESS);
            bookMovieResponseDto.setAmount(booking.getAmount());


        } catch (Exception e) {
            bookMovieResponseDto.setBookingReponse(ResponseStatus.FAILURE);
            // log the exception
            System.out.println("Exception occurred while booking the movie: " + e.getMessage());
            // return an error response or rethrow the exception as needed
        }
        return bookMovieResponseDto;
    }

}
