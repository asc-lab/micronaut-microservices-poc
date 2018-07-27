package pl.altkom.asc.lab.micronaut.poc.payment.domain;

public class PolicyAccount {

    private String policyNumber;

    public PolicyAccount() {
    }

    public PolicyAccount(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

}
