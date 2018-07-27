package pl.altkom.asc.lab.micronaut.poc.payment.domain;

import java.util.Optional;

public interface PolicyAccountRepository {

    Optional<PolicyAccount> findForPolicy(String policyNumber);

    void save(PolicyAccount policyAccount);
}
