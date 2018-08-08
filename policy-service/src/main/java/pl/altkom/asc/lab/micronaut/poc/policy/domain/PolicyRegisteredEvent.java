package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyRegisteredEvent {

    private Policy policy;
}
