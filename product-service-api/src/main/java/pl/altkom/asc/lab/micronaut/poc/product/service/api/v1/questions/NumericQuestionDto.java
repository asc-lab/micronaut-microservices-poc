package pl.altkom.asc.lab.micronaut.poc.product.service.api.v1.questions;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class NumericQuestionDto extends QuestionDto {
    public NumericQuestionDto(String code, int index, String text) {
        super(code, index, text);
    }
}
