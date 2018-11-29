package pl.altkom.asc.lab.micronaut.poc.cms.service.api.v1;

import io.micronaut.http.annotation.Get;

import java.io.IOException;
import java.io.InputStream;

public interface ImageOperations {
    @Get("/{imageName}")
    InputStream getImageByName(String imageName) throws IOException;

    @Get("/imageset/{+path}")
    InputStream getImageByPath(String path) throws IOException;
}
