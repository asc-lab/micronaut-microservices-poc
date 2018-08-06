package pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.commands.createoffer;

import java.math.BigDecimal;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOfferResult {
    private String offerNumber;
    private BigDecimal totalPrice;
    private Map<String, BigDecimal> coversPrices;
}
