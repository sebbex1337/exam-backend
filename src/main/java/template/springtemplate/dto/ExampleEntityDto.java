package template.springtemplate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import template.springtemplate.entity.ExampleEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExampleEntityDto {
    private int id;
    private String name;
    // Tilføj så mange felter som du vil have i din DTO

    // Constructor tager en original entity og laver en DTO ud af den
    public ExampleEntityDto(ExampleEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
