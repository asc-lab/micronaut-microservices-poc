package asc.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class NumericQuestion extends Question {
    public NumericQuestion(String code, int index, String text) {
        super(code, index, text);
    }
}
