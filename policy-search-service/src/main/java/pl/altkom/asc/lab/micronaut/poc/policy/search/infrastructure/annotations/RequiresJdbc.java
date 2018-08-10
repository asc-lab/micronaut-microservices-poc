package pl.altkom.asc.lab.micronaut.poc.policy.search.infrastructure.annotations;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PACKAGE, ElementType.TYPE})
@Requires(property = "datasources.default.url")
@Requires(notEnv = Environment.TEST)
public @interface RequiresJdbc {
}
