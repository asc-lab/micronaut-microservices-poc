package asc.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.math.BigDecimal;


@Getter
@NoArgsConstructor
public class Cover {
    private String code;
    private String name;
    private String description;
    private boolean optional;
    private BigDecimal sumInsured;

    @BsonCreator
    public Cover(
            @BsonProperty("code") String code,
            @BsonProperty("name") String name,
            @BsonProperty("description") String description,
            @BsonProperty("optional") boolean optional,
            @BsonProperty("sumInsured") BigDecimal sumInsured) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.optional = optional;
        this.sumInsured = sumInsured;
    }
}
