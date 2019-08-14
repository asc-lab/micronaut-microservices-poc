package pl.altkom.asc.lab.micronaut.poc.payment.domain;


import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import pl.altkom.asc.lab.micronaut.poc.payment.service.api.v1.PolicyAccountDto;

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
    public Optional<PolicyAccount> findByPolicyNumber(String policyNumber) {
        return Optional.ofNullable(policyAccountMap.get(policyNumber));
    }

    @Override
    public PolicyAccount save(PolicyAccount policyAccount) {
        policyAccountMap.put(policyAccount.getPolicyNumber(), policyAccount);
        return policyAccount;
    }

    @Override
    public Collection<PolicyAccountDto> findAll() {
        return policyAccountMap
                .values()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<PolicyAccount> findByPolicyAccountNumber(String accountNumber) {
        return policyAccountMap.values().stream()
                .filter(ac -> ac.getPolicyAccountNumber().equals(accountNumber))
                .findFirst();
    }
    
    
    private PolicyAccountDto mapToDto(PolicyAccount entity){
        return new PolicyAccountDto(
                entity.getPolicyAccountNumber(),
                entity.getPolicyNumber(),
                entity.getCreated(),
                entity.getUpdated());
    }
}
