package pl.altkom.asc.lab.micronaut.poc.gateway.client.v1.config;

import io.micronaut.context.annotation.ConfigurationProperties;
import lombok.Getter;
import lombok.Setter;


@ConfigurationProperties(CrmHippoConfig.PREFIX)
public class CrmHippoConfig {

    static final String PREFIX = "hippocms";

    @Getter
    @Setter
    private String url;
}
