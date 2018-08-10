package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Policy {

    @Id
    @GeneratedValue
    private Long id;

    private String number;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL)
    private Set<PolicyVersion> versions;

    public Policy(String number) {
        this.number = number;
        this.versions = new HashSet<>();
    }

    public LocalDate getLastVersionValidityFrom() {
        return versions().lastVersion().getVersionValidityPeriod().getFrom();
    }

    public LocalDate getLastVersionValidityTo() {
        return versions().lastVersion().getVersionValidityPeriod().getTo();
    }

    public PolicyVersionCollection versions() {
        return new PolicyVersionCollection(this, versions);
    }

    public void terminate() {
        //TODO implement terminate business logic (specification pattern example)
    }
}
