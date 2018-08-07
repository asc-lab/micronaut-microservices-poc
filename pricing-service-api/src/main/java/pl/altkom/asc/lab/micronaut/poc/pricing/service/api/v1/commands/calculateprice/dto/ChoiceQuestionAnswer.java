package pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice.dto;

public class ChoiceQuestionAnswer extends QuestionAnswer<String> {
    public ChoiceQuestionAnswer(String questionCode, String answer) {
        super(questionCode, answer);
    }
}
