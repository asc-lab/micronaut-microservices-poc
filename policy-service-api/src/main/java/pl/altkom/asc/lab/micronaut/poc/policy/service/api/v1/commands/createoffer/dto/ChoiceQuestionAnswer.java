package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createoffer.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class ChoiceQuestionAnswer extends QuestionAnswer<String> {
    @JsonCreator
    public ChoiceQuestionAnswer(@JsonProperty("questionCode") String questionCode, @JsonProperty("answer") String answer) {
        super(questionCode, answer);
    }
}
