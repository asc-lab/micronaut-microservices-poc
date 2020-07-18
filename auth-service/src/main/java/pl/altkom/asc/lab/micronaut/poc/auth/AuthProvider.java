package pl.altkom.asc.lab.micronaut.poc.auth;

import org.reactivestreams.Publisher;

import java.util.Optional;

import javax.inject.Singleton;

import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.reactivex.Flowable;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider {

    //private final InsuranceAgents insuranceAgents;
    private final InsuranceAgentsRepository insuranceAgents;

    @Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {
        Optional<InsuranceAgent> agent = insuranceAgents.findByLogin((String) authenticationRequest.getIdentity());

        if (agent.isPresent() && agent.get().passwordMatches((String) authenticationRequest.getSecret())) {
            return Flowable.just(createUserDetails(agent.get()));
        }

        return Flowable.just(new AuthenticationFailed());
    }

    private InsuranceAgentDetails createUserDetails(InsuranceAgent agent) {
        return new InsuranceAgentDetails(agent.getLogin(), agent.getAvatar(), agent.availableProductCodes());
    }
}
