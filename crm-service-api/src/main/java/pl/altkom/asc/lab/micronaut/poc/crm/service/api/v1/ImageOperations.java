package pl.altkom.asc.lab.micronaut.poc.crm.service.api.v1;

import io.micronaut.http.annotation.Get;

import java.io.IOException;
import java.io.InputStream;

public interface ImageOperations {
    @Get("/{imageName}")
    InputStream getImage(String imageName) throws IOException;
}
