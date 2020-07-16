package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.terminatepolicy;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Introspected
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
