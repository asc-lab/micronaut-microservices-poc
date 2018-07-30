package asc.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @BsonId
    private String id;
    private String code;
    private String name;
    private String image;
    private String description;
    private List<Cover> covers;
    private List<Question> questions;
    private int maxNumberOfInsured;

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

    public void setQuestions(List<Question> questions){
        this.questions.addAll(questions);
    }
}
