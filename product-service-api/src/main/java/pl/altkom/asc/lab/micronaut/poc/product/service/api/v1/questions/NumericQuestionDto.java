package pl.altkom.asc.lab.micronaut.poc.product.service.api.v1.questions;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Introspected
@NoArgsConstructor
@Getter
public class NumericQuestionDto extends QuestionDto {
    public NumericQuestionDto(String code, int index, String text) {
        super(code, index, text);
    }
}
