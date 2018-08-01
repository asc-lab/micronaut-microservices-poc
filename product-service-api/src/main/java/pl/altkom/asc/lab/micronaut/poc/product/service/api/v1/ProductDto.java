package pl.altkom.asc.lab.micronaut.poc.product.service.api.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.product.service.api.v1.questions.QuestionDto;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String code;
    private String name;
    private String image;
    private String description;
    private List<CoverDto> covers;
    private List<QuestionDto> questions;
    private int maxNumberOfInsured;
}
