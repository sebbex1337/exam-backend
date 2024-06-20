package exam.backend.dto;

import exam.backend.entity.ResultatType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DisciplinDTO {
    private int id;
    private String navn;
    private ResultatType resultatType;
}
