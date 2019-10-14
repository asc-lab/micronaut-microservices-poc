package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.vo.DateRange;
import pl.altkom.asc.lab.micronaut.poc.policy.shared.exceptions.BusinessException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
public class PolicyVersionCollection {

    private final Policy policy;
    private final Set<PolicyVersion> versions;

    public PolicyVersion withNumber(Long number) {
        return versions
                .stream()
                .filter(v -> v.getVersionNumber().equals(number))
                .findFirst()
                .orElseThrow(() -> new BusinessException("POLICY NOT FOUND"));
    }

    public PolicyVersion lastVersion() {
        return versions
                .stream()
                .min(Comparators.BY_VERSION_NUMBER_DESC)
                .orElseThrow(() -> new BusinessException("POLICY NOT FOUND"));
    }


    PolicyVersion add(
            Long versionNumber,
            String productCode,
            Person policyHolder,
            String accountNumber,
            DateRange coverPeriod,
            DateRange versionPeriod,
            BigDecimal totalPremiumAmount,
            Map<String, BigDecimal> coversPrices) {

        if (hasVersion(versionNumber)) {
            throw new BusinessException("POLVEREXISTS", new Object[]{policy.getNumber(), versionNumber});
        }

        PolicyVersion ver = new PolicyVersion(
                null,
                policy,
                versionNumber,
                productCode,
                policyHolder,
                accountNumber,
                coverPeriod,
                versionPeriod,
                new HashSet<>(),
                totalPremiumAmount
        );
        versions.add(ver);
        coversPrices.forEach((key, value) -> ver.covers().add(key, value));

        return ver;
    }

    void addTerminalVersion(LocalDate terminationDate) {
        PolicyVersion baseVersion = lastVersion();

        DateRange newCoverPeriod = baseVersion.getCoverPeriod().endOn(terminationDate);

        DateRange newVersionPeriod = DateRange.between(
                terminationDate.plusDays(1),
                baseVersion.getVersionValidityPeriod().getTo());

        BigDecimal correctionFactor = newCoverPeriod.days().divide(
                baseVersion.getCoverPeriod().days(),
                20,
                RoundingMode.HALF_UP);
        Map<String, BigDecimal> correctedCovers = baseVersion
                .covers()
                .correct(correctionFactor);

        add(
                baseVersion.getVersionNumber()+1L,
                baseVersion.getProductCode(),
                baseVersion.getPolicyHolder(),
                baseVersion.getAccountNumber(),
                newCoverPeriod,
                newVersionPeriod,
                correctedCovers.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add),
                correctedCovers);
    }

    private boolean hasVersion(Long versionNumber) {
        return versions.stream().anyMatch(v -> v.getVersionNumber().equals(versionNumber));
    }

    static class Comparators {
        static final Comparator<PolicyVersion> BY_VERSION_NUMBER_ASC = Comparator.comparing(PolicyVersion::getVersionNumber);
        static final Comparator<PolicyVersion> BY_VERSION_NUMBER_DESC = (v1, v2) -> v2.getVersionNumber().compareTo(v1.getVersionNumber());
    }
}
