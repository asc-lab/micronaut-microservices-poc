package pl.altkom.asc.lab.micronaut.poc.pricing.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Calculation {
    private String productCode;
    private LocalDate policyFrom;
    private LocalDate policyTo;
    private BigDecimal totalPremium;
    private Map<String,Cover> covers = new HashMap<>();
    private Map<String,Object> subject = new HashMap<>();

    public Calculation(String productCode,
                       LocalDate policyFrom,
                       LocalDate policyTo,
                       Iterable<String> selectedCovers,
                       Map<String,Object> subject) {
        this.productCode = productCode;
        this.policyFrom = policyFrom;
        this.policyTo = policyTo;
        this.totalPremium = BigDecimal.ZERO;
        selectedCovers.forEach(this::zeroPrice);
        this.subject = subject;
    }

    public Map<String,Object> toMap(){
        Map<String,Object> ctx = new HashMap<>();
        ctx.put("policyFrom", policyFrom);
        ctx.put("policyTo", policyTo);
        for (Cover c : covers.values()){
            ctx.put(c.getCode(), c);
        }
        ctx.putAll(subject);
        return ctx;
    }


    public void updateTotal() {
        totalPremium = getCovers()
                .values()
                .stream()
                .filter(c -> c.getPrice()!=null)
                .map(c -> c.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void zeroPrice(String cover){
        covers.put(cover, new Cover(cover,BigDecimal.ZERO));
    }
}
