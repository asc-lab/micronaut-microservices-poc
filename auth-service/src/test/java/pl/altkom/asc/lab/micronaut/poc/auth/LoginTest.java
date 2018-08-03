package pl.altkom.asc.lab.micronaut.poc.auth;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.fail;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoginTest {
    private static EmbeddedServer server;
    private static RxHttpClient httpClient;
    
    @BeforeClass
    public static void setup() {
        server = ApplicationContext.run(EmbeddedServer.class);
        httpClient = server.getApplicationContext().createBean(RxHttpClient.class, server.getURL());
    }
    
    @AfterClass
    public static void cleanup() {
        if (server != null)
            server.stop();
    }
    
    @Test
    public void canLoginWithValidCredentials() {
        UsernamePasswordCredentials upc = new UsernamePasswordCredentials("jimmy.solid","secret");
        HttpRequest loginRequest = HttpRequest.POST("/login", upc);
        HttpResponse<BearerAccessRefreshToken> rsp = httpClient.toBlocking().exchange(loginRequest, BearerAccessRefreshToken.class);
        
        assertEquals("Status should be OK", rsp.getStatus(), HttpStatus.OK);
        assertEquals("user should be jimmy", "jimmy.solid", rsp.getBody().get().getUsername());
    }
    
    
    @Test
    public void canntLoginWithInvalidCredentials() {
        try {
            UsernamePasswordCredentials upc = new UsernamePasswordCredentials("jimmy.solid","secret111");
            HttpRequest loginRequest = HttpRequest.POST("/login", upc);
            HttpResponse<BearerAccessRefreshToken> rsp = httpClient.toBlocking().exchange(loginRequest, BearerAccessRefreshToken.class);
            fail();
        } catch (HttpClientResponseException ex) {
            assertEquals("Status should be OK", ex.getStatus(), HttpStatus.UNAUTHORIZED);
        }
        
    }

}
