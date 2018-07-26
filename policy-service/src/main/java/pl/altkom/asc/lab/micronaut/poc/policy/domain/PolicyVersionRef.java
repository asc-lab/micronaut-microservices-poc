package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class PolicyVersionRef {

    private String policyNumber;
    private Long versionNumber;

    static PolicyVersionRef of(PolicyVersion policyVersion) {
        return new PolicyVersionRef(policyVersion.getPolicy().getNumber(), policyVersion.getVersionNumber());
    }

    public PolicyRef policyRef() {
        return new PolicyRef(policyNumber);
    }
}
