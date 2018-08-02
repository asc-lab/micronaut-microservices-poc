package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyclose;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClosePolicyResult {

    private String policyNumber;

    public static ClosePolicyResult success(String policyNumber) {
        return new ClosePolicyResult(policyNumber);
    }

    public static ClosePolicyResult empty() {
        //TODO implement empty/fallback result
        return null;
    }
}
