package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import java.util.Optional;
import io.micronaut.data.repository.GenericRepository;
import io.micronaut.data.annotation.*;
import io.micronaut.data.model.*;

@Repository
public interface PolicyRepository extends GenericRepository<Policy,Long> {
    Optional<Policy> findByNumber(String number);

    Policy save(Policy policy);
}
