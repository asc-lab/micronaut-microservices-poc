package pl.altkom.asc.lab.micronaut.poc.payment.infrastructure.adapters.jobs;

import io.micronaut.context.annotation.ConfigurationProperties;
import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("payments")
@Getter
@Setter
public class BankStatementImportJobCfg {
    private String importDir = "c:\\temp\\bank_imports";  
}
