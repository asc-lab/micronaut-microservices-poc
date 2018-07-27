package pl.altkom.asc.lab.micronaut.poc.pricing.service.api.v1;

public class ServicePriceNotExistsException extends RuntimeException {
    public ServicePriceNotExistsException(String msg) {
        super(msg);
    }
}
