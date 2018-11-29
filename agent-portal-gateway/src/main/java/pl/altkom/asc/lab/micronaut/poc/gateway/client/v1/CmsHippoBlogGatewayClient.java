package pl.altkom.asc.lab.micronaut.poc.gateway.client.v1;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.uri.UriTemplate;
import io.reactivex.Maybe;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Singleton;

import pl.altkom.asc.lab.micronaut.poc.cms.service.api.v1.*;

@Singleton
public class CmsHippoBlogGatewayClient implements BlogOperations {
    private final RxHttpClient httpClient;

    public CmsHippoBlogGatewayClient(CmsHippoConfig config) throws MalformedURLException {
        this.httpClient = RxHttpClient.create(new URL(config.getUrl()));
    }
    
    @Override
    public Maybe<BlogPostsPage> getBlogPosts(BlogPostsPageRequest pageRequest) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("offset", pageRequest.getPageNumber() * pageRequest.getPageSize());
        params.put("max", pageRequest.getPageSize());
        if (pageRequest.getSearchPhrase()!=null) {
            params.put("query",pageRequest.getSearchPhrase());
        }
        String path = "/site/api/documents?_nodetype=minicms:blogpost&_offset={offset}&_max={max}&_query={query}";
        String uri = UriTemplate.of(path).expand(params);
        HttpRequest<?> req = HttpRequest.GET(uri);  
        return httpClient.retrieve(req, Argument.of(BlogPostsPage.class)).firstElement();
    }

    @Override
    public Maybe<BlogPostDetails> getBlogPost(String postId) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("uuid",postId);
        String path = "/site/api/documents/{uuid}";
        String uri = UriTemplate.of(path).expand(params);
        HttpRequest<?> req = HttpRequest.GET(uri);  
        
        return httpClient
                .retrieve(req, Argument.of(Map.class))
                .map(doc -> DocMapper.from(doc).map())
                .firstElement();
    }
    
    static class DocMapper {
        private Map map;

        public DocMapper(Map map) {
            this.map = map;
        }
        
        static DocMapper from(Map map){
            return new DocMapper(map);
        }
        
        BlogPostDetails map(){
            return BlogPostDetails.builder()
                    .id((String)map.get("id"))
                    .name((String)map.get("name"))
                    .title((String)items().get("minicms:title"))
                    .publicationDate((String)items().get("minicms:publicationdate"))
                    .categories((ArrayList)items().get("minicms:categories"))
                    .introduction((String)items().get("minicms:introduction"))
                    .htmlContent((String)content().get("content"))
                    .links(contentLinks())
                    .build();
        }
        
       private Map items() {
           return (Map)map.get("items");
       }
       
       private Map content() {
           return (Map)items().get("minicms:content");
       }
       
       private List<CmsLink> contentLinks(){
           List<CmsLink> links = new ArrayList<>();
           if (!content().containsKey("links"))
               return links;
           
           Map linksMap = (Map)content().get("links");
           linksMap.forEach((k,v)->{
               CmsLink l = new CmsLink((String)k,(String)((Map)v).get("url"));
               links.add(l);
           });
           
           return links;
       }
    }
}
