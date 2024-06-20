package exam.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ResultatDTO {
    private int id;
    private LocalDate dato;
    private int deltagerId;
    private int disciplinId;
    private double resultat;

}
