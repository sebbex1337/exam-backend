package exam.backend.service;


import exam.backend.dto.DeltagerDTO;
import exam.backend.entity.Deltager;
import exam.backend.entity.Resultat;
import exam.backend.repository.DeltagerRepository;
import exam.backend.repository.DisciplinRepository;
import exam.backend.repository.ResultatRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DeltagerService {
    private final DeltagerRepository deltagerRepository;
    private final DisciplinRepository disciplinRepository;

    private final ResultatRepository resultatRepository;

    public DeltagerService(DeltagerRepository deltagerRepository, DisciplinRepository disciplinRepository, ResultatRepository resultatRepository) {
        this.deltagerRepository = deltagerRepository;
        this.disciplinRepository = disciplinRepository;
        this.resultatRepository = resultatRepository;
    }

    public List<Deltager> hentDeltagere() {
        return deltagerRepository.findAll();
    }

    public Deltager hentDeltager(int id) {
        return deltagerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Deltager ikke fundet"));
    }

    public Deltager opretDeltager(DeltagerDTO deltagerDTO) {
        Deltager nyDeltager = new Deltager();
        Deltager d = konverterDeltagerDTOtilDeltager(nyDeltager, deltagerDTO);
        validerResultater(d, deltagerDTO.getResultatIds());
        return deltagerRepository.save(d);

    }

    public Deltager opdaterDeltager(int id, DeltagerDTO deltagerDTO) {
        Deltager gammelDeltager = hentDeltager(id);
        Deltager opdateretDeltager = konverterDeltagerDTOtilDeltager(gammelDeltager, deltagerDTO);
        validerResultater(opdateretDeltager, deltagerDTO.getResultatIds());
        return deltagerRepository.save(opdateretDeltager);
    }

    public void sletDeltager(int id) {
        if (!deltagerRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Deltageren med id " + id + " blev ikke fundet");
        }
        deltagerRepository.deleteById(id);
    }

    public List<Deltager> søgDeltagere(String navn) {
        return deltagerRepository.findByNavnContainingIgnoreCase(navn);
    }

    private Deltager konverterDeltagerDTOtilDeltager(Deltager deltager, DeltagerDTO deltagerDTO) {
        deltager.setNavn(deltagerDTO.getNavn());
        deltager.setFødselsdato(deltagerDTO.getFødselsdato());
        deltager.setKøn(deltagerDTO.getKøn());
        deltager.setEmail(deltagerDTO.getEmail());
        deltager.setKlub(deltagerDTO.getKlub());
        deltager.setLandKode(deltagerDTO.getLandKode());

        // Tjek om disciplinIds og resultatIds er null eller tomme
        // Hvis de er null eller tomme springer vi if statement over
        if (deltagerDTO.getDisciplinIds() != null && !deltagerDTO.getDisciplinIds().isEmpty()) {
            deltager.setDiscipliner(disciplinRepository.findAllById(deltagerDTO.getDisciplinIds()));
        }
        if (deltagerDTO.getResultatIds() != null && !deltagerDTO.getResultatIds().isEmpty()) {
            deltager.setResultater(resultatRepository.findAllById(deltagerDTO.getResultatIds()));
        }

        return deltager;
    }

    private void validerResultater(Deltager deltager, List<Integer> resultatIds) {
        if (resultatIds != null) {
            for (Integer resultatId : resultatIds) {
                /*
                 Tjek om resultatId er 0, hvis det er så springer vi over til næste iteration
                 Dette er for at undgå at vi får en exception hvis resultatId er 0
                 Da vi ikke kan finde et resultat med id 0 og så forbliver vores resultat liste bare tom
                */
                if (resultatId == 0) {
                    continue;
                }
                Resultat resultat = resultatRepository.findById(resultatId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resultat med id " + resultatId + " blev ikke fundet"));
                if (!resultat.getDeltager().equals(deltager)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Resultat med id " + resultatId + " tilhører ikke til deltageren");
                }
            }
        }
    }

}
