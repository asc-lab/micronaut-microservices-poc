package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.dto.PolicyDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyTerminatedEvent {
    private PolicyDto policy;
}
