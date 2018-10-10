package pl.altkom.asc.lab.micronaut.poc.auth;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.handlers.LoginHandler;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import io.micronaut.security.token.jwt.validator.JwtTokenValidator;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CustomLoginHandlerTest {
    private static EmbeddedServer server;
    private static RxHttpClient httpClient;

    @BeforeClass
    public static void setupServer() {
        server = ApplicationContext.run(EmbeddedServer.class);
        httpClient = server.getApplicationContext().createBean(RxHttpClient.class, server.getURL());
    }

    @AfterClass
    public static void stopServer() {
        if (httpClient != null) {
            httpClient.stop();
        }
        if (server != null) {
            server.stop();
        }
    }

    @Test
    public void customLoginHandler() {
        //when:
        HttpRequest request = HttpRequest.create(HttpMethod.POST, "/login")
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .body(new UsernamePasswordCredentials("jimmy.solid", "secret"));
        HttpResponse<CustomBearerAccessRefreshToken> rsp = httpClient.toBlocking().exchange(request, CustomBearerAccessRefreshToken.class);

        //then:
        Assert.assertEquals(200, rsp.getStatus().getCode());
        Assert.assertTrue(rsp.body()  != null);
        Assert.assertTrue(rsp.body().getAccessToken()  != null);
        Assert.assertTrue(rsp.body().getRefreshToken()  != null);
        Assert.assertTrue(rsp.body().getAvatar()  != null);
        Assert.assertEquals("https://pickaface.net/gallery/avatar/unr_example_170227_1250_yq2lr.png", rsp.body().getAvatar());

    }
}
