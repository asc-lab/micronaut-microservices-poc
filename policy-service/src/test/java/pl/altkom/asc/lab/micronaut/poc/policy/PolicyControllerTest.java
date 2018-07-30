package pl.altkom.asc.lab.micronaut.poc.policy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.altkom.asc.lab.micronaut.poc.policy.client.PolicyClient;
import pl.altkom.asc.lab.micronaut.poc.policy.commands.policyregister.RegisterPolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.commands.policyregister.RegisterPolicyResult;
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
