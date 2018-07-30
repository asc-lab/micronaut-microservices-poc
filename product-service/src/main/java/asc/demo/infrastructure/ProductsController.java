package asc.demo.infrastructure;

import asc.demo.domain.Product;
import asc.demo.domain.Products;
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
}
