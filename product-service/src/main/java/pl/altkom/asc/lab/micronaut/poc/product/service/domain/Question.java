package pl.altkom.asc.lab.micronaut.poc.product.service.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@NoArgsConstructor
@Getter
@BsonDiscriminator
public class Question {
    private String code;
    private int index;
    private String text;

    public Question(String code, int index, String text) {
        this.code = code;
        this.index = index;
        this.text = text;
    }
}
