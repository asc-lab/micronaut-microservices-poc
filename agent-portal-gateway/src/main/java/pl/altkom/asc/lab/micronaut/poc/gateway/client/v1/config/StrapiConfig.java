package pl.altkom.asc.lab.micronaut.poc.gateway.client.v1.config;

import io.micronaut.context.annotation.ConfigurationProperties;
import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(StrapiConfig.PREFIX)
public class StrapiConfig {

    static final String PREFIX = "strapicms";

    @Getter
    @Setter
    private String url;
}
