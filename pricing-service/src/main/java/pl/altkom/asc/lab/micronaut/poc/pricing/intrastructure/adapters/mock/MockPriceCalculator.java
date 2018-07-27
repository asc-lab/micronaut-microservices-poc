package pl.altkom.asc.lab.micronaut.poc.pricing.intrastructure.adapters.mock;

import pl.altkom.asc.lab.micronaut.poc.pricing.domain.PriceCalculator;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.ServicePriceDto;
import pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1.ServicePriceNotExistsException;

import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Singleton
public class MockPriceCalculator implements PriceCalculator {

    private Map<String, ServicePriceDto> priceMap = init();

    private LinkedHashMap<String, ServicePriceDto> init() {
        LinkedHashMap<String, ServicePriceDto> map = new LinkedHashMap<>();

        map.put("SERVICE_1", new ServicePriceDto(BigDecimal.valueOf(10)));
        map.put("SERVICE_2", new ServicePriceDto(BigDecimal.valueOf(100)));
        map.put("SERVICE_3", new ServicePriceDto(BigDecimal.valueOf(25)));
        map.put("SERVICE_4", new ServicePriceDto(BigDecimal.valueOf(1500)));

        return map;
    }

    @Override
    public ServicePriceDto findPriceForService(String serviceCode) {
        ServicePriceDto price = priceMap.get(serviceCode);
        if(price == null)
            throw new ServicePriceNotExistsException(String.format("Price for Service with code %s not exists!", serviceCode));

        return price;
    }
}
