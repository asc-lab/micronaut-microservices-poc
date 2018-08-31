package documents.service.api;

import documents.service.api.queries.finddocuments.FindDocumentsQuery;
import documents.service.api.queries.finddocuments.FindDocumentsResult;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;

import javax.validation.constraints.NotNull;

public interface DocumentsOperations {

    @Post
    FindDocumentsResult find(@Body @NotNull FindDocumentsQuery query);

}
