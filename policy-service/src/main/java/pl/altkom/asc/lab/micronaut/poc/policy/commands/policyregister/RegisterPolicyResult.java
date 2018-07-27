package pl.altkom.asc.lab.micronaut.poc.policy.commands.policyregister;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.Policy;

//io.micronaut.http.codec.CodecException: Error encoding object
// [pl.altkom.asc.lab.micronaut.poc.policy.commands.policyregister.RegisterPolicyResult@44694ad0] to JSON:
// No serializer found for class pl.altkom.asc.lab.micronaut.poc.policy.commands.policyregister.RegisterPolicyResult
// and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPolicyResult {

    private String policyNumber;

    static RegisterPolicyResult success(Policy policy) {
        return new RegisterPolicyResult(policy.getNumber());
    }
}
