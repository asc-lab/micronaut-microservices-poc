package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.policyregistered;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.policyregistered.dto.PolicyDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyRegisteredApiEvent {

    private PolicyDto policy;
}
