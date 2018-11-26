package pl.altkom.asc.lab.micronaut.poc.gateway;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.server.types.files.StreamedFile;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Maybe;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.validation.constraints.Null;

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
    
    @Get("/blogposts{?pageNumber,pageSize,searchPhrase}")
    Maybe<BlogPostsPage> blogPosts(@Nullable Integer pageNumber, @Nullable Integer pageSize, @Nullable String searchPhrase) {
        BlogPostsPageRequest pageRequest = BlogPostsPageRequest.builder()
                .pageNumber(pageNumber!=null ? pageNumber : 0)
                .pageSize(pageSize!= null ? pageSize : 2)
                .searchPhrase(searchPhrase!=null ? searchPhrase : "")
                .build();
        return crmBlogClient.getBlogPosts(pageRequest);
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
