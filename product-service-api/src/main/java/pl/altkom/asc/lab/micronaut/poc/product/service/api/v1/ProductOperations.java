package pl.altkom.asc.lab.micronaut.poc.product.service.api.v1;

import io.micronaut.http.annotation.Get;
import java.util.List;
import reactor.core.publisher.Mono;

public interface ProductOperations {

    @Get
    Mono<List<ProductDto>> getAll();

    @Get("/{productCode}")
    Mono<ProductDto> get(String productCode);
}
