package pl.altkom.asc.lab.micronaut.poc.payment.infrastructure.adapters.mock;

import pl.altkom.asc.lab.micronaut.poc.payment.domain.PolicyAccountNumberGenerator;

import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class MockPolicyAccountNumberGenerator implements PolicyAccountNumberGenerator {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
