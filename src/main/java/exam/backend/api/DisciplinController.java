package exam.backend.api;

import exam.backend.dto.DisciplinDTO;
import exam.backend.entity.Disciplin;
import exam.backend.service.DisciplinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discipliner")
public class DisciplinController {
    private final DisciplinService disciplinService;

    public DisciplinController(DisciplinService disciplinService) {
        this.disciplinService = disciplinService;
    }

    @GetMapping
    public List<Disciplin> hentDiscipliner() {
        return disciplinService.hentDiscipliner();
    }

    @GetMapping("/{id}")
    public Disciplin hentDisciplin(@PathVariable int id) {
        return disciplinService.hentDisciplin(id);
    }

    @PostMapping
    public Disciplin opretDisciplin(@RequestBody DisciplinDTO disciplin) {
        return disciplinService.opretDisciplin(disciplin);
    }

    @PutMapping("/{id}")
    public Disciplin opdaterDisciplin(@PathVariable int id, @RequestBody DisciplinDTO disciplin) {
        return disciplinService.opdaterDisciplin(id, disciplin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> sletDisciplin(@PathVariable int id) {
        disciplinService.sletDisciplin(id);
        return ResponseEntity.ok("Disciplin slettet");
    }

}
