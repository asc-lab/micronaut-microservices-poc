package pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice.dto;

public class TextQuestionAnswer extends QuestionAnswer<String> {
    public TextQuestionAnswer(String questionCode, String answer) {
        super(questionCode, answer);
    }
}

