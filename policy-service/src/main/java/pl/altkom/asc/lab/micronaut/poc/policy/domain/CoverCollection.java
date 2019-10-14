package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
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

    public Map<String, BigDecimal> correct(BigDecimal correctionFactor) {
        Map<String,BigDecimal> correctedValues = new HashMap<>();
        covers.forEach(c -> correctedValues.put(
                c.getCode(),
                c.getPrice().multiply(correctionFactor).setScale(2, RoundingMode.HALF_UP)));
        return correctedValues;
    }
}
