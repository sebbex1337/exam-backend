package exam.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Deltager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String navn;
    private LocalDate fødselsdato;
    private String aldersgruppe;
    @Enumerated(EnumType.STRING)
    private Køn køn;
    private String email;
    // Måske skal der laves en klub klasse??
    private String klub;
    private String landKode;

    @ManyToMany
    @JsonManagedReference
    private List<Disciplin> discipliner = new ArrayList<>();

    @OneToMany(mappedBy = "deltager", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference
    private List<Resultat> resultater = new ArrayList<>();

    // Constructor uden id og resultater så der kan oprettes data til databasen gennem CommandLineRunner
    public Deltager(String navn, LocalDate fødselsdato, Køn køn, String email, String klub, String landKode, List<Disciplin> discipliner) {
        this.navn = navn;
        this.fødselsdato = fødselsdato;
        this.køn = køn;
        this.email = email;
        this.klub = klub;
        this.landKode = landKode;
        this.discipliner = discipliner;
    }

    public void setFødselsdato(LocalDate fødselsdato) {
        this.fødselsdato = fødselsdato;
        this.aldersgruppe = beregnAldersgruppe(fødselsdato);
    }

    private String beregnAldersgruppe(LocalDate fødselsdato) {
        int alder = Period.between(fødselsdato, LocalDate.now()).getYears();

        if (alder >= 6 && alder <= 9) {
            return "Børn";
        } else if (alder >= 10 && alder <= 13) {
            return "Unge";
        } else if (alder >= 14 && alder <= 22) {
            return "Junior";
        } else if (alder >= 23 && alder <= 40) {
            return "Voksne";
        } else if (alder >= 41) {
            return "Senior";
        } else {
            return "Ugyldig alder";
        }
    }
}
