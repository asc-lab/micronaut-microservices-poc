package pl.altkom.asc.lab.micronaut.poc.product.service.api.v1.questions;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class ChoiceQuestionDto extends QuestionDto {
    private List<ChoiceDto> choices;

    public ChoiceQuestionDto(String code, int index, String text, List<ChoiceDto> choices) {
        super(code, index, text);
        this.choices = choices;
    }
}
