package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Cover {
    @Id
    @GeneratedValue
    private Long id;

    private String code;
    
    private BigDecimal price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "POLICY_VERSION_ID")
    private PolicyVersion policyVersion;

    public Cover(PolicyVersion policyVersion, String code, BigDecimal price) {
        this.policyVersion = policyVersion;
        this.code = code;
        this.price = price;
    }

    @Override
    public String toString() {
        return code + " - " + price;
    }
}
