package pl.altkom.asc.lab.micronaut.poc.gateway;

import io.micronaut.http.annotation.Controller;
import io.reactivex.Single;
import pl.altkom.asc.lab.micronaut.poc.gateway.client.v1.ProductGatewayClient;
import pl.altkom.asc.lab.micronaut.poc.product.service.api.v1.ProductDto;
import pl.altkom.asc.lab.micronaut.poc.product.service.api.v1.ProductOperations;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
@Controller("/api/products")
public class ProductGatewayController implements ProductOperations {

    @Inject
    private ProductGatewayClient client;

    @Override
    public Single<List<ProductDto>> getAll() {
        return client.getAll();
    }

    @Override
    public Single<ProductDto> addOne() {
        return client.addOne();
    }
}
