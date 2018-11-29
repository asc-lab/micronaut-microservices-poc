package pl.altkom.asc.lab.micronaut.poc.cms.service.api.v1;

import io.micronaut.http.annotation.Get;
import io.reactivex.Maybe;

import java.util.List;

public interface ProductHeaderOperations {
    @Get
    Maybe<List<ProductHeader>> productHeaders();
}
