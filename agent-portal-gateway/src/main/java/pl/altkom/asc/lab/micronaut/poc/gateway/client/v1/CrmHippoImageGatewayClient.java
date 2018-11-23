package pl.altkom.asc.lab.micronaut.poc.gateway.client.v1;

import pl.altkom.asc.lab.micronaut.poc.crm.service.api.v1.ImageOperations;

import javax.inject.Singleton;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Singleton
public class CrmHippoImageGatewayClient implements ImageOperations {
    private final CrmHippoConfig config;
    public CrmHippoImageGatewayClient(CrmHippoConfig config)  {
        this.config = config;
    }

    @Override
    public InputStream getImageByName(String imageName) throws IOException {
        return new BufferedInputStream(
                new URL(config.getUrl() +
                        "/site/binaries/content/gallery/minicms/" +
                        imageName)
                        .openStream());
    }

    @Override
    public InputStream getImageByPath(String path) throws IOException {
        return new BufferedInputStream(
                new URL(config.getUrl() +
                        "/site/binaries/" +
                        path)
                        .openStream());
    }
}
