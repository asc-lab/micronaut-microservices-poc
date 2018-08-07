package pl.altkom.asc.lab.micronaut.poc.payment.domain;

import io.micronaut.spring.tx.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Singleton;
import lombok.RequiredArgsConstructor;
import pl.altkom.asc.lab.micronaut.poc.payment.domain.BankStatementFile.BankStatement;

@Singleton
@RequiredArgsConstructor
public class InPaymentRegistrationService {

    private final PolicyAccountRepository policyAccountRepository;
    
    @Transactional
    public void registerInPayments(String directory, LocalDate date) {
        BankStatementFile fileToImport = new BankStatementFile(directory, date);
        
        if (!fileToImport.exists()) {
            return;
        }
        
        List<BankStatement> bankStatements = fileToImport.read();
        bankStatements.forEach(this::registerInPayment);
        fileToImport.markProcessed();
    }

    private void registerInPayment(BankStatement bankStatement) {
        policyAccountRepository
                .findByNumber(bankStatement.getAccountNumber())
                .ifPresent(account -> { 
                    account.inPayment(bankStatement.getAmount(), bankStatement.getAccountingDate()); 
                });
    }
}
