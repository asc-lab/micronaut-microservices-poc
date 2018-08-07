package pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice.dto;

import java.math.BigDecimal;

public class NumericQuestionAnswer extends QuestionAnswer<BigDecimal> {
    public NumericQuestionAnswer(String questionCode, BigDecimal answer) {
        super(questionCode, answer);
    }
}

