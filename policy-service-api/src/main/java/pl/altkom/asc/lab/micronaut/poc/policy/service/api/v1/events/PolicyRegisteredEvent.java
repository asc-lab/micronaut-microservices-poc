package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.dto.PolicyDto;

@Introspected
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PolicyRegisteredEvent {
    private PolicyDto policy;
}
