package pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice.dto;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class ChoiceQuestionAnswer extends QuestionAnswer<String> {
    public ChoiceQuestionAnswer(String questionCode, String answer) {
        super(questionCode, answer);
    }
}
