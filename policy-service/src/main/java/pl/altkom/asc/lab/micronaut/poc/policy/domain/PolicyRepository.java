package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import java.util.Optional;

public interface PolicyRepository {

    Optional<Policy> findByNumber(String number);

    void add(Policy policy);
}
