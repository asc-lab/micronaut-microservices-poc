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
    private String avatar;
    private List<String> availableProducts;

    InsuranceAgent(String login, String password, String avatar, List<String> availableProducts) {
        this.login = login;
        this.password = password;
        this.avatar = avatar;
        this.availableProducts = availableProducts;
    }
    
    boolean passwordMatches(String passwordToTest) {
        return this.password.equals(passwordToTest);
    }
}
