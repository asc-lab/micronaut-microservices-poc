package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import io.micronaut.core.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AgentRef {
    @Column(name = "agent_login")
    private String login;

    public static AgentRef of(String login) {
        if (StringUtils.isNotEmpty(login))
            return new AgentRef(login);

        return null;
    }
}
