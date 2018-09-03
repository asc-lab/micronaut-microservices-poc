package documents.service.api

import documents.service.api.queries.finddocuments.FindDocumentsResult
import io.micronaut.http.annotation.Get
import org.jetbrains.annotations.NotNull

interface DocumentsOperations {

    @Get("/{policyNumber}")
    fun find(@NotNull policyNumber: String): FindDocumentsResult

}
