package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.PolicyDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyRegisteredApiEvent {

    private PolicyDto policy;
}
