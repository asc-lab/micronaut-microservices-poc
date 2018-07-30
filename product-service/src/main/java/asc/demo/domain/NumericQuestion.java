package asc.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@NoArgsConstructor
@Getter
public class NumericQuestion extends Question {
    @BsonCreator
    public NumericQuestion(@BsonProperty("code") String code,
                           @BsonProperty("index") int index,
                           @BsonProperty("text") String text) {
        super(code, index, text);
    }
}
