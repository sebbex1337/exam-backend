package exam.backend.repository;

import exam.backend.entity.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultatRepository extends JpaRepository<Resultat, Integer> {
    List<Resultat> findByDeltagerId(int deltagerId);

    List<Resultat> findAllByDisciplinId(int disciplinId);
}
