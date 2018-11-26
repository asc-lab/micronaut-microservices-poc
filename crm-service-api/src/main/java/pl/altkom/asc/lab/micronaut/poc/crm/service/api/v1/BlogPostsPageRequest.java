package pl.altkom.asc.lab.micronaut.poc.crm.service.api.v1;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BlogPostsPageRequest {
    private Integer pageNumber;
    private Integer pageSize;
    private String searchPhrase;
}
