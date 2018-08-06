package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createoffer.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class NumericQuestionAnswer extends QuestionAnswer<BigDecimal> {
    @JsonCreator
    public NumericQuestionAnswer(@JsonProperty("questionCode") String questionCode, @JsonProperty("answer") BigDecimal answer) {
        super(questionCode, answer);
    }
}
