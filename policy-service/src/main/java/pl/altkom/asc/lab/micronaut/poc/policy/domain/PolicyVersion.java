package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.domain.vo.DateRange;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class PolicyVersion {
    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "POLICY_ID")
    private Policy policy;

    private Long versionNumber;

    private String productCode;

    private Person policyHolder;

    private String accountNumber;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "from", column = @Column(name = "cover_from")),
            @AttributeOverride(name = "to", column = @Column(name = "cover_to"))
    })
    private DateRange coverPeriod;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "from", column = @Column(name = "version_from")),
            @AttributeOverride(name = "to", column = @Column(name = "version_to"))
    })
    private DateRange versionValidityPeriod;

    @OneToMany(mappedBy = "policyVersion", cascade = CascadeType.ALL)
    private Set<Cover> covers;
    
    private BigDecimal totalPremiumAmount;

    CoverCollection covers() {
        return new CoverCollection(this, covers);
    }
}
