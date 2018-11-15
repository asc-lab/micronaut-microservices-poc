package pl.altkom.asc.lab.micronaut.poc.gateway;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.reactivex.Maybe;
import javax.inject.Inject;
import pl.altkom.asc.lab.micronaut.poc.crm.service.api.v1.BlogOperations;
import pl.altkom.asc.lab.micronaut.poc.crm.service.api.v1.BlogPostDetails;
import pl.altkom.asc.lab.micronaut.poc.crm.service.api.v1.BlogPostsPage;

@Secured("isAuthenticated()")
@Controller("/api/crm")
public class CrmGatewayController {
    @Inject
    private BlogOperations crmBlogClient;
    
    @Get("/blogposts")
     Maybe<BlogPostsPage> blogPosts() {
        return crmBlogClient.getBlogPosts(0,100);
    }
     
     @Get("/blogposts/{postId}")
     Maybe<BlogPostDetails> blogPost(String postId) {
        return crmBlogClient.getBlogPost(postId);
    }

}
