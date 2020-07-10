package pl.altkom.asc.lab.micronaut.poc.product.service.api.v1.questions;

import java.util.List;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Introspected
@NoArgsConstructor
@Getter
public class ChoiceQuestionDto extends QuestionDto {
    private List<ChoiceDto> choices;

    public ChoiceQuestionDto(String code, int index, String text, List<ChoiceDto> choices) {
        super(code, index, text);
        this.choices = choices;
    }
}
