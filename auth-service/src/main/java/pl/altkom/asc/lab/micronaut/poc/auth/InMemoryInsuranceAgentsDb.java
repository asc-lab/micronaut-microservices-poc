package pl.altkom.asc.lab.micronaut.poc.auth;

import javax.inject.Singleton;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class InMemoryInsuranceAgentsDb implements InsuranceAgents {

    private Map<String, InsuranceAgent> db = new ConcurrentHashMap<>();

    public InMemoryInsuranceAgentsDb() {
        add(new InsuranceAgent("jimmy.solid", "secret", "https://pickaface.net/gallery/avatar/unr_example_170227_1250_yq2lr.png", Arrays.asList("TRI", "HSI", "FAI", "CAR")));
        add(new InsuranceAgent("danny.solid", "secret", "https://pickaface.net/gallery/avatar/unr_example_170227_1250_yq2lr.png", Arrays.asList("TRI", "HSI")));
        add(new InsuranceAgent("admin", "admin", "https://pickaface.net/gallery/avatar/unr_example_170227_1250_yq2lr.png", Arrays.asList("TRI", "HSI")));
        add(new InsuranceAgent("agent1", "agent1", "https://pickaface.net/gallery/avatar/unr_example_170227_1250_yq2lr.png", Arrays.asList("TRI", "HSI")));
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
