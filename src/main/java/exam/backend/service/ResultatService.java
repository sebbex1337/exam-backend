package exam.backend.service;

import exam.backend.dto.ResultatDTO;
import exam.backend.dto.ResultatRegistreringDTO;
import exam.backend.entity.Resultat;
import exam.backend.repository.DeltagerRepository;
import exam.backend.repository.DisciplinRepository;
import exam.backend.repository.ResultatRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ResultatService {
    private final ResultatRepository resultatRepository;
    private final DeltagerRepository deltagerRepository;
    private final DisciplinRepository disciplinRepository;

    public ResultatService(ResultatRepository resultatRepository, DeltagerRepository deltagerRepository, DisciplinRepository disciplinRepository) {
        this.resultatRepository = resultatRepository;
        this.deltagerRepository = deltagerRepository;
        this.disciplinRepository = disciplinRepository;
    }

    public Resultat opretResultat(ResultatDTO resultat) {
        Resultat nyResultat = new Resultat();
        return getResultat(resultat, nyResultat);
    }

    // Bruges til at oprette flere resultater til en deltager ud fra et disciplinId
    public void registrerResultater(int disciplinId, ResultatRegistreringDTO resultat) {
        Resultat nyResultat = new Resultat();
        nyResultat.setDato(resultat.getDato());
        nyResultat.setDeltager(deltagerRepository.findById(resultat.getDeltagerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Deltager ikke fundet")));
        nyResultat.setDisciplin(disciplinRepository.findById(disciplinId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplin ikke fundet")));
        nyResultat.setDisciplinNavn(nyResultat.getDisciplin().getNavn());
        nyResultat.setResultat(resultat.getResultat());
        resultatRepository.save(nyResultat);
    }

    public Resultat opdaterResultat(int disciplinId, ResultatDTO resultat) {
        Resultat eksisterendeResultat = resultatRepository.findById(disciplinId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resultat ikke fundet"));
        return getResultat(resultat, eksisterendeResultat);
    }

    public void sletResultat(int resultatId) {
        if (!resultatRepository.existsById(resultatId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resultat ikke fundet");
        }
        resultatRepository.deleteById(resultatId);
    }

    public List<Resultat> hentResultater(int disciplinId) {
        return resultatRepository.findAllByDisciplinId(disciplinId);
    }

    // Bruger hjælpe funktion så jeg fjerne kode duplikation
    private Resultat getResultat(ResultatDTO resultat, Resultat eksisterendeResultat) {
        eksisterendeResultat.setDato(resultat.getDato());
        eksisterendeResultat.setDeltager(deltagerRepository.findById(resultat.getDeltagerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Deltager ikke fundet")));
        eksisterendeResultat.setDisciplin(disciplinRepository.findById(resultat.getDisciplinId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplin ikke fundet")));
        eksisterendeResultat.setDisciplinNavn(eksisterendeResultat.getDisciplin().getNavn());
        eksisterendeResultat.setResultat(resultat.getResultat());
        return resultatRepository.save(eksisterendeResultat);
    }

}
