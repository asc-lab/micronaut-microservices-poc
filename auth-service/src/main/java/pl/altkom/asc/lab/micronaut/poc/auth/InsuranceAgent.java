package pl.altkom.asc.lab.micronaut.poc.auth;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class InsuranceAgent {

    private String login;
    private String password;
    private List<String> availableProducts;

    InsuranceAgent(String login, String password, List<String> availableProducts) {
        this.login = login;
        this.password = password;
        this.availableProducts = availableProducts;
    }
    
    boolean passwordMatches(String passwordToTest) {
        return this.password.equals(passwordToTest);
    }
}
