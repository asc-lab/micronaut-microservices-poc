package pl.altkom.asc.lab.micronaut.poc.auth;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import io.micronaut.security.token.jwt.render.BearerTokenRenderer;

@Replaces(bean = BearerTokenRenderer.class)
public class CustomBearerTokenRenderer extends BearerTokenRenderer {

    @Override
    public AccessRefreshToken render(UserDetails userDetails, Integer expiresIn, String accessToken, String refreshToken) {
        if (userDetails instanceof InsuranceAgentDetails) {
            return new CustomBearerAccessRefreshToken(
                    userDetails.getUsername(),
                    userDetails.getRoles(),
                    expiresIn,
                    accessToken,
                    refreshToken,
                    ((InsuranceAgentDetails) userDetails).getAvatarUrl()
            );
        }

        return new BearerAccessRefreshToken(
                userDetails.getUsername(),
                userDetails.getRoles(),
                expiresIn,
                accessToken,
                refreshToken);
    }
}
