package exam.backend.api;

import exam.backend.dto.DeltagerDTO;
import exam.backend.entity.Deltager;
import exam.backend.service.DeltagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deltagere")
public class DeltagerController {
    private final DeltagerService deltagerService;

    public DeltagerController(DeltagerService deltagerService) {
        this.deltagerService = deltagerService;
    }

    @GetMapping
    public List<Deltager> hentDeltagere() {
        return deltagerService.hentDeltagere();
    }

    @GetMapping("/{id}")
    public Deltager hentDeltager(@PathVariable int id) {
        return deltagerService.hentDeltager(id);
    }

    @GetMapping("/search/{navn}")
    public List<Deltager> søgDeltagere(@PathVariable String navn) {
        return deltagerService.søgDeltagere(navn);
    }

    @PostMapping
    public Deltager opretDeltager(@RequestBody DeltagerDTO deltagerDTO) {
        return deltagerService.opretDeltager(deltagerDTO);
    }

    @PutMapping("/{id}")
    public Deltager opdaterDeltager(@PathVariable int id, @RequestBody DeltagerDTO deltagerDTO) {
        return deltagerService.opdaterDeltager(id, deltagerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> sletDeltager(@PathVariable int id) {
        deltagerService.sletDeltager(id);
        return ResponseEntity.noContent().build();
    }
}
