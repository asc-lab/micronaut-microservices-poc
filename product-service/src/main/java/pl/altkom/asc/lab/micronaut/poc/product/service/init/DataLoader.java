package pl.altkom.asc.lab.micronaut.poc.product.service.init;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Singleton;
import java.util.List;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.product.service.domain.Product;
import pl.altkom.asc.lab.micronaut.poc.product.service.domain.Products;

@Singleton
@RequiredArgsConstructor
public class DataLoader implements ApplicationEventListener<ServerStartupEvent> {

    private final Products productsRepository;

    @Override
    public void onApplicationEvent(ServerStartupEvent serverStartupEvent) {
        List<Product> allProducts = productsRepository.findAll().block();

        if (allProducts.stream().noneMatch(p -> p.getCode().equals("CAR"))) {
            productsRepository.add(DemoProductsFactory.car()).block();
        }

        if (allProducts.stream().noneMatch(p -> p.getCode().equals("FAI"))) {
            productsRepository.add(DemoProductsFactory.farm()).block();
        }

        if (allProducts.stream().noneMatch(p -> p.getCode().equals("HSI"))) {
            productsRepository.add(DemoProductsFactory.house()).block();
        }

        if (allProducts.stream().noneMatch(p -> p.getCode().equals("TRI"))) {
            productsRepository.add(DemoProductsFactory.travel()).block();
        }
    }
}
