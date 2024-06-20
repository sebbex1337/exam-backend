package exam.backend.repository;

import exam.backend.entity.Deltager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeltagerRepository extends JpaRepository<Deltager, Integer> {
    List<Deltager> findByNavnContainingIgnoreCase(String navn);
}
