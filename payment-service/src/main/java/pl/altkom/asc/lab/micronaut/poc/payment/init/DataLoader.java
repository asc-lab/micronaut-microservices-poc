package pl.altkom.asc.lab.micronaut.poc.payment.init;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import io.micronaut.spring.tx.annotation.Transactional;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.altkom.asc.lab.micronaut.poc.payment.domain.PolicyAccount;
import pl.altkom.asc.lab.micronaut.poc.payment.infrastructure.adapters.db.PolicyAccountDb;

@Singleton
@Slf4j
@RequiredArgsConstructor
public class DataLoader  implements ApplicationEventListener<ServerStartupEvent> {
    private final PolicyAccountDb policyAccountDb;

    @Transactional
    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        DemoAccountsFactory.demoAccounts().forEach(this::addIfNotExists);
        log.info("Demo data added");
    }
    
    private void addIfNotExists(PolicyAccount account) {
        if (!policyAccountDb.findByNumber(account.getPolicyAccountNumber()).isPresent()) {
            policyAccountDb.add(account);
        }
    }
}
