package pl.altkom.asc.lab.micronaut.poc.auth;

import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Singleton;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

@Singleton
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider {

    private final InsuranceAgentsRepository insuranceAgents;

    @Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        Optional<InsuranceAgent> agent = insuranceAgents.findByLogin((String) authenticationRequest.getIdentity());

        if (agent.isPresent() && agent.get().passwordMatches((String) authenticationRequest.getSecret())) {
            return Mono.just(new AuthenticationResponse() {
    
                @Override
                public Optional<Authentication> getAuthentication() {
                    return Optional.of(createAuthentication(agent.get()));
                }
            });
        }

        return Mono.just(new AuthenticationFailed());
    }

    private Authentication createAuthentication(InsuranceAgent agent) {
        return new InsuranceAgentDetails(agent.login(), agent.avatar(), agent.availableProductCodes());
    }
}
