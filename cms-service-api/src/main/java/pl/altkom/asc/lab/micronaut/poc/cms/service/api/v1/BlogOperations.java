package pl.altkom.asc.lab.micronaut.poc.cms.service.api.v1;

import io.micronaut.http.annotation.Get;
import io.reactivex.Maybe;

public interface BlogOperations {

    @Get("/{?pageNumber,pageSize,searchPhrase}")
    Maybe<BlogPostsPage> getBlogPosts(BlogPostsPageRequest pageRequest);

    @Get("/{postId}")
    Maybe<BlogPostDetails> getBlogPost(String postId);
}
