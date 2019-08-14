package pl.altkom.asc.lab.micronaut.poc.payment.domain;

import com.google.common.io.Files;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class InPaymentRegistrationServiceTest {
    @Test
    public void canReadStatementsFile() throws IOException {
        PolicyAccountRepository policyAccountRepository = new MockPolicyAccountRepository();
        InPaymentRegistrationService inPaymentRegistrationService = new InPaymentRegistrationService(policyAccountRepository);
        PolicyAccount account231232132131 = policyAccountRepository.findByPolicyAccountNumber("231232132131").get();
        assertEquals(BigDecimal.ZERO, account231232132131.balanceAt(LocalDate.of(2019,12,31)));
        File testData  = createTestData();
                
        inPaymentRegistrationService.registerInPayments(testData.getParent(), LocalDate.of(2018, Month.AUGUST, 2));
        
        testData.delete();
        assertEquals(new BigDecimal("10.21"), account231232132131.balanceAt(LocalDate.of(2019,12,31)));
    }
    
    private File createTestData() throws IOException {
        File tempDir = Files.createTempDir();
        File testFile = new File(tempDir, "bankStatements_2018_8_2.csv");
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.append("TransactionId,TransactionType,AccountingDate,AccountNumber,Amount\r\n");
            writer.append("1,A,2018-08-01,231232132131,10.21\r\n");
            writer.append("1,A,2018-08-01,0rju130fhj20,99.25\r\n");
            return testFile;
        } 
    }
    
}
