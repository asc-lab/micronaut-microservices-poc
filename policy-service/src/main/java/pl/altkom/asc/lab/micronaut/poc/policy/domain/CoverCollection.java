package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class CoverCollection {
    private final PolicyVersion policyVersion;
    private final Set<Cover> covers;

    Cover add(String code) {
        Cover cover = new Cover(policyVersion, code);
        covers.add(cover);
        return cover;
    }

    boolean hasCoverForService(String serviceCode) {
        return covers
                .stream()
                .anyMatch(c -> c.services().hasService(serviceCode));
    }

    CoPayment getCoPaymentForService(String serviceCode) {
        Cover coverWithService = coverWithService(serviceCode);

        Service service = coverWithService.services().withCode(serviceCode);

        return service.getCoPayment()!=null ? service.getCoPayment() : CoPayment.empty();
    }

    Limit getLimitForService(String serviceCode) {
        Cover coverWithService = coverWithService(serviceCode);

        Service service = coverWithService.services().withCode(serviceCode);

        return service!=null && service.getLimit()!= null ? service.getLimit() : Limit.empty();
    }

    private Cover coverWithService(String serviceCode) {
        return covers
                .stream()
                .filter(c -> c.services().hasService(serviceCode))
                .findFirst()
                .get();
    }
}
