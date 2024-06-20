package template.springtemplate.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExampleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    /*
        Bidirectional OneToMany relation
        OneToMany relation skal have en Liste af ManyToOne entities
        mappedBy angiver hvilken property i ManyToOneExampleEntity der refererer til denne klasse
        i dette tilfælde er det "exampleEntity" fra ManyToOneExampleEntity

        JsonManagedReference bruges til at undgå uendelige loops i JSON
        JsonBackReference skal være på ManyToOne klassen
    */
    @OneToMany(mappedBy = "exampleEntity")
    @JsonManagedReference
    private List<ManyToOneExampleEntity> manyToOneExampleEntities = new ArrayList<>();
}
