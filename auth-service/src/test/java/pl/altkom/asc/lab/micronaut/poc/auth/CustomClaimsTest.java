package pl.altkom.asc.lab.micronaut.poc.auth;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import io.micronaut.security.token.jwt.validator.JwtTokenValidator;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
@Property(name = "micronaut.server.port", value = "-1")
public class CustomClaimsTest {
    @Inject
    private EmbeddedServer server;

    @Inject
    @Client("/")
    private HttpClient httpClient;

    @Test
    public void testCustomClaimsArePresentInJwt() {
        //when:
        var request = HttpRequest.create(HttpMethod.POST, "/login")
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .body(new UsernamePasswordCredentials("jimmy.solid", "secret"));
        HttpResponse<AccessRefreshToken> rsp = httpClient.toBlocking().exchange(request, AccessRefreshToken.class);

        //then:
        assertThat(rsp.getStatus().getCode()).isEqualTo(200);
        assertThat(rsp.body()).isNotNull();
        assertThat(rsp.body().getAccessToken()).isNotNull();
        assertThat(rsp.body().getRefreshToken()).isNull();

        //when:
        String accessToken = rsp.body().getAccessToken();
        JwtTokenValidator tokenValidator = server.getApplicationContext().getBean(JwtTokenValidator.class);
        Authentication authentication = Flux
                .from(tokenValidator.validateToken(accessToken,request))
                .blockFirst();

        //then:
        assertThat(authentication.getAttributes()).isNotNull();
        assertThat(authentication.getAttributes()).containsKey("roles");
        assertThat(authentication.getAttributes()).containsKey("iss");
        assertThat(authentication.getAttributes()).containsKey("exp");
        assertThat(authentication.getAttributes()).containsKey("iat");
        assertThat(authentication.getAttributes()).containsKey("avatar");
        assertThat(authentication.getAttributes().get("avatar")).isEqualTo("static/avatars/jimmy_solid.png");
    }
}
