package pl.altkom.asc.lab.micronaut.poc.policy;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.policy.commands.policyclose.ClosePolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.commands.policyclose.ClosePolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.commands.policyregister.RegisterPolicyCommand;
import pl.altkom.asc.lab.micronaut.poc.policy.commands.policyregister.RegisterPolicyResult;
import pl.altkom.asc.lab.micronaut.poc.policy.queries.findpolicy.FindPolicyQuery;
import pl.altkom.asc.lab.micronaut.poc.policy.queries.findpolicy.FindPolicyQueryResult;
import pl.altkom.asc.lab.micronaut.poc.policy.infrastructure.bus.CommandBus;

@RequiredArgsConstructor
@Controller("/policies")
public class PolicyController {

    private final CommandBus bus;

    @Get("/")
    FindPolicyQueryResult policies() {
        return bus.executeQuery(new FindPolicyQuery());
    }

    @Post("/")
    RegisterPolicyResult register(@Body RegisterPolicyCommand cmd) {
        return bus.executeCommand(cmd);
    }

    @Post("/close")
    ClosePolicyResult close(@Body ClosePolicyCommand cmd) {
        return bus.executeCommand(cmd);
    }
}
