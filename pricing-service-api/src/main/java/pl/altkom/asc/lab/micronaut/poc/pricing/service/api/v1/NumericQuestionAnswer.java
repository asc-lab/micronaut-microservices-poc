package pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1;

import java.math.BigDecimal;

public class NumericQuestionAnswer extends QuestionAnswer<BigDecimal> {
    public NumericQuestionAnswer(String questionCode, BigDecimal answer) {
        super(questionCode, answer);
    }
}

