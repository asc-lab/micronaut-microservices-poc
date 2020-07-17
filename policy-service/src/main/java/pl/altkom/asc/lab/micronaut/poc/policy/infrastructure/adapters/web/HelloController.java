package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.adapters.web;

import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.Health;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

@Controller("/hello")
public class HelloController {

    @ExecuteOn(TaskExecutors.IO)
    @Get
    public HttpStatus index() {
        return HttpStatus.OK;
    }

    @ExecuteOn(TaskExecutors.IO)
    @Get("/version")
    public Health version() {
        return new Health("1.0", "OK");
    }
}