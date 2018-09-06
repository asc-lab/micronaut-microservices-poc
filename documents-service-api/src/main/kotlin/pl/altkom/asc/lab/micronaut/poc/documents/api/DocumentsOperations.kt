package pl.altkom.asc.lab.micronaut.poc.documents.api

import pl.altkom.asc.lab.micronaut.poc.documents.api.queries.finddocuments.FindDocumentsResult
import io.micronaut.http.annotation.Get
import org.jetbrains.annotations.NotNull

interface DocumentsOperations {

    @Get("/{policyNumber}")
    fun find(@NotNull policyNumber: String): FindDocumentsResult

}
