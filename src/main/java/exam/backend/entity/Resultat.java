package exam.backend.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resultat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime dato;

    @ManyToOne
    @JsonBackReference
    private Disciplin disciplin;

    // For at kunne vise disciplinNavn i JSON
    private String disciplinNavn;

    @ManyToOne
    @JsonBackReference
    private Deltager deltager;

    private double resultat;

    public Resultat(LocalDateTime dato, Disciplin disciplin, Deltager deltager, double resultat) {
        this.dato = dato;
        this.disciplin = disciplin;
        this.disciplinNavn = disciplin.getNavn();
        this.deltager = deltager;
        this.resultat = resultat;
    }
}
