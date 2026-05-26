package Models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Screen extends BaseModel{
    private String screenNumber;
    private List<Seat> seats;
    private List<Feature> features;
}
