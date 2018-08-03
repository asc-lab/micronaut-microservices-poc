package pl.altkom.asc.lab.micronaut.poc.payment.service.api.v1.exceptions;

public class BankStatementsFileNotFound extends RuntimeException {
    public BankStatementsFileNotFound(Throwable cause) {
        super(cause);
    }
}
