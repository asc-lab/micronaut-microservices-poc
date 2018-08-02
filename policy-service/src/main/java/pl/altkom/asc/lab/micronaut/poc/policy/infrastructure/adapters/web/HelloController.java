package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.web;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.HttpStatus;
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.Health;

@Controller("/hello")
public class HelloController {

    @Get("/")
    public HttpStatus index() {
        return HttpStatus.OK;
    }

    @Get("/version")
    public Health version() {
        return new Health("1.0", "OK");
    }
}