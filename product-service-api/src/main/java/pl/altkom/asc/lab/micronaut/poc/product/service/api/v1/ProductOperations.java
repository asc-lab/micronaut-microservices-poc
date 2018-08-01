package pl.altkom.asc.lab.micronaut.poc.product.service.api.v1;

import io.micronaut.http.annotation.Get;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.util.List;

public interface ProductOperations {

    @Get("/")
    Single<List<ProductDto>> getAll();

    @Get("/{productCode}/")
    Maybe<ProductDto> get(String productCode);
}
