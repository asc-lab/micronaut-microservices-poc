package pl.altkom.asc.lab.micronaut.poc.gateway;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.server.types.files.StreamedFile;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Maybe;
import javax.inject.Inject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import pl.altkom.asc.lab.micronaut.poc.crm.service.api.v1.BlogOperations;
import pl.altkom.asc.lab.micronaut.poc.crm.service.api.v1.BlogPostDetails;
import pl.altkom.asc.lab.micronaut.poc.crm.service.api.v1.BlogPostsPage;
import pl.altkom.asc.lab.micronaut.poc.crm.service.api.v1.ImageOperations;

import java.io.IOException;
import java.io.InputStream;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api/crm")
public class CrmGatewayController {
    @Inject
    private BlogOperations crmBlogClient;
    @Inject
    private ImageOperations crmImageClient;
    
    @Get("/blogposts")
    Maybe<BlogPostsPage> blogPosts() {
    return crmBlogClient.getBlogPosts(0,100);
    }

    @Get("/blogposts/{postId}")
    Maybe<BlogPostDetails> blogPost(String postId) {
    return crmBlogClient.getBlogPost(postId);
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/images/{imageName}")
    StreamedFile image(String imageName) throws IOException {
        InputStream is = crmImageClient.getImage(imageName);
        return new StreamedFile(is,imageName);
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/images64Base/{imageName}")
    String imageBase64(String imageName) throws IOException {
        InputStream is = crmImageClient.getImage(imageName);
        return Base64.encodeBase64String(IOUtils.toByteArray(is));
    }
}
