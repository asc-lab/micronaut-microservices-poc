package pl.altkom.asc.lab.micronaut.poc.product.service.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@BsonDiscriminator
public class Product {
    private String code;
    private String name;
    private String image;
    private String description;
    private List<Cover> covers;
    private List<Question> questions;
    private int maxNumberOfInsured;

    @BsonCreator
    public Product(
            @BsonProperty("code") String code,
            @BsonProperty("name") String name,
            @BsonProperty("image") String image,
            @BsonProperty("description") String description,
            @BsonProperty("covers") List<Cover> covers,
            @BsonProperty("questions") List<Question> questions,
            @BsonProperty("maxNumberOfInsured") int maxNumberOfInsured) {
        this.code = code;
        this.name = name;
        this.image = image;
        this.description = description;
        this.covers = covers;
        this.questions = questions;
        this.maxNumberOfInsured = maxNumberOfInsured;
    }

    public Product(String code, String name, String image, String description, int maxNumberOfInsured) {
        this.code = code;
        this.name = name;
        this.image = image;
        this.description = description;
        this.maxNumberOfInsured = maxNumberOfInsured;
        this.covers = new ArrayList<>();
        this.questions = new ArrayList<>();
    }

    public void addCover(String code, String name, String description, boolean isOptional, BigDecimal sumInsured) {
        covers.add(new Cover(code,name,description,isOptional, sumInsured));
    }

    public void addQuestions(List<Question> questions){
        if (this.questions==null) {
            this.questions = new ArrayList<>();
        }
        this.questions.addAll(questions);
    }
}
