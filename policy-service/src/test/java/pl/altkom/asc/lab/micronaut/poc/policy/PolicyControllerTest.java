package pl.altkom.asc.lab.micronaut.poc.policy;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.altkom.asc.lab.micronaut.poc.policy.client.PolicyClient;
import pl.altkom.asc.lab.micronaut.poc.policy.commands.policyregister.RegisterPolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.queries.findpolicy.FindPolicyQueryResult;
import pl.altkom.asc.lab.micronaut.poc.policy.queries.findpolicy.dto.PolicyViewDto;

import java.util.Optional;

public class PolicyControllerTest {

    private static EmbeddedServer server;
    private static PolicyClient client;

    @BeforeClass
    public static void setup() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = server.getApplicationContext().createBean(PolicyClient.class, server.getURL());
    }

    @AfterClass
    public static void cleanup() {
        if (server != null) {
            server.stop();
        }
    }

    @Test
    public void testPolicies() {
        FindPolicyQueryResult policies = client.policies();
        Assert.assertNotNull(policies);
        Assert.assertNotNull(policies.getPolicies());
        Assert.assertFalse(policies.getPolicies().isEmpty());
    }

    @Test
    public void testRegisterPolicy() {
        String policyNumber = "P1";
        RegisterPolicyCommand cmd = new RegisterPolicyCommand(PolicyBuilder.buildDto(policyNumber, 1L));
        client.register(cmd);

        FindPolicyQueryResult policies = client.policies();
        Optional<PolicyViewDto> dto = policies.getPolicies()
                .stream()
                .filter(x -> x.getNumber().equals(policyNumber))
                .findAny();

        Assert.assertNotNull(dto);
        Assert.assertTrue(dto.isPresent());
        Assert.assertEquals(policyNumber, dto.get().getNumber());
    }
}
