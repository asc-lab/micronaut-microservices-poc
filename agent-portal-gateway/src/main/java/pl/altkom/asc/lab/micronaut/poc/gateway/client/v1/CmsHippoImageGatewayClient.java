package pl.altkom.asc.lab.micronaut.poc.gateway.client.v1;

import pl.altkom.asc.lab.micronaut.poc.cms.service.api.v1.ImageOperations;

import javax.inject.Singleton;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Singleton
public class CmsHippoImageGatewayClient implements ImageOperations {
    private final CmsHippoConfig config;
    public CmsHippoImageGatewayClient(CmsHippoConfig config)  {
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
