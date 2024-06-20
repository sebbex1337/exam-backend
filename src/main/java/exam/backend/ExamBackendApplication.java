package exam.backend;

import exam.backend.entity.*;
import exam.backend.repository.DeltagerRepository;
import exam.backend.repository.DisciplinRepository;
import exam.backend.repository.ResultatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class ExamBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner importData(DeltagerRepository deltagerRepository, DisciplinRepository disciplinRepository, ResultatRepository resultatRepository) {
        return (args) -> {
            System.out.println("Importing data...");

            // Opret discipliner
            Disciplin d1 = new Disciplin("100m løb", ResultatType.TID);
            Disciplin d2 = new Disciplin("Diskoskast", ResultatType.AFSTAND);
            Disciplin d3 = new Disciplin("Trespring", ResultatType.AFSTAND);
            Disciplin d4 = new Disciplin("Højdespring", ResultatType.AFSTAND);
            Disciplin d5 = new Disciplin("Spydkast", ResultatType.AFSTAND);
            Disciplin d6 = new Disciplin("Stangspring", ResultatType.AFSTAND);

            disciplinRepository.saveAll(Arrays.asList(d1, d2, d3, d4, d5, d6));

            // Opret deltagere
            Deltager dt1 = new Deltager("John Doe", LocalDate.of(2010, 1, 1), Køn.MAND, "john@example.com", "Klub A", "DK", Arrays.asList(d1, d2));
            Deltager dt2 = new Deltager("Jane Doe", LocalDate.of(2015, 1, 1), Køn.KVINDE, "jane@example.com", "Klub B", "US", Arrays.asList(d3, d4));
            Deltager dt3 = new Deltager("Jim Doe", LocalDate.of(1980, 1, 1), Køn.MAND, "jim@example.com", "Klub C", "DE",Arrays.asList(d5, d1));
            Deltager dt4 = new Deltager("Armand Duplantis", LocalDate.of(1999, 11, 10), Køn.MAND, "duplantis@easteregg.com", "World Champion", "SE", List.of(d6));

            /*
             Skal bruge setFødselsdato for at beregne aldersgruppe
             Lidt dumt but oh well :)
            */
            dt1.setFødselsdato(dt1.getFødselsdato());
            dt2.setFødselsdato(dt2.getFødselsdato());
            dt3.setFødselsdato(dt3.getFødselsdato());
            dt4.setFødselsdato(dt4.getFødselsdato());

            // Og så gemmer vi dem
            deltagerRepository.saveAll(Arrays.asList(dt1, dt2, dt3, dt4));

            // Opret resultater
            DecimalFormat df = new DecimalFormat("0.0000");
            Random rand = new Random();
            for (Deltager deltager : Arrays.asList(dt1, dt2, dt3)) {
                for (Disciplin disciplin : deltager.getDiscipliner()) {
                    double resultat = disciplin.getResultatType() == ResultatType.TID ? rand.nextDouble() * 10 : rand.nextDouble() * 100;
                    double formattedResultat = Double.parseDouble(df.format(resultat).replace(",", "."));
                    Resultat r = new Resultat(LocalDate.now(), disciplin, deltager, formattedResultat);
                    resultatRepository.save(r);
                }
            }


            // Opret resultater for Armand Duplantis
            Resultat r1 = new Resultat(LocalDate.now(), d6, dt4, 6.24);
            resultatRepository.save(r1);

            System.out.println("Data imported.");
        };
    }

}
