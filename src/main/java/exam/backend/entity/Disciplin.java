package exam.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Disciplin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String navn;

    @Enumerated(EnumType.STRING)
    private ResultatType resultatType;

    @ManyToMany(mappedBy = "discipliner")
    @JsonBackReference
    private List<Deltager> deltagere;

    @OneToMany(mappedBy = "disciplin")
    @JsonBackReference
    private List<Resultat> resultater;

    public Disciplin(String navn, ResultatType resultatType) {
        this.navn = navn;
        this.resultatType = resultatType;
    }
}
