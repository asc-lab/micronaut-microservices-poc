package pl.altkom.asc.lab.micronaut.poc.payment.domain;


import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class MockPolicyAccountRepository implements PolicyAccountRepository {

    private Map<String, PolicyAccount> policyAccountMap = init();

    private LinkedHashMap<String, PolicyAccount> init() {
        LinkedHashMap<String, PolicyAccount> map = new LinkedHashMap<>();

        map.put("PA1", new PolicyAccount("POLICY_1", "231232132131"));
        map.put("PA2", new PolicyAccount("POLICY_2", "389hfswjfrh2032r"));
        map.put("PA3", new PolicyAccount("POLICY_3", "0rju130fhj20"));

        return map;
    }

    @Override
    public Optional<PolicyAccount> findForPolicy(String policyNumber) {
        return Optional.ofNullable(policyAccountMap.get(policyNumber));
    }

    @Override
    public void add(PolicyAccount policyAccount) {
        policyAccountMap.put(policyAccount.getPolicyNumber(), policyAccount);
    }

    @Override
    public Collection<PolicyAccount> findAll() {
        return policyAccountMap.values();
    }

    @Override
    public Optional<PolicyAccount> findByNumber(String accountNumber) {
        return policyAccountMap.values().stream()
                .filter(ac -> ac.getPolicyAccountNumber().equals(accountNumber))
                .findFirst();
    }
}
