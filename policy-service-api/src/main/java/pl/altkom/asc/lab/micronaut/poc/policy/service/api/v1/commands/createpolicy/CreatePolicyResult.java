package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createpolicy;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Introspected
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePolicyResult {
    private String policyNumber;
}
