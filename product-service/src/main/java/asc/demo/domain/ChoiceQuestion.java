package asc.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Getter
public class ChoiceQuestion extends Question {
    private List<Choice> choices;

    public ChoiceQuestion(String code, int index, String text, List<Choice> choices) {
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
