package template.springtemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import template.springtemplate.entity.ExampleEntity;

// JpaRepository er en interface der giver os CRUD funktionalitet
public interface ExampleEntityRepository extends JpaRepository<ExampleEntity, Integer>{
}
