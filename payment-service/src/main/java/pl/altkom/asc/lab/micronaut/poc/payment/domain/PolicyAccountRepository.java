package pl.altkom.asc.lab.micronaut.poc.payment.domain;

import java.util.Collection;
import java.util.Optional;

import io.micronaut.data.repository.GenericRepository;
import io.micronaut.data.annotation.*;
import io.micronaut.data.model.*;

@Repository
public interface PolicyAccountRepository extends GenericRepository<PolicyAccount, Long> {

    @Join(value = "entries", type = Join.Type.FETCH) 
    Optional<PolicyAccount> findByPolicyNumber(String policyNumber);
    
    @Join(value = "entries", type = Join.Type.FETCH) 
    @Query("FROM PolicyAccount p WHERE p.policyAccountNumber = :policyAccountNumber")
    Optional<PolicyAccount> findByPolicyAccountNumber(String policyAccountNumber);

    PolicyAccount save(PolicyAccount policyAccount);

    Collection<PolicyAccount> findAll();
}
