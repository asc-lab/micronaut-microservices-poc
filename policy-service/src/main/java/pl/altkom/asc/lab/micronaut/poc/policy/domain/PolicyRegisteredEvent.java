package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PolicyRegisteredEvent {

    private final Policy policy;
}
