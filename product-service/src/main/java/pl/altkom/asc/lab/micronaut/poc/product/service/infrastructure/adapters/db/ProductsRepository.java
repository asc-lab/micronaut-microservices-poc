package pl.altkom.asc.lab.micronaut.poc.product.service.infrastructure.adapters.db;

import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import jakarta.inject.Singleton;
import java.util.List;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.product.service.domain.Product;
import pl.altkom.asc.lab.micronaut.poc.product.service.domain.Products;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Singleton
@RequiredArgsConstructor
public class ProductsRepository implements Products {

    private final MongoClient mongoClient;

    @Override
    public Mono<Product> add(Product product) {
        return Mono.from(
                getCollection().insertOne(product)
        ).map(success -> product);
    }

    @Override
    public Mono<List<Product>> findAll() {
        return Flux.from(
                getCollection().find()
        ).collectList();
    }

    @Override
    public Mono<Product> findOne(String productCode) {
        return Mono.from(
                getCollection()
                        .find(Filters.eq("code", productCode))
                        .limit(1)
        );
    }

    private MongoCollection<Product> getCollection() {
        return mongoClient
                .getDatabase("products-demo")
                .getCollection("product", Product.class);
    }
}
