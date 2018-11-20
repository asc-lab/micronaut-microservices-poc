package pl.altkom.asc.lab.micronaut.poc.gateway.client.v1;

import io.micronaut.context.annotation.Requires;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.uri.UriTemplate;
import io.reactivex.Maybe;
import pl.altkom.asc.lab.micronaut.poc.crm.service.api.v1.*;
import pl.altkom.asc.lab.micronaut.poc.gateway.client.v1.config.StrapiConfig;

import javax.inject.Singleton;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
@Requires(property = "strapicms.url")
public class StrapiBlogGatewayClient implements BlogOperations {

    private final RxHttpClient httpClient;

    public StrapiBlogGatewayClient(StrapiConfig config) throws MalformedURLException {
        this.httpClient = RxHttpClient.create(new URL(config.getUrl()));
    }

    @Override
    public Maybe<BlogPostsPage> getBlogPosts(int pageNumber, int postsPerPage) {
        Map<String, Object> params = new HashMap<>();
        params.put("start", pageNumber * postsPerPage);
        params.put("limit", postsPerPage);
        String path = "/posts?_start={start}&_limit={limit}";
        String uri = UriTemplate.of(path).expand(params);
        HttpRequest<?> req = HttpRequest.GET(uri);

        return httpClient
                .retrieve(req, Argument.of(Map[].class))
                .map(r -> ListMapper.from(r).map())
                .firstElement();
    }

    @Override
    public Maybe<BlogPostDetails> getBlogPost(String postId) {
        Map<String, Object> params = new HashMap<>();
        params.put("postId", postId);
        String path = "/posts/{postId}";
        String uri = UriTemplate.of(path).expand(params);
        HttpRequest<?> req = HttpRequest.GET(uri);

        return httpClient
                .retrieve(req, Argument.of(Map.class))
                .map(r -> Mapper.from(r).map())
                .firstElement();
    }

    static class Mapper {
        private Map post;

        private Mapper(Map post) {
            this.post = post;
        }

        static Mapper from(Map post) {
            return new Mapper(post);
        }

        BlogPostDetails map() {
            return BlogPostDetails.builder()
                    .id((String) post.get("id"))
                    .title((String) post.get("title"))
                    .markdownContent((String) post.get("content"))
                    .publicationDate((String) post.get("createdAt"))
                    .categories(Collections.singletonList((String) post.get("categories")))
                    .author(new Author(
                            (String) user().get("username"),
                            (String) user().get("email"),
                            (boolean) user().get("confirmed"),
                            (boolean) user().get("blocked")
                    ))
                    .build();
        }

        private Map user() {
            return (Map) post.get("user");
        }
    }

    static class ListMapper {
        private Map[] posts;

        private ListMapper(Map[] posts) {
            this.posts = posts;
        }

        static ListMapper from(Map[] map) {
            return new ListMapper(map);
        }

        BlogPostsPage map() {
            return BlogPostsPage.builder()
                    .items(Arrays.stream(posts)
                            .map(p -> new BlogPost((String) p.get("id"), (String) p.get("content")))
                            .collect(Collectors.toList()))
                    .build();
        }
    }
}
