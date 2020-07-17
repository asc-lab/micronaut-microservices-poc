package pl.altkom.asc.lab.micronaut.poc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.queries.findpolicy.FindPolicyQueryResult;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;

public class PolicySearchControllerTest {

    private static EmbeddedServer server;
    private static PolicySearchTestClient client;

    @BeforeAll
    public static void setup() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = server.getApplicationContext().createBean(PolicySearchTestClient.class, server.getURL());
    }

    @Test
    public void testPolicies() {
        FindPolicyQueryResult policies = client.policies("1234").blockingGet();

        Assertions.assertNotNull(policies);
        Assertions.assertNotNull(policies.getPolicies());
        Assertions.assertFalse(policies.getPolicies().isEmpty());
    }

    @AfterAll
    public static void cleanup() {
        if (server != null)
            server.stop();

    }
}
