package pl.altkom.asc.lab.micronaut.poc.product.service.api.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    //TODO private List<Question> questions;
    private int maxNumberOfInsured;
}
