package pl.altkom.asc.lab.micronaut.poc.crm.service.api.v1;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogPost {
    private String id;
    private String name;
}
