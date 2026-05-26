package Models;

import jdk.dynalink.linker.LinkerServices;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
public class Theatre extends BaseModel{
    private String name;
    private Region region;
    private List<Screen> screens;


}
