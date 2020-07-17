package pl.altkom.asc.lab.micronaut.poc.payment.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PolicyAccountTest {
    @Test
    public void canRegisterInPayment() {
        PolicyAccount account = new PolicyAccount("A", "A");
        account.inPayment(new BigDecimal("10"), LocalDate.of(2018,1,1));
        
        Assertions.assertEquals(1, account.getEntries().size(),"one entry present");
        Assertions.assertEquals(new BigDecimal("10"), account.balanceAt(LocalDate.of(2018,1,1)),"balance is 10");
    }
    
    @Test
    public void canRegisterOutpayment() {
        PolicyAccount account = new PolicyAccount("A", "A");
        account.outPayment(new BigDecimal("10"), LocalDate.of(2018,1,1));

        Assertions.assertEquals(1, account.getEntries().size(),"one entry present");
        Assertions.assertEquals(new BigDecimal("-10"), account.balanceAt(LocalDate.of(2018,1,1)),"balance is -10");
    }
    
    @Test
    public void canRegisterExpectdPayment() {
        PolicyAccount account = new PolicyAccount("A", "A");
        account.expectedPayment(new BigDecimal("10"), LocalDate.of(2018,1,1));

        Assertions.assertEquals(1, account.getEntries().size(), "one entry present");
        Assertions.assertEquals(new BigDecimal("-10"), account.balanceAt(LocalDate.of(2018,1,1)),"balance is -10");
    }
    
    @Test
    public void canProperlyCalculateBalance() {
        PolicyAccount account = new PolicyAccount("A", "A");
        account.expectedPayment(new BigDecimal("10"), LocalDate.of(2018,1,1));
        account.expectedPayment(new BigDecimal("10"), LocalDate.of(2018,6,1));
        account.inPayment(new BigDecimal("15"), LocalDate.of(2018,1,7));

        Assertions.assertEquals(new BigDecimal("-10"), account.balanceAt(LocalDate.of(2018,1,1)),"balance at 2018-1-1  is -10");
        Assertions.assertEquals(new BigDecimal("5"), account.balanceAt(LocalDate.of(2018,1,7)),"balance at 2018-1-7  is 5");
        Assertions.assertEquals(new BigDecimal("-5"), account.balanceAt(LocalDate.of(2018,6,1)),"balance at 2018-6-1  is -5");
    }
}
