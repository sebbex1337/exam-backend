package template.springtemplate.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import template.springtemplate.entity.ExampleEntity;
import template.springtemplate.repository.ExampleEntityRepository;

import java.util.List;

@Service
public class ExampleEntityService {

    // Vi laver vores repository final da det skal være det samme gennem hele klassen
    private final ExampleEntityRepository exampleEntityRepository;

    // Vi bruger constructor injection til at injecte vores repository så vi kan bruge den i klassen
    public ExampleEntityService(ExampleEntityRepository exampleEntityRepository) {
        this.exampleEntityRepository = exampleEntityRepository;
    }

    /*
        Her kan du lave metoder der bruger repository til at hente data fra databasen
        Du kan også lave metoder der gemmer data i databasen
        Du kan også lave metoder der sletter data fra databasen
        Du kan også lave metoder der opdaterer data i databasen
     */

    // Her er et eksempel på en metode der henter alle entities fra databasen
    public List<ExampleEntity> getAllExampleEntities() {
        return exampleEntityRepository.findAll();
    }

    // Her er et eksempel på en metode der henter en entity fra databasen ud fra dens id
    public ExampleEntity getExampleEntityById(int id) {
        return exampleEntityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ExampleEntity not found"));
        // Vi bruger ResponseStatusException til at sende en 404 status code hvis entityen ikke findes
    }

    // Her er et eksempel på en metode der gemmer en entity i databasen
    public ExampleEntity saveExampleEntity(ExampleEntity exampleEntity) {
        return exampleEntityRepository.save(exampleEntity);
    }

    // Her er et eksempel på en metode der sletter en entity fra databasen
    public void deleteExampleEntity(int id) {
        exampleEntityRepository.deleteById(id);
    }

    // Her er et et eksempel på en metode der opdaterer en entity i databasen
    public ExampleEntity updateExampleEntity(int id, ExampleEntity exampleEntity) {
        ExampleEntity entity = exampleEntityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ExampleEntity not found"));
        entity.setName(exampleEntity.getName());
        return exampleEntityRepository.save(entity);
    }

}
