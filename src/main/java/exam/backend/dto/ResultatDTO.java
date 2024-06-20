package exam.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResultatDTO {
    private int id;
    private LocalDateTime dato;
    private int deltagerId;
    private int disciplinId;
    private double resultat;

}
