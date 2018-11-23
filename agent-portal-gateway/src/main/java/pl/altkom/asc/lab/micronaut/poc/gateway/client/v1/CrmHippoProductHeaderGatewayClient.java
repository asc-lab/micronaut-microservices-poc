package pl.altkom.asc.lab.micronaut.poc.gateway.client.v1;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.reactivex.Maybe;
import pl.altkom.asc.lab.micronaut.poc.crm.service.api.v1.BlogPostsPage;
import pl.altkom.asc.lab.micronaut.poc.crm.service.api.v1.ProductHeader;
import pl.altkom.asc.lab.micronaut.poc.crm.service.api.v1.ProductHeaderOperations;

import javax.inject.Singleton;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class CrmHippoProductHeaderGatewayClient implements ProductHeaderOperations {
    private final RxHttpClient httpClient;

    public CrmHippoProductHeaderGatewayClient(CrmHippoConfig config) throws MalformedURLException {
        this.httpClient = RxHttpClient.create(new URL(config.getUrl()));
    }
    @Override
    public Maybe<List<ProductHeader>> productHeaders() {
        String path = "/site/api-manual/ProductHeader";
        HttpRequest<?> req = HttpRequest.GET(path);
        return httpClient
                .retrieve(req, Argument.of(Map.class))
                .map(doc -> CrmHippoProductHeaderGatewayClient.DocMapper.mapList(doc))
                .firstElement();
    }

    static class DocMapper {
        private Map map;

        public DocMapper(Map map) {
            this.map = map;
        }

        static List<ProductHeader> mapList(Map map){
            return ((List<Map>)map.get("items"))
                    .stream()
                    .map(m -> new DocMapper(m).map())
                    .collect(Collectors.toList());
        }

        ProductHeader map() {
            return ProductHeader.builder()
                    .code((String)map.get("code"))
                    .title((String)map.get("title"))
                    .shortDescription((String)map.get("shortDescription"))
                    .mainPictureUrl((String)((Map)map.get("mainPicture")).get("path"))
                    .build();
        }
    }
}
