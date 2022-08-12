package pl.altkom.asc.lab.micronaut.poc.payment.infrastructure.adapters.mock;

import jakarta.inject.Singleton;
import java.util.UUID;
import pl.altkom.asc.lab.micronaut.poc.payment.domain.PolicyAccountNumberGenerator;

@Singleton
public class MockPolicyAccountNumberGenerator implements PolicyAccountNumberGenerator {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
