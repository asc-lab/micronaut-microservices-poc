package pl.altkom.asc.lab.micronaut.poc.payment.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

public class PolicyAccountTest {
    @Test
    public void canRegisterInPayment() {
        PolicyAccount account = new PolicyAccount("A", "A");
        account.inPayment(new BigDecimal("10"), LocalDate.of(2018,1,1));
        
        Assert.assertEquals("one entry present", 1, account.getEntries().size());
        Assert.assertEquals("balance is 10", new BigDecimal("10"), account.balanceAt(LocalDate.of(2018,1,1)));
    }
    
    @Test
    public void canRegisterOutpayment() {
        PolicyAccount account = new PolicyAccount("A", "A");
        account.outPayment(new BigDecimal("10"), LocalDate.of(2018,1,1));
        
        Assert.assertEquals("one entry present", 1, account.getEntries().size());
        Assert.assertEquals("balance is -10", new BigDecimal("-10"), account.balanceAt(LocalDate.of(2018,1,1)));
    }
    
    @Test
    public void canRegisterExpectdPayment() {
        PolicyAccount account = new PolicyAccount("A", "A");
        account.expectedPayment(new BigDecimal("10"), LocalDate.of(2018,1,1));
        
        Assert.assertEquals("one entry present", 1, account.getEntries().size());
        Assert.assertEquals("balance is -10", new BigDecimal("-10"), account.balanceAt(LocalDate.of(2018,1,1)));
    }
    
    @Test
    public void canProperlyCalculateBalance() {
        PolicyAccount account = new PolicyAccount("A", "A");
        account.expectedPayment(new BigDecimal("10"), LocalDate.of(2018,1,1));
        account.expectedPayment(new BigDecimal("10"), LocalDate.of(2018,6,1));
        account.inPayment(new BigDecimal("15"), LocalDate.of(2018,1,7));
       
        Assert.assertEquals("balance at 2018-1-1  is -10", new BigDecimal("-10"), account.balanceAt(LocalDate.of(2018,1,1)));
        Assert.assertEquals("balance at 2018-1-7  is 5", new BigDecimal("5"), account.balanceAt(LocalDate.of(2018,1,7)));
        Assert.assertEquals("balance at 2018-6-1  is -5", new BigDecimal("-5"), account.balanceAt(LocalDate.of(2018,6,1)));
    }
}
