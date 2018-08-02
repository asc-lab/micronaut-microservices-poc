package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//io.micronaut.http.codec.CodecException: Error encoding object
// [pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.RegisterPolicyResult@44694ad0] to JSON:
// No serializer found for class pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.policyregister.RegisterPolicyResult
// and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPolicyResult {

    private String policyNumber;

    public static RegisterPolicyResult success(String policyNumber) {
        return new RegisterPolicyResult(policyNumber);
    }

    public static RegisterPolicyResult empty() {
        //TODO implement empty result
        return null;
    }
}
