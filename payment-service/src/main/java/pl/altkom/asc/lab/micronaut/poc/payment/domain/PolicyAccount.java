package pl.altkom.asc.lab.micronaut.poc.payment.domain;

public class PolicyAccount {

    private String policyNumber;
    private String policyAccountNumber;

    public PolicyAccount() {
    }

    public PolicyAccount(String policyNumber, String policyAccountNumber) {
        this.policyNumber = policyNumber;
        this.policyAccountNumber = policyAccountNumber;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getPolicyAccountNumber() {
        return policyAccountNumber;
    }
}
