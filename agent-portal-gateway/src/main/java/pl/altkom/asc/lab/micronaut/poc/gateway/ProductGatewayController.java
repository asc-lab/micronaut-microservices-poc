package pl.altkom.asc.lab.micronaut.poc.gateway;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Maybe;
import io.reactivex.Single;
import pl.altkom.asc.lab.micronaut.poc.gateway.client.v1.ProductGatewayClient;
import pl.altkom.asc.lab.micronaut.poc.product.service.api.v1.ProductDto;

import javax.inject.Inject;
import java.util.List;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api/products")
public class ProductGatewayController {

    @Inject
    private ProductGatewayClient client;

    @Get
    public Single<List<ProductDto>> getAll() {
        return client.getAll();
    }

    @Get("/{productCode}")
    public Maybe<ProductDto> get(String productCode) {
        return client.get(productCode);
    }
}
