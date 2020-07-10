package pl.altkom.asc.lab.micronaut.poc.product.service.api.v1;

import pl.altkom.asc.lab.micronaut.poc.product.service.api.v1.questions.QuestionDto;

import java.util.List;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Introspected
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
    private String icon;
}
