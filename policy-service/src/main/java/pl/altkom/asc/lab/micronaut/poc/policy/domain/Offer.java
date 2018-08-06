package pl.altkom.asc.lab.micronaut.poc.policy.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Offer {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "number")
    private String number;
    @Column(name = "product_code")
    private String productCode;
    @Column(name = "policy_from")
    private LocalDate policyFrom;
    @Column(name = "policy_to")
    private LocalDate policyTo;
    @ElementCollection
    @CollectionTable(name = "offer_answers", joinColumns = @JoinColumn(name = "offer_id"))
    @MapKeyColumn(name = "question_code")
    @Column(name = "answer")
    private Map<String, String> answers;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @ElementCollection
    @CollectionTable(name = "offer_cover", joinColumns = @JoinColumn(name = "offer_id"))
    @MapKeyColumn(name = "cover_code")
    @Column(name = "price")
    private Map<String, BigDecimal> coversPrices;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OfferStatus status;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    
    /*
    Offers are valid only for 30 days
    */
    public boolean isExpired(LocalDate theDate) {
        return creationDate.plusDays(30).isAfter(theDate);
    }
}
