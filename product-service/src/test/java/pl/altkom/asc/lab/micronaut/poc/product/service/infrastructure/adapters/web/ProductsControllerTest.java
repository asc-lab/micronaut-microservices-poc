package pl.altkom.asc.lab.micronaut.poc.product.service.infrastructure.adapters.web;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.junit.jupiter.api.Test;
import pl.altkom.asc.lab.micronaut.poc.product.service.api.v1.ProductDto;
import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
public class ProductsControllerTest {
  
  @Test
  void canGetAllProducts(RequestSpecification spec) {
    var products = spec
          .when()
            .get("/products")
            .as(new TypeRef<List<ProductDto>>() {});
    
    assertThat(products)
        .isNotNull()
        .hasSize(4);
  }
  
  @Test
  void canGetProductWithCode(RequestSpecification spec) {
    var product = spec
        .when()
        .get("/products/CAR")
        .as(new TypeRef<ProductDto>() {});
    
    assertThat(product)
        .isNotNull();
    assertThat(product.getCode()).isEqualTo("CAR");
    assertThat(product.getName()).isEqualTo("Happy Driver");
    assertThat(product.getDescription()).isEqualTo("Car insurance");
    assertThat(product.getImage()).isEqualTo("/static/car.jpg");
    assertThat(product.getIcon()).isEqualTo("car");
    assertThat(product.getMaxNumberOfInsured()).isEqualTo(1);
    assertThat(product.getCovers()).hasSize(1);
    assertThat(product.getCovers().get(0).getCode()).isEqualTo("C1");
    assertThat(product.getCovers().get(0).getName()).isEqualTo("Assistance");
    assertThat(product.getCovers().get(0).isOptional()).isTrue();
    assertThat(product.getQuestions()).hasSize(1);
    assertThat(product.getQuestions().get(0).getCode()).isEqualTo("NUM_OF_CLAIM");
    assertThat(product.getQuestions().get(0).getText()).isEqualTo("Number of claims in last 5 years");
    assertThat(product.getQuestions().get(0).getIndex()).isEqualTo(3);
  }
}
