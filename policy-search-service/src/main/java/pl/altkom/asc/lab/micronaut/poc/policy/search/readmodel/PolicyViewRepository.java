package pl.altkom.asc.lab.micronaut.poc.policy.search.readmodel;

import java.util.List;

public interface PolicyViewRepository {

    List<PolicyView> findAll();

    void save(PolicyView view);
}
