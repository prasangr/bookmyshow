package Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jdk.dynalink.linker.LinkerServices;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel{
    private String name;

    @ManyToOne
    private Region region;
    @OneToMany
    private List<Screen> screens;

    /*

    1    -->  1
    theatre  Region
     M    <--   1

    1  -->    M
    Theatre  Screen
       1   <--   1
      */


}
