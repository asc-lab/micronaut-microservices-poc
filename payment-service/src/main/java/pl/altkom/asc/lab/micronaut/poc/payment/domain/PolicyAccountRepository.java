package pl.altkom.asc.lab.micronaut.poc.payment.domain;

import java.util.Collection;
import java.util.Optional;

public interface PolicyAccountRepository {

    Optional<PolicyAccount> findForPolicy(String policyNumber);
    
    Optional<PolicyAccount> findByNumber(String accountNumber);

    void add(PolicyAccount policyAccount);

    Collection<PolicyAccount> findAll();
}
