package pl.altkom.asc.lab.micronaut.poc.product.service.infrastructure;

import pl.altkom.asc.lab.micronaut.poc.product.service.init.DemoProductsFactory;
import pl.altkom.asc.lab.micronaut.poc.product.service.domain.Product;
import pl.altkom.asc.lab.micronaut.poc.product.service.domain.Products;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller("/api/products")
@RequiredArgsConstructor
public class ProductsController {

    private final Products products;

    @Get("/")
    public Single<List<Product>> getAll() {
        return products.findAll();
    }


    @Get("/add")
    public Single<Product> addOne() {
        return products.add(DemoProductsFactory.house());
    }
}
