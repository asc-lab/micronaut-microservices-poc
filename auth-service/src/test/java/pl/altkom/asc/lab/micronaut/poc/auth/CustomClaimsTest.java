package pl.altkom.asc.lab.micronaut.poc.auth;

import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import io.micronaut.security.token.jwt.validator.JwtTokenValidator;
import io.micronaut.test.annotation.MicronautTest;
import io.reactivex.Flowable;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
@Property(name = "micronaut.server.port", value = "-1")
public class CustomClaimsTest {
    @Inject
    private EmbeddedServer server;

    @Inject
    @Client("/")
    private RxHttpClient httpClient;

    @Test
    public void testCustomClaimsArePresentInJwt() {
        //when:
        HttpRequest request = HttpRequest.create(HttpMethod.POST, "/login")
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .body(new UsernamePasswordCredentials("jimmy.solid", "secret"));
        HttpResponse<AccessRefreshToken> rsp = httpClient.toBlocking().exchange(request, AccessRefreshToken.class);

        //then:
        assertThat(rsp.getStatus().getCode()).isEqualTo(200);
        assertThat(rsp.body()).isNotNull();
        assertThat(rsp.body().getAccessToken()).isNotNull();
        assertThat(rsp.body().getRefreshToken()).isNotNull();

        //when:
        String accessToken = rsp.body().getAccessToken();
        JwtTokenValidator tokenValidator = server.getApplicationContext().getBean(JwtTokenValidator.class);
        Authentication authentication = Flowable.fromPublisher(tokenValidator.validateToken(accessToken)).blockingFirst();

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
