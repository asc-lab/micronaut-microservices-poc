package pl.altkom.asc.lab.micronaut.poc.cms.service.api.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CmsLink {
    private String name;
    private String url;
}
