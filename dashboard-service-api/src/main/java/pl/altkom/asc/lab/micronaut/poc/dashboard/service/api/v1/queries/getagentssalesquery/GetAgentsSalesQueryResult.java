package pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.getagentssalesquery;

import lombok.*;
import pl.altkom.asc.lab.micronaut.poc.dashboard.service.api.v1.queries.getagentssalesquery.dto.SalesDto;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAgentsSalesQueryResult {
    @Singular("agentTotal")
    private Map<String, SalesDto> perAgentTotal;
}
