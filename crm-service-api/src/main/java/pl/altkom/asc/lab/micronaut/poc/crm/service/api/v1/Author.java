package pl.altkom.asc.lab.micronaut.poc.crm.service.api.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    private String username;
    private String email;
    private boolean confirmed;
    private boolean blocked;
}
