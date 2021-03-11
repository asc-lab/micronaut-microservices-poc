package pl.altkom.asc.lab.micronaut.poc.auth;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import io.micronaut.core.annotation.Creator;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@MappedEntity
record InsuranceAgent(
    @Id UUID id,
    String login,
    String password,
    String avatar,
    String availableProducts) {

    /*
    @Creator
    InsuranceAgent(UUID id,String login, String password, String avatar, String availableProducts) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.avatar = avatar;
        this.availableProducts = availableProducts;
    }*/

    InsuranceAgent(UUID id,String login, String password, String avatar, List<String> availableProducts) {
        this(id,login,password,avatar,String.join(";",availableProducts));
    }
    
    boolean passwordMatches(String passwordToTest) {
        return this.password.equals(passwordToTest);
    }

    public Collection<String> availableProductCodes() {
        return Arrays.asList(availableProducts.split(";"));
    }
}
