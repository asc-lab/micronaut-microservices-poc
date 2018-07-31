package pl.altkom.asc.lab.micronaut.poc.product.service.infrastructure;

import io.micronaut.http.annotation.Controller;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.product.service.api.v1.ProductDto;
import pl.altkom.asc.lab.micronaut.poc.product.service.api.v1.ProductOperations;
import pl.altkom.asc.lab.micronaut.poc.product.service.domain.Products;
import pl.altkom.asc.lab.micronaut.poc.product.service.init.DemoProductsFactory;

import java.util.List;

@Controller("/api/product")
@RequiredArgsConstructor
public class ProductsController implements ProductOperations {

    private final Products products;

    @Override
    public Single<List<ProductDto>> getAll() {
        return products.findAll().map(ProductsAssembler::map);
    }


    @Override
    public Single<ProductDto> addOne() {
        return products.add(DemoProductsFactory.house()).map(ProductsAssembler::map);
    }
}
