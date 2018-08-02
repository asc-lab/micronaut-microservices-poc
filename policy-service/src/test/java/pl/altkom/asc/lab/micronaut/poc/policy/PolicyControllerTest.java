package pl.altkom.asc.lab.micronaut.poc.policy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.RegisterPolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.RegisterPolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.queries.policyfind.FindPolicyQueryResult;

public class PolicyControllerTest {

    private static EmbeddedServer server;
    private static PolicyTestClient client;

    @BeforeClass
    public static void setup() {
        server = ApplicationContext.run(EmbeddedServer.class);
        client = server.getApplicationContext().createBean(PolicyTestClient.class, server.getURL());
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
        printJson(cmd);
        RegisterPolicyResult result = client.register(cmd);

        Assert.assertNotNull(result);
        Assert.assertEquals(policyNumber, result.getPolicyNumber());
    }

    private void printJson(RegisterPolicyCommand cmd) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(cmd);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void cleanup() {
        if (server != null)
            server.stop();

    }
}
