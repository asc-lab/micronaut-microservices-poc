package pl.altkom.asc.lab.micronaut.poc.product.service.api.v1.questions;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChoiceQuestionDto.class, name = "choice"),
        @JsonSubTypes.Type(value = DateQuestionDto.class, name = "date"),
        @JsonSubTypes.Type(value = NumericQuestionDto.class, name = "numeric")
})
public class QuestionDto {
    private String code;
    private int index;
    private String text;

    public QuestionDto(String code, int index, String text) {
        this.code = code;
        this.index = index;
        this.text = text;
    }
}
