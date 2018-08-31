package pl.altkom.asc.lab.micronaut.poc.gateway;

import documents.service.api.queries.finddocuments.FindDocumentsQuery;
import documents.service.api.queries.finddocuments.FindDocumentsResult;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.Secured;
import pl.altkom.asc.lab.micronaut.poc.gateway.client.v1.DocumentsGatewayClient;

import javax.inject.Inject;

@Secured("isAuthenticated()")
@Controller("/api/documents")
public class DocumentsGatewayController {

    @Inject
    private DocumentsGatewayClient client;

    @Get
    FindDocumentsResult find(FindDocumentsQuery cmd) {
        return client.find(cmd);
    }

}

