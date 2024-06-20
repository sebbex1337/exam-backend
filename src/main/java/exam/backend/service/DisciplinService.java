package exam.backend.service;

import exam.backend.dto.DisciplinDTO;
import exam.backend.entity.Disciplin;
import exam.backend.repository.DisciplinRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DisciplinService {
    private final DisciplinRepository disciplinRepository;

    public DisciplinService(DisciplinRepository disciplinRepository) {
        this.disciplinRepository = disciplinRepository;
    }

    public List<Disciplin> hentDiscipliner() {
        return disciplinRepository.findAll();
    }

    public Disciplin hentDisciplin(int id) {
        return disciplinRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplin ikke fundet"));
    }

    public Disciplin opretDisciplin(DisciplinDTO disciplinDTO) {
        Disciplin nyDisciplin = new Disciplin();
        nyDisciplin.setNavn(disciplinDTO.getNavn());
        nyDisciplin.setResultatType(disciplinDTO.getResultatType());

        return disciplinRepository.save(nyDisciplin);
    }

    public Disciplin opdaterDisciplin(int id, DisciplinDTO disciplin) {
        Disciplin eksisterendeDisciplin = hentDisciplin(id);
        eksisterendeDisciplin.setNavn(disciplin.getNavn());
        eksisterendeDisciplin.setResultatType(disciplin.getResultatType());
        return disciplinRepository.save(eksisterendeDisciplin);
    }

    public void sletDisciplin(int id) {
        if (!disciplinRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplin med id " + id + " blev ikke fundet");
        }
        disciplinRepository.deleteById(id);
    }
}
