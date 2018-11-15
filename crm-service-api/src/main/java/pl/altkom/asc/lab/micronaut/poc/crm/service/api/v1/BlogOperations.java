package pl.altkom.asc.lab.micronaut.poc.crm.service.api.v1;

import io.micronaut.http.annotation.Get;
import io.reactivex.Maybe;

public interface BlogOperations {

    @Get
    Maybe<BlogPostsPage> getBlogPosts(int pageNumber, int postsPerPage);

   @Get("/{postId}")
    Maybe<BlogPostDetails> getBlogPost(String postId);
}
