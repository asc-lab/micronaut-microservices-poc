package pl.altkom.asc.lab.micronaut.poc.auth;

import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;
import io.reactivex.Flowable;
import java.util.Optional;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;

@Singleton
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider  {
    private final InsuranceAgents insuranceAgents;
    
    @Override
    public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest ar) {
        Optional<InsuranceAgent> agent = insuranceAgents.findByLogin((String)ar.getIdentity());
        
        if (agent.isPresent() && agent.get().passwordMatches((String)ar.getSecret())) {
            return Flowable.just(createUserDetails(agent.get()));
        }
        
        return Flowable.just(new AuthenticationFailed());
    }
    
    private UserDetails createUserDetails(InsuranceAgent agent) {
        return new UserDetails(agent.getLogin(), agent.getAvailableProducts());
    }
}
