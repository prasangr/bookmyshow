package Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.EmbeddedColumnNaming;

import java.util.List;

@Getter
@Setter
@Entity
public class Region extends BaseModel {

private String name;
/*@OneToMany
private List<Theatre> theatres;*/
}
