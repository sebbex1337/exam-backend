package exam.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultatRegistreringDTO {
    private int deltagerId;
    private double resultat;
    private LocalDate dato;
    private int disciplinId;
}
