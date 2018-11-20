package pl.altkom.asc.lab.micronaut.poc.crm.service.api.v1;

import java.util.List;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostsPage {
    private int offset;
    private int max;
    private long count;
    private long total;
    private boolean more;
    private List<BlogPost> items;
}
