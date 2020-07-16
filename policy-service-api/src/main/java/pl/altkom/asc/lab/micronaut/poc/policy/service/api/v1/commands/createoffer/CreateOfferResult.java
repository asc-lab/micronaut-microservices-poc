package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createoffer;

import java.math.BigDecimal;
import java.util.Map;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Introspected
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOfferResult {
    private String offerNumber;
    private BigDecimal totalPrice;
    private Map<String, BigDecimal> coversPrices;

    public static CreateOfferResult empty() {
        return new CreateOfferResult();
    }
}
