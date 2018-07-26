package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class ServiceCollection {
    private final Cover cover;
    private final Set<Service> services;

    Service add(String code, CoPayment coPayment, Limit limit) {
        Service svc = new Service(null, code, cover, limit, coPayment);
        services.add(svc);
        return svc;
    }


    boolean hasService(String serviceCode) {
        return services
                .stream()
                .anyMatch(s -> s.getCode().equals(serviceCode));
    }

    public Service withCode(String serviceCode) {
        return services
                .stream()
                .filter(s -> s.getCode().equals((serviceCode)))
                .findFirst()
                .get();
    }
}
