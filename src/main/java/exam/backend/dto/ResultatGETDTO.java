package exam.backend.dto;

import exam.backend.entity.Deltager;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ResultatGETDTO {
    private int id;
    private LocalDate dato;
    private double resultat;
    private Deltager deltager;
    private int disciplinId;

}
