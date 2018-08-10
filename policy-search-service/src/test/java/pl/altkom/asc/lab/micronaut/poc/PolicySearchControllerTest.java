package pl.altkom.asc.lab.micronaut.poc;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.altkom.asc.lab.micronaut.poc.policy.search.service.api.v1.queries.findpolicy.FindPolicyQueryResult;

public class PolicySearchControllerTest {

    private static EmbeddedServer server;
    private static PolicySearchTestClient client;

    @BeforeClass
    public static void setup() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = server.getApplicationContext().createBean(PolicySearchTestClient.class, server.getURL());
    }

    @Test
    public void testPolicies() {
        FindPolicyQueryResult policies = client.policies();

        Assert.assertNotNull(policies);
        Assert.assertNotNull(policies.getPolicies());
        Assert.assertFalse(policies.getPolicies().isEmpty());
    }

    @AfterClass
    public static void cleanup() {
        if (server != null)
            server.stop();

    }
}
