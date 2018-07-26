package pl.altkom.asc.lab.micronaut.poc.api.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServiceInfo {
    private String ver;
    private String status;
}
