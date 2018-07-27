package pl.altkom.asc.lab.micronaut.poc.policy.commands.policyclose;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Policy;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClosePolicyResult {

    private String policyNumber;

    static ClosePolicyResult success(Policy policy) {
        return new ClosePolicyResult(policy.getNumber());
    }
}
