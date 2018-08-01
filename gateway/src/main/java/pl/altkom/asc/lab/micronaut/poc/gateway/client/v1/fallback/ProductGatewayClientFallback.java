package pl.altkom.asc.lab.micronaut.poc.gateway.client.v1.fallback;

import io.micronaut.retry.annotation.Fallback;
import io.reactivex.Maybe;
import io.reactivex.Single;
import pl.altkom.asc.lab.micronaut.poc.gateway.client.v1.ProductGatewayClient;
import pl.altkom.asc.lab.micronaut.poc.product.service.api.v1.ProductDto;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;

@Singleton
@Fallback
public class ProductGatewayClientFallback implements ProductGatewayClient {

    @Override
    public Single<List<ProductDto>> getAll() {
        return Single.just(Collections.emptyList());
    }

    @Override
    public Maybe<ProductDto> get(String productCode) {
        return Maybe.empty();
    }
}
