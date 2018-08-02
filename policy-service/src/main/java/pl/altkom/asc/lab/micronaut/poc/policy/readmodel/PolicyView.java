package pl.altkom.asc.lab.micronaut.poc.policy.readmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyView {
    @Id
    @GeneratedValue
    private Long id;
    private String number;

    public PolicyView(String number) {
        this.number = number;
    }
}
