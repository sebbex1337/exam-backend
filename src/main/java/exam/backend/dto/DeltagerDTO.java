package exam.backend.dto;

import exam.backend.entity.Køn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeltagerDTO {
    private String navn;
    private LocalDate fødselsdato;
    private Køn køn;
    private String email;
    private String klub;
    private String landkode;
    private List<Integer> disciplinIds;
    private List<Integer> resultatIds;
}
