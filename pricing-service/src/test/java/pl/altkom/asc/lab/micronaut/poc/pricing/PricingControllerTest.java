package pl.altkom.asc.lab.micronaut.poc.pricing;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PricingControllerTest {

    private static EmbeddedServer server;
    //private static PricingTestClient client;

    @BeforeClass
    public static void setup() {
        server = ApplicationContext.run(EmbeddedServer.class);
        //client = server.getApplicationContext().createBean(PricingTestClient.class, server.getURL());
    }

    @Test
    public void testClient() {

    }

    @AfterClass
    public static void cleanup() {
        if (server != null)
            server.stop();
    }
}
