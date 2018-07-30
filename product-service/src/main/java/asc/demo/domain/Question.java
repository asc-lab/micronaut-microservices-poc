package asc.demo.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChoiceQuestion.class, name = "choice"),
        @JsonSubTypes.Type(value = DateQuestion.class, name = "date"),
        @JsonSubTypes.Type(value = NumericQuestion.class, name = "numeric")
})
public class Question {
    private String code;
    private int index;
    private String text;
}
