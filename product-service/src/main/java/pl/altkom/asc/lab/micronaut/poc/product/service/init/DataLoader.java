package pl.altkom.asc.lab.micronaut.poc.product.service.init;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.product.service.infrastructure.ProductsRepository;

import javax.inject.Singleton;

@Singleton
@RequiredArgsConstructor
public class DataLoader implements ApplicationEventListener<ServerStartupEvent> {

    private final ProductsRepository productsRepository;

    @Override
    public void onApplicationEvent(ServerStartupEvent serverStartupEvent) {
            productsRepository.add(DemoProductsFactory.car());
            productsRepository.add(DemoProductsFactory.farm());
            productsRepository.add(DemoProductsFactory.house());
            productsRepository.add(DemoProductsFactory.travel());
    }
}
