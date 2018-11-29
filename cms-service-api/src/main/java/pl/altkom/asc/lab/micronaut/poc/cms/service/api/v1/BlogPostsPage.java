package pl.altkom.asc.lab.micronaut.poc.cms.service.api.v1;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
