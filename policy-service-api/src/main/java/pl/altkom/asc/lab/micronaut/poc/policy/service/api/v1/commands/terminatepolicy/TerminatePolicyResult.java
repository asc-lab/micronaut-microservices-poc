package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.terminatepolicy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TerminatePolicyResult {

    private String policyNumber;

    public static TerminatePolicyResult success(String policyNumber) {
        return new TerminatePolicyResult(policyNumber);
    }

    public static TerminatePolicyResult empty() {
        return new TerminatePolicyResult();
    }
}
