package pl.altkom.asc.lab.micronaut.poc.product.service.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Getter
public class ChoiceQuestion extends Question {
    private List<Choice> choices;

    @BsonCreator
    public ChoiceQuestion(
            @BsonProperty("code") String code,
            @BsonProperty("index") int index,
            @BsonProperty("text") String text,
            @BsonProperty("choices") List<Choice> choices) {
        super(code, index, text);
        this.choices = choices;
    }

    public static List<Choice> yesNoChoice(){
        return Arrays.asList(
                new Choice("YES", "Yes"),
                new Choice("NO", "No")
        );
    }
}
