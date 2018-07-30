package asc.demo.infrastructure;

import asc.demo.domain.DemoProductsFactory;
import asc.demo.domain.Product;
import asc.demo.domain.Products;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.reactivex.Flowable;
import io.reactivex.Single;

import javax.inject.Singleton;
import java.util.List;


@Singleton
public class ProductsRepository implements Products {
    private final MongoClient mongoClient;

    public ProductsRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public Single<Product> add(Product product) {
        return Single.fromPublisher(getCollection().insertOne(product)).map(success -> product);
    }

    @Override
    public Single<List<Product>> findAll() {
        return Flowable.fromPublisher(getCollection().find()).toList();
    }

    private MongoCollection<Product> getCollection() {
        return mongoClient
                .getDatabase("products-demo")
                .getCollection("product", Product.class);
    }
}
