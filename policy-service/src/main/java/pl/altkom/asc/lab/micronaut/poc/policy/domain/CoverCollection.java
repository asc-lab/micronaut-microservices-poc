package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class CoverCollection {
    private final PolicyVersion policyVersion;
    private final Set<Cover> covers;

    Cover add(String code, BigDecimal price) {
        Cover cover = new Cover(policyVersion, code, price);
        covers.add(cover);
        return cover;
    }
}
