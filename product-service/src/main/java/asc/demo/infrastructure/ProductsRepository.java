package asc.demo.infrastructure;

import asc.demo.domain.Product;
import asc.demo.domain.Products;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.reactivex.Flowable;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import org.bson.codecs.configuration.CodecProvider;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ProductsRepository implements Products {
    private final MongoClient mongoClient;

    public ProductsRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public Single<Product> save(Product product) {
        return Single.fromPublisher(getCollection().insertOne(product)).map(success -> product);
    }

    @Override
    public Single<List<Product>> findAll() {
        return Flowable.fromPublisher(getCollection().find()).toList();
        //return Arrays.asList(DemoProductsFactory.travel(), DemoProductsFactory.house());
    }

    private MongoCollection<Product> getCollection() {
        return mongoClient
                .getDatabase("products")
                .getCollection("product", Product.class);
    }
}
