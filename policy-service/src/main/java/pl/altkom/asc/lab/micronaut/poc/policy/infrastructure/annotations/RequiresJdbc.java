package pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.annotations;

import io.micronaut.context.annotation.Requires;

import javax.sql.DataSource;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PACKAGE, ElementType.TYPE})
@Requires(beans = DataSource.class)
@Requires(property = "datasources.default.url")
public @interface RequiresJdbc {
}
