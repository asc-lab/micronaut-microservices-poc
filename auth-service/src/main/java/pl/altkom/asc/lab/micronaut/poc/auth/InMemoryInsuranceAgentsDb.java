package pl.altkom.asc.lab.micronaut.poc.auth;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Singleton;

@Singleton
public class InMemoryInsuranceAgentsDb implements InsuranceAgents {

    private Map<String, InsuranceAgent> db = new ConcurrentHashMap<>();

    public InMemoryInsuranceAgentsDb() {
        add(new InsuranceAgent("jimmy.solid","secret", Arrays.asList("TRI","HSI","FAI","CAR")));
        add(new InsuranceAgent("danny.solid","secret", Arrays.asList("TRI","HSI")));
        add(new InsuranceAgent("admin","admin", Arrays.asList("TRI","HSI")));
        add(new InsuranceAgent("agent1","agent1", Arrays.asList("TRI","HSI")));
    }
    
    @Override
    public void add(InsuranceAgent agent) {
        db.put(agent.getLogin(), agent);
    }

    @Override
    public Optional<InsuranceAgent> findByLogin(String login) {
        InsuranceAgent agent = db.get(login);
        return Optional.ofNullable(agent);
    }
    
}
