package pl.altkom.asc.lab.micronaut.poc.policy;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.AgentRef;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Offer;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.OfferRepository;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.OfferStatus;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Person;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyFactory;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.PolicyRepository;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.dto.PersonDto;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.getpolicydetails.GetPolicyDetailsQueryResult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest(transactional = false)
public class PolicyControllerTest {

    @Inject
    private PolicyTestClient client;

    @Inject
    private OfferRepository offers;
    
    @Inject
    private PolicyRepository policies;
    
    @Test
    public void testGetPolicyByNumber() {
        Map<String, BigDecimal> coverPrices = new HashMap<>();
        coverPrices.put("C1", new BigDecimal("100"));
        coverPrices.put("C2", new BigDecimal("99"));
        Offer offer112 = new Offer(
            null,
            "112",
            "TRI",
            LocalDate.of(2018, 8, 1),
            LocalDate.of(2018, 8, 10),
            new HashMap<>(),
            new BigDecimal("199"),
            coverPrices,
            OfferStatus.NEW,
            LocalDate.now()
        );
        offers.save(offer112);
    
        Person policyHolder = new Person("Jan", "Nowak", "1111111116");
        AgentRef agent = AgentRef.of("jimmy.solid");
        var policy = new PolicyFactory()
            .fromOffer(offer112,policyHolder,agent);
        policies.save(policy);
        
        String policyNumber = policy.getNumber();
        GetPolicyDetailsQueryResult foundPolicy = client.get(policyNumber);

        assertNotNull(foundPolicy);
        assertNotNull(foundPolicy.getPolicy());
        assertEquals(policyNumber, foundPolicy.getPolicy().getNumber());
    }

    @Test
    public void testCreatePolicy() {
        //given: offer with number 111 exists
        Map<String, BigDecimal> coverPrices = new HashMap<>();
        coverPrices.put("C1", new BigDecimal("100"));
        coverPrices.put("C2", new BigDecimal("99"));
        Offer offer111 = new Offer(
                null,
                "111",
                "TRI",
                LocalDate.of(2018, 8, 1),
                LocalDate.of(2018, 8, 10),
                new HashMap<>(),
                new BigDecimal("199"),
                coverPrices,
                OfferStatus.NEW,
                LocalDate.now()
        );
        offers.save(offer111);

        //when policy creation is requested
        CreatePolicyCommand cmd = new CreatePolicyCommand(
                "111",
                new PersonDto("Timmy", "Lamb", "111111111116"),
                "admin");

        CreatePolicyResult result = client.create(cmd);

        //then policy is created and number is assigned
        assertNotNull(result);
        assertNotNull(result.getPolicyNumber());
    }
    
}
