package pl.altkom.asc.lab.micronaut.poc.product.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Getter
@NoArgsConstructor
public class Choice {
    private String code;
    private String label;

    @BsonCreator
    public Choice(@BsonProperty("code") String code, @BsonProperty("label") String label) {
        this.code = code;
        this.label = label;
    }
}
