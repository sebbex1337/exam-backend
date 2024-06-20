package exam.backend.api;

import exam.backend.dto.ResultatDTO;
import exam.backend.dto.ResultatRegistreringDTO;
import exam.backend.entity.Resultat;
import exam.backend.service.ResultatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resultater")
public class ResultatController {
    private final ResultatService resultatService;

    public ResultatController(ResultatService resultatService) {
        this.resultatService = resultatService;
    }

    @GetMapping("/disciplin/{disciplinId}/deltagere")
    public List<Resultat> hentResultaterMedDeltagere(@PathVariable int disciplinId) {
        return resultatService.hentResultater(disciplinId);
    }

    @PostMapping
    public Resultat opretResultat(@RequestBody ResultatDTO resultat) {
        return resultatService.opretResultat(resultat);
    }

    @PostMapping("/disciplin/{disciplinId}")
    public ResponseEntity<String> registrerResultater(@PathVariable int disciplinId, @RequestBody List<ResultatRegistreringDTO> resultater) {
        for (ResultatRegistreringDTO resultat : resultater) {
            resultatService.registrerResultater(disciplinId, resultat);
        }
        return ResponseEntity.ok("Resultater registreret");
    }

    @PutMapping("/{id}")
    public Resultat opdaterResultat(@PathVariable int id, @RequestBody ResultatDTO resultat) {
        return resultatService.opdaterResultat(id, resultat);
    }

    @DeleteMapping("/{resultatId}")
    public ResponseEntity<String> sletResultat(@PathVariable int resultatId) {
        resultatService.sletResultat(resultatId);
        return ResponseEntity.ok("Resultat slettet");
    }
}
