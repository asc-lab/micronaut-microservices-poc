package pl.altkom.asc.lab.micronaut.poc.product.service.domain;

import java.util.List;
import reactor.core.publisher.Mono;

public interface Products {

    Mono<Product> add(Product product);

    Mono<List<Product>> findAll();

    Mono<Product> findOne(String productCode);
}
