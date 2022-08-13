package pl.altkom.asc.lab.micronaut.poc.auth;

import io.micronaut.security.authentication.ServerAuthentication;
import java.util.Collection;
import java.util.Map;
import lombok.Getter;

@Getter
class InsuranceAgentDetails extends ServerAuthentication {

    private final String avatar;

    InsuranceAgentDetails(String username, String avatar, Collection<String> roles) {
        super(username, roles, Map.of("avatar", avatar));
        this.avatar = avatar;
   }
   
   
}
