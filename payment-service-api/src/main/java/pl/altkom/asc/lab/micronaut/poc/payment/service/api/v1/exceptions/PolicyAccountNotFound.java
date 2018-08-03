package pl.altkom.asc.lab.micronaut.poc.payment.service.api.v1.exceptions;

public class PolicyAccountNotFound extends RuntimeException {
    public PolicyAccountNotFound(String accountNumber) {
        super("Policy Account not found. Looking for account with number: " + accountNumber);
    }
}
