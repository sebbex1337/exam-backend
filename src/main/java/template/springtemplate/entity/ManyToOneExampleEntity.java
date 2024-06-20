package template.springtemplate.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManyToOneExampleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    // En exampleEntity kan have MANGE ManyToOneExampleEntity
    @ManyToOne
    @JoinColumn(name = "example_entity_id")
    @JsonBackReference
    private ExampleEntity exampleEntity;
}
