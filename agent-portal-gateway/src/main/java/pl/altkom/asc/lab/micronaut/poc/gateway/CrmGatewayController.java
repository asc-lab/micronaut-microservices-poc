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
import pl.altkom.asc.lab.micronaut.poc.crm.service.api.v1.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api/crm")
public class CrmGatewayController {
    @Inject
    private BlogOperations crmBlogClient;
    @Inject
    private ImageOperations crmImageClient;
    @Inject
    private ProductHeaderOperations crmProductHeaderOperations;
    
    @Get("/blogposts")
    Maybe<BlogPostsPage> blogPosts() {
    return crmBlogClient.getBlogPosts(0,100);
    }

    @Get("/blogposts/{postId}")
    Maybe<BlogPostDetails> blogPost(String postId) {
    return crmBlogClient.getBlogPost(postId);
    }

    @Get("/productHeaders")
    Maybe<List<ProductHeader>> productHeaers() { return crmProductHeaderOperations.productHeaders(); }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/images/{imageName}")
    StreamedFile imageByName(String imageName) throws IOException {
        InputStream is = crmImageClient.getImageByName(imageName);
        return new StreamedFile(is,imageName);
    }
    
    @Get("/imageset/{+imagePath}")
    StreamedFile imageByPath(String imagePath) throws IOException {
        InputStream is = crmImageClient.getImageByPath(imagePath);
        return new StreamedFile(is,imagePath);
    }

    @Get("/images64Base/{imageName}")
    String imageBase64(String imageName) throws IOException {
        InputStream is = crmImageClient.getImageByName(imageName);
        return Base64.encodeBase64String(IOUtils.toByteArray(is));
    }
}
