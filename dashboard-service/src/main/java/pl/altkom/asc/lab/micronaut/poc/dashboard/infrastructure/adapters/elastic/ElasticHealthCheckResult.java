package pl.altkom.asc.lab.micronaut.poc.dashboard.infrastructure.adapters.elastic;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.Setter;

@Introspected
@Getter
@Setter
public class ElasticHealthCheckResult {
    private String status;

    public boolean isOk() {
        return "green".equals(status) || "yellow".equals(status);
    }
}
