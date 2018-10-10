package pl.altkom.asc.lab.micronaut.poc.auth;

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import io.micronaut.security.token.jwt.validator.JwtTokenValidator;
import io.reactivex.Flowable;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Assert;
import org.junit.Test;

public class CustomClaimsTest {
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
    public void testCustomClaimsArePresentInJwt() {
        //when:
        HttpRequest request = HttpRequest.create(HttpMethod.POST, "/login")
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .body(new UsernamePasswordCredentials("jimmy.solid", "secret"));
        HttpResponse<AccessRefreshToken> rsp = httpClient.toBlocking().exchange(request, AccessRefreshToken.class);

        //then:
        Assert.assertEquals(200, rsp.getStatus().getCode());
        Assert.assertTrue(rsp.body()  != null);
        Assert.assertTrue(rsp.body().getAccessToken()  != null);
        Assert.assertTrue(rsp.body().getRefreshToken()  != null);

        //when:
        String accessToken = rsp.body().getAccessToken();
        JwtTokenValidator tokenValidator = server.getApplicationContext().getBean(JwtTokenValidator.class);
        Authentication authentication = Flowable.fromPublisher(tokenValidator.validateToken(accessToken)).blockingFirst();

        //then:
        Assert.assertTrue(authentication.getAttributes()!=null);
        Assert.assertTrue(authentication.getAttributes().containsKey("roles"));
        Assert.assertTrue(authentication.getAttributes().containsKey("iss"));
        Assert.assertTrue(authentication.getAttributes().containsKey("exp"));
        Assert.assertTrue(authentication.getAttributes().containsKey("iat"));
        Assert.assertTrue(authentication.getAttributes().containsKey("avatar"));
        Assert.assertTrue(authentication.getAttributes().get("avatar").equals("https://pickaface.net/gallery/avatar/unr_example_170227_1250_yq2lr.png"));
    }
}
