package pl.altkom.asc.lab.micronaut.poc.product.service.infrastructure.adapters.web;

import io.micronaut.http.annotation.Controller;
import java.util.List;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.product.service.api.v1.ProductDto;
import pl.altkom.asc.lab.micronaut.poc.product.service.api.v1.ProductOperations;
import pl.altkom.asc.lab.micronaut.poc.product.service.domain.Products;
import reactor.core.publisher.Mono;

@Controller("/products")
@RequiredArgsConstructor
public class ProductsController implements ProductOperations {

    private final Products products;

    @Override
    public Mono<List<ProductDto>> getAll() {
        return products.findAll().map(ProductsAssembler::map);
    }

    @Override
    public Mono<ProductDto> get(String productCode) {
        return products.findOne(productCode).map(ProductsAssembler::map);
    }
}
