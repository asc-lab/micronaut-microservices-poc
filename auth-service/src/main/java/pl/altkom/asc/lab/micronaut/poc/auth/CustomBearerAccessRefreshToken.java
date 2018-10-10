package pl.altkom.asc.lab.micronaut.poc.auth;

import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;

import java.util.Collection;

public class CustomBearerAccessRefreshToken extends BearerAccessRefreshToken {
    private String avatar;

    public CustomBearerAccessRefreshToken() {

    }

    public CustomBearerAccessRefreshToken(String username,
                                          Collection<String> roles,
                                          Integer expiresIn,
                                          String accessToken,
                                          String refreshToken,
                                          String avatar) {
        super(username, roles, expiresIn, accessToken, refreshToken);
        this.avatar = avatar;

    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
