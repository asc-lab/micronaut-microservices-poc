package pl.altkom.asc.lab.micronaut.poc.auth;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Singleton;

@Singleton
public class InMemoryInsuranceAgentsDb implements InsuranceAgents {
    private Map<Long, InsuranceAgent> db = new ConcurrentHashMap<>();

    public InMemoryInsuranceAgentsDb() {
        db.put(1L, new InsuranceAgent("jimmy.solid","secret", Arrays.asList("TRI","HSI","FAI","CAR")));
        db.put(2L, new InsuranceAgent("danny.solid","secret", Arrays.asList("TRI","HSI")));
    }
    
    @Override
    public void add(InsuranceAgent agent) {
        db.put(agent.getId(), agent);
    }

    @Override
    public Optional<InsuranceAgent> findByLogin(String login) {
        return db
                .values()
                .stream()
                .filter(a -> a.getLogin().equals(login))
                .findFirst();
    }
    
}
