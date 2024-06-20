package template.springtemplate.api;

import org.springframework.web.bind.annotation.*;
import template.springtemplate.entity.ExampleEntity;
import template.springtemplate.service.ExampleEntityService;

import java.util.List;

@RestController
@RequestMapping("/api/example-entity")
public class ExampleEntityController {
    private final ExampleEntityService exampleEntityService;

    public ExampleEntityController(ExampleEntityService exampleEntityService) {
        this.exampleEntityService = exampleEntityService;
    }

    /*
        Her kan du lave metoder der bruger service til at hente data fra databasen
        Du kan også lave metoder der gemmer data i databasen
        Du kan også lave metoder der sletter data fra databasen
        Du kan også lave metoder der opdaterer data i databasen
    */

    // Her er et eksempel på en metode der henter alle entities fra databasen
    @GetMapping
    public List<ExampleEntity> getAllExampleEntities() {
        return exampleEntityService.getAllExampleEntities();
    }

    // Her er et eksempel på en metode der henter en entity fra databasen ud fra dens id
    @GetMapping("/{id}")
    public ExampleEntity getExampleEntityById(@PathVariable int id) {
        return exampleEntityService.getExampleEntityById(id);
    }

    /*
        Her er et eksempel på en metode der gemmer en entity i databasen
        Denne metode tager en entity som request body og returnerer den gemte entity
        Request body'en skal være en JSON object der matcher entity klassen
        I dette tilfælde skal det være et JSON object med et id og et name felt
    */
    @PostMapping
    public ExampleEntity saveExampleEntity(@RequestBody ExampleEntity exampleEntity) {
        return exampleEntityService.saveExampleEntity(exampleEntity);
    }

    /*
        Her er et eksempel på en metode der sletter en entity fra databasen
        Denne metode tager et id som path variable og sletter entityen med det id
     */
    @DeleteMapping("/{id}")
    public void deleteExampleEntity(@PathVariable int id) {
        exampleEntityService.deleteExampleEntity(id);
    }

    /*
        Her er et et eksempel på en metode der opdaterer en entity i databasen
        Denne metode tager et id som path variable og en entity som request body
        Request body'en skal være en JSON object der matcher entity klassen
        I dette tilfælde skal det være et JSON object med et id og et name felt
    */
    @PutMapping("/{id}")
    public ExampleEntity updateExampleEntity(@PathVariable int id, @RequestBody ExampleEntity exampleEntity) {
        return exampleEntityService.updateExampleEntity(id, exampleEntity);
    }

}
