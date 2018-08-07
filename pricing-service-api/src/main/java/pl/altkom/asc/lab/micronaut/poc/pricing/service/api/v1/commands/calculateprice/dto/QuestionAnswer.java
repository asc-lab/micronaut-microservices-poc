package pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.commands.calculateprice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionAnswer<T> {
    private String questionCode;
    private T answer;
}
