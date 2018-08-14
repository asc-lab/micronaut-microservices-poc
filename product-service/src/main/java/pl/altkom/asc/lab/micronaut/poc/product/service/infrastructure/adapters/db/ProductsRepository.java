package pl.altkom.asc.lab.micronaut.poc.product.service.infrastructure.adapters.db;

import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.product.service.domain.Product;
import pl.altkom.asc.lab.micronaut.poc.product.service.domain.Products;

import javax.inject.Singleton;
import java.util.List;


@Singleton
@RequiredArgsConstructor
public class ProductsRepository implements Products {

    private final MongoClient mongoClient;

    @Override
    public Single<Product> add(Product product) {
        return Single.fromPublisher(
                getCollection().insertOne(product)
        ).map(success -> product);
    }

    @Override
    public Single<List<Product>> findAll() {
        return Flowable.fromPublisher(
                getCollection().find()
        ).toList();
    }

    @Override
    public Maybe<Product> findOne(String productCode) {
        return Flowable.fromPublisher(
                getCollection()
                        .find(Filters.eq("code", productCode))
                        .limit(1)
        ).firstElement();
    }

    private MongoCollection<Product> getCollection() {
        return mongoClient
                .getDatabase("products-demo")
                .getCollection("product", Product.class);
    }
}
