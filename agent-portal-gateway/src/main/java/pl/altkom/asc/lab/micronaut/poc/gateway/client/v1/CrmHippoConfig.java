package pl.altkom.asc.lab.micronaut.poc.gateway.client.v1;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties(CrmHippoConfig.PREFIX)
public class CrmHippoConfig {
    public static final String PREFIX = "hippocms";
     
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
