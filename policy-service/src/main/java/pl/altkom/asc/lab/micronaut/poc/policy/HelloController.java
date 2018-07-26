package pl.altkom.asc.lab.micronaut.poc.policy;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.HttpStatus;
import pl.altkom.asc.lab.micronaut.poc.api.v1.ServiceInfo;

@Controller("/hello")
public class HelloController {

    @Get("/")
    public HttpStatus index() {
        return HttpStatus.OK;
    }

    @Get("/version")
    public ServiceInfo version() {
        return new ServiceInfo("1.0", "OK");
    }
}