package pl.altkom.asc.lab.micronaut.poc.auth;

import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.reactivex.Flowable;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider {

    private final InsuranceAgents insuranceAgents;

    @Override
    public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest request) {
        Optional<InsuranceAgent> agent = insuranceAgents.findByLogin((String) request.getIdentity());

        if (agent.isPresent() && agent.get().passwordMatches((String) request.getSecret())) {
            return Flowable.just(createUserDetails(agent.get()));
        }

        return Flowable.just(new AuthenticationFailed());
    }

    private InsuranceAgentDetails createUserDetails(InsuranceAgent agent) {
        return new InsuranceAgentDetails(agent.getLogin(), agent.getAvatar(), agent.getAvailableProducts());
    }
}
