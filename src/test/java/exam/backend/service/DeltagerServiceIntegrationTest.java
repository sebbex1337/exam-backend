package exam.backend.service;

import exam.backend.dto.DeltagerDTO;
import exam.backend.entity.Deltager;
import exam.backend.entity.Køn;
import exam.backend.repository.DeltagerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class DeltagerServiceIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DeltagerRepository deltagerRepository;

    @Test
    void testHentDeltager() {
        // Arrange
        Deltager deltager = new Deltager();
        deltager.setNavn("Alice Wonderland");
        entityManager.persistAndFlush(deltager);
        // Act
        DeltagerService deltagerService = new DeltagerService(deltagerRepository, null, null);

        Deltager hentetDeltager = deltagerService.hentDeltager(deltager.getId());

        // Assert
        assertThat(hentetDeltager).isEqualTo(deltager);
    }

    @Test
    void testSøgDeltager() {
        // Arrange
        Deltager deltager = new Deltager();
        deltager.setNavn("Alice Wonderland");
        entityManager.persistAndFlush(deltager);

        // Act
        DeltagerService deltagerService = new DeltagerService(deltagerRepository, null, null);

        List<Deltager> hentetDeltager = deltagerService.søgDeltagere("Alice");

        // Assert
        assertThat(hentetDeltager).contains(deltager);
    }

    @Test
    void testOpretDeltager() {
        // Arrange
        DeltagerDTO deltagerDTO = new DeltagerDTO();
        deltagerDTO.setNavn("Alice Wonderland");
        deltagerDTO.setKøn(Køn.KVINDE);
        deltagerDTO.setKlub("Klubben");
        deltagerDTO.setEmail("test@test.dk");
        deltagerDTO.setFødselsdato(LocalDate.of(1990, 1, 1));
        deltagerDTO.setLandKode("DK");

        // Act
        DeltagerService deltagerService = new DeltagerService(deltagerRepository, null, null);

        Deltager oprettetDeltager = deltagerService.opretDeltager(deltagerDTO);

        // Assert
        assertThat(oprettetDeltager.getNavn()).isEqualTo(deltagerDTO.getNavn());
        assertThat(oprettetDeltager.getFødselsdato()).isEqualTo(deltagerDTO.getFødselsdato());
        assertThat(oprettetDeltager.getKøn()).isEqualTo(deltagerDTO.getKøn());
        assertThat(oprettetDeltager.getEmail()).isEqualTo(deltagerDTO.getEmail());
        assertThat(oprettetDeltager.getKlub()).isEqualTo(deltagerDTO.getKlub());
        assertThat(oprettetDeltager.getLandKode()).isEqualTo(deltagerDTO.getLandKode());
    }

    @Test
    void testOpdaterDeltager() {
        // Arrange
        Deltager deltager = new Deltager();
        deltager.setNavn("Alice Wonderland");
        deltager.setKøn(Køn.KVINDE);
        deltager.setKlub("Klubben");
        deltager.setEmail("test@test.dk");
        deltager.setFødselsdato(LocalDate.of(1990, 1, 1));
        deltager.setLandKode("DK");
        entityManager.persistAndFlush(deltager);

        DeltagerDTO deltagerDTO = new DeltagerDTO();
        deltagerDTO.setNavn("Alice Wonderland2");
        deltagerDTO.setKøn(Køn.MAND);
        deltagerDTO.setKlub("Ny klub");
        deltagerDTO.setEmail("ny@email.dk");
        deltagerDTO.setFødselsdato(LocalDate.of(2024, 1, 1));
        deltagerDTO.setLandKode("KR");

        // Act
        DeltagerService deltagerService = new DeltagerService(deltagerRepository, null, null);

        Deltager opdateretDeltager = deltagerService.opdaterDeltager(deltager.getId(), deltagerDTO);

        // Assert
        assertThat(opdateretDeltager.getNavn()).isEqualTo(deltagerDTO.getNavn());
        assertThat(opdateretDeltager.getFødselsdato()).isEqualTo(deltagerDTO.getFødselsdato());
        assertThat(opdateretDeltager.getKøn()).isEqualTo(deltagerDTO.getKøn());
        assertThat(opdateretDeltager.getEmail()).isEqualTo(deltagerDTO.getEmail());
        assertThat(opdateretDeltager.getKlub()).isEqualTo(deltagerDTO.getKlub());
        assertThat(opdateretDeltager.getLandKode()).isEqualTo(deltagerDTO.getLandKode());
    }

    @Test
    void testSletDeltager() {
        // Arrange
        Deltager deltager = new Deltager();
        entityManager.persistAndFlush(deltager);

        // Act
        DeltagerService deltagerService = new DeltagerService(deltagerRepository, null, null);

        deltagerService.sletDeltager(deltager.getId());

        // Assert
        assertThat(deltagerRepository.findById(deltager.getId())).isEmpty();
    }
}
