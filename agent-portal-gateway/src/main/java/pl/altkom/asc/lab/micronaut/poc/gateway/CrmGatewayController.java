package pl.altkom.asc.lab.micronaut.poc.gateway;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Maybe;
import pl.altkom.asc.lab.micronaut.poc.crm.service.api.v1.BlogOperations;
import pl.altkom.asc.lab.micronaut.poc.crm.service.api.v1.BlogPostDetails;
import pl.altkom.asc.lab.micronaut.poc.crm.service.api.v1.BlogPostsPage;

import javax.inject.Inject;
import javax.inject.Qualifier;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api/crm")
public class CrmGatewayController {

    @Inject
    private BlogOperations crmBlogClient;

    @Get("/blogposts")
    Maybe<BlogPostsPage> blogPosts() {
        return crmBlogClient.getBlogPosts(0, 100);
    }

    @Get("/blogposts/{postId}")
    Maybe<BlogPostDetails> blogPost(String postId) {
        return crmBlogClient.getBlogPost(postId);
    }

}
