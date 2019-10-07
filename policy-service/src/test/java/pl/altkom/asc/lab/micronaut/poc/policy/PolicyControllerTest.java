package pl.altkom.asc.lab.micronaut.poc.policy;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Offer;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.OfferRepository;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.OfferStatus;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.CreatePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy.dto.PersonDto;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.getpolicydetails.GetPolicyDetailsQueryResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PolicyControllerTest {

    private static EmbeddedServer server;
    private static PolicyTestClient client;

    @BeforeClass
    public static void setup() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = server.getApplicationContext().createBean(PolicyTestClient.class, server.getURL());
    }

    @Test
    public void testGetPolicyByNumber() {
        String policyNumber = "1234";
        GetPolicyDetailsQueryResult policy = client.get(policyNumber);

        Assert.assertNotNull(policy);
        Assert.assertNotNull(policy.getPolicy());
        Assert.assertEquals(policyNumber, policy.getPolicy().getNumber());
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
        server.getApplicationContext().getBean(OfferRepository.class).save(offer111);

        //when policy creation is requested
        CreatePolicyCommand cmd = new CreatePolicyCommand(
                "111",
                new PersonDto("Timmy", "Lamb", "111111111116"),
                "admin");

        CreatePolicyResult result = client.create(cmd);

        //then policy is created and number is assigned
        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getPolicyNumber());
    }

    @AfterClass
    public static void cleanup() {
        if (server != null)
            server.stop();

    }
}
