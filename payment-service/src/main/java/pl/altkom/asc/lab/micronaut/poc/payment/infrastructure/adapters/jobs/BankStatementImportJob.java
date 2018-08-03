package pl.altkom.asc.lab.micronaut.poc.payment.infrastructure.adapters.jobs;

import io.micronaut.context.annotation.Prototype;
import io.micronaut.scheduling.annotation.Scheduled;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.altkom.asc.lab.micronaut.poc.payment.domain.InPaymentRegistrationService;


@Prototype
@Slf4j
@RequiredArgsConstructor
public class BankStatementImportJob {
    private final BankStatementImportJobCfg jobCfg;
    private final InPaymentRegistrationService inPaymentRegistrationService;

    @Scheduled(cron = "0 0 13,19 ? * MON,TUE,WED,THU,FRI *")
    public void importBankStatement() {
       log.info("Starting bank statement import job");
       inPaymentRegistrationService.registerInPayments(jobCfg.getImportDir(), LocalDate.now());
       
    }
}
