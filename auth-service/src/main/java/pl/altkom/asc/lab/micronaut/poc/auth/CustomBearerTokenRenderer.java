package pl.altkom.asc.lab.micronaut.poc.auth;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.http.HttpHeaderValues;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import io.micronaut.security.token.jwt.render.BearerTokenRenderer;
import jakarta.inject.Singleton;

@Singleton
@Replaces(BearerTokenRenderer.class)
public class CustomBearerTokenRenderer extends BearerTokenRenderer {

    private final String BEARER_TOKEN_TYPE = HttpHeaderValues.AUTHORIZATION_PREFIX_BEARER;

    @Override
    public AccessRefreshToken render(Authentication userDetails, Integer expiresIn, String accessToken, String refreshToken) {
        if (userDetails instanceof InsuranceAgentDetails) {
            return new CustomBearerAccessRefreshToken(
                    userDetails.getName(),
                    userDetails.getRoles(),
                    expiresIn,
                    accessToken,
                    refreshToken,
                    BEARER_TOKEN_TYPE,
                    ((InsuranceAgentDetails) userDetails).getAvatar()
            );
        }

        return new BearerAccessRefreshToken(
                userDetails.getName(),
                userDetails.getRoles(),
                expiresIn,
                accessToken,
                refreshToken,
                BEARER_TOKEN_TYPE);
    }
}
