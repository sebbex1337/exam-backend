package exam.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultatRegistreringDTO {
    private int deltagerId;
    private double resultat;
    private LocalDateTime dato;
    private int disciplinId;
}
