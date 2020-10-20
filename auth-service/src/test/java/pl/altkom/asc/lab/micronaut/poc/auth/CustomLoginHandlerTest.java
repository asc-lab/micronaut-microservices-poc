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
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
@Property(name = "micronaut.server.port", value = "-1")
public class CustomLoginHandlerTest {
    @Inject
    private EmbeddedServer server;

    @Inject
    @Client("/")
    private RxHttpClient httpClient;

    @Test
    public void customLoginHandler() {
        //when:
        HttpRequest request = HttpRequest.create(HttpMethod.POST, "/login")
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .body(new UsernamePasswordCredentials("jimmy.solid", "secret"));
        HttpResponse<CustomBearerAccessRefreshToken> rsp = httpClient.toBlocking().exchange(request, CustomBearerAccessRefreshToken.class);

        //then:
        assertThat(rsp.getStatus().getCode()).isEqualTo(200);
        assertThat(rsp.body()).isNotNull();
        assertThat(rsp.body().getAccessToken()).isNotNull();
        assertThat(rsp.body().getRefreshToken()).isNull();
        assertThat(rsp.body().getAvatar()).isNotNull();
        assertThat(rsp.body().getAvatar()).isEqualTo("static/avatars/jimmy_solid.png");
    }
}
