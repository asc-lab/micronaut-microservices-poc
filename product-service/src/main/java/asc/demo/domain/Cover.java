package asc.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cover {
    private String code;
    private String name;
    private String description;
    private boolean optional;
    private BigDecimal sumInsured;
}
