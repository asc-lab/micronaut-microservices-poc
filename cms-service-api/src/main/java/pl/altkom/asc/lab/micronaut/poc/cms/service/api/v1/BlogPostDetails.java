package pl.altkom.asc.lab.micronaut.poc.cms.service.api.v1;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogPostDetails {
    private String id;
    private String name;
    private String displayName;
    private String title;
    private String publicationDate;
    private List<String> categories;
    private String introduction;
    private String htmlContent;
    private List<CmsLink> links;
}
