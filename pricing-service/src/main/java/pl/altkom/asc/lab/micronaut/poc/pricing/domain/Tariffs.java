package pl.altkom.asc.lab.micronaut.poc.pricing.domain;

import java.util.Optional;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.annotation.*;
import io.micronaut.data.model.*;

@Repository
public interface Tariffs extends CrudRepository<Tariff, Long>  {

    Optional<Tariff> findByCode(String code);
    
    Tariff getByCode(String code);
}
