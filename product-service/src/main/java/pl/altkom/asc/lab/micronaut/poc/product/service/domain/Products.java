package pl.altkom.asc.lab.micronaut.poc.product.service.domain;

import io.reactivex.Single;

import java.util.List;

public interface Products {
    Single<Product> add(Product product);

    Single<List<Product>> findAll();
}
