package pl.altkom.asc.lab.micronaut.poc.auth;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@MicronautTest
@Property(name = "micronaut.server.port", value = "-1")
public class LoginTest {
    @Inject
    private EmbeddedServer server;

    @Inject
    @Client("/")
    private HttpClient httpClient;

    @Test
    public void canLoginWithValidCredentials() {
        UsernamePasswordCredentials upc = new UsernamePasswordCredentials("jimmy.solid","secret");
        var loginRequest = HttpRequest.POST("/login", upc);
        HttpResponse<BearerAccessRefreshToken> rsp = httpClient.toBlocking().exchange(loginRequest, BearerAccessRefreshToken.class);
        
        assertThat(rsp.getStatus().getCode()).isEqualTo(200);
        assertThat(rsp.getBody().get().getUsername()).isEqualTo("jimmy.solid");
    }
    
    
    @Test
    public void cantLoginWithInvalidCredentials() {
        try {
            var upc = new UsernamePasswordCredentials("jimmy.solid","secret111");
            var loginRequest = HttpRequest.POST("/login", upc);
            HttpResponse<BearerAccessRefreshToken> rsp = httpClient.toBlocking().exchange(loginRequest, BearerAccessRefreshToken.class);
            fail();
        } catch (HttpClientResponseException ex) {
            assertThat(ex.getStatus().getCode()).isEqualTo(HttpStatus.UNAUTHORIZED.getCode());
        }
        
    }

}
