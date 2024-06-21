package exam.backend.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import exam.backend.dto.DeltagerDTO;
import exam.backend.entity.Deltager;
import exam.backend.entity.Køn;
import exam.backend.repository.DisciplinRepository;
import exam.backend.repository.ResultatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import exam.backend.repository.DeltagerRepository;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class DeltagerServiceTest {
    
    private DeltagerService deltagerService;

    @MockBean
    private DeltagerRepository deltagerRepository;

    @MockBean
    private DisciplinRepository disciplinRepository;

    @MockBean
    private ResultatRepository resultatRepository;

    @BeforeEach
    void setUp() {
        deltagerService = new DeltagerService(deltagerRepository, disciplinRepository, resultatRepository);
    }

    @Test
    void testHentDeltager() {
        Deltager deltager = new Deltager();
        deltager.setNavn("Alice Wonderland");
        when(deltagerRepository.findById(1)).thenReturn(Optional.of(deltager));

        Deltager result = deltagerService.hentDeltager(1);

        assertEquals("Alice Wonderland", result.getNavn());

        verify(deltagerRepository).findById(1);
    }

    @Test
    void testSletDeltager() {
        when(deltagerRepository.existsById(1)).thenReturn(true);

        deltagerService.sletDeltager(1);

        verify(deltagerRepository).deleteById(1);
    }

    @Test
    void testSøgDeltagere() {
        Deltager deltager1 = new Deltager();
        deltager1.setNavn("Alice Wonderland");
        when(deltagerRepository.findByNavnContainingIgnoreCase("Alice")).thenReturn(List.of(deltager1));

        List<Deltager> result = deltagerService.søgDeltagere("Alice");

        assertEquals(1, result.size());
        assertEquals("Alice Wonderland", result.get(0).getNavn());

        verify(deltagerRepository).findByNavnContainingIgnoreCase("Alice");
    }

    @Test
    void testOpretDeltager() {
        DeltagerDTO deltagerDTO = new DeltagerDTO();
        deltagerDTO.setNavn("Alice Wonderland");
        deltagerDTO.setKøn(Køn.KVINDE);
        deltagerDTO.setKlub("Klubben");
        deltagerDTO.setEmail("test@test.dk");
        deltagerDTO.setFødselsdato(LocalDate.of(1990, 1, 1));
        deltagerDTO.setLandKode("DK");

        Deltager deltager = new Deltager();
        deltager.setNavn(deltagerDTO.getNavn());
        deltager.setFødselsdato(deltagerDTO.getFødselsdato());
        deltager.setKøn(deltagerDTO.getKøn());
        deltager.setEmail(deltagerDTO.getEmail());
        deltager.setKlub(deltagerDTO.getKlub());
        deltager.setLandKode(deltagerDTO.getLandKode());

        /*
            Bruger ArugmentCaptor for at have adgang
            til den korrekte instans af Deltager selvom
            jeg ikke har direkte adgang til den
        */
        ArgumentCaptor<Deltager> captor = ArgumentCaptor.forClass(Deltager.class);
        when(deltagerRepository.save(captor.capture())).thenAnswer(invocation -> {
            Deltager d = invocation.getArgument(0);
            d.setId(1);
            return d;
        });

        Deltager faktiskDeltager = deltagerService.opretDeltager(deltagerDTO);

        assertEquals(deltager.getNavn(), faktiskDeltager.getNavn());
        assertEquals(deltager.getFødselsdato(), faktiskDeltager.getFødselsdato());
        assertEquals(deltager.getKøn(), faktiskDeltager.getKøn());
        assertEquals(deltager.getEmail(), faktiskDeltager.getEmail());
        assertEquals(deltager.getKlub(), faktiskDeltager.getKlub());
        assertEquals(deltager.getLandKode(), faktiskDeltager.getLandKode());

        verify(deltagerRepository).save(captor.getValue());
    }
}