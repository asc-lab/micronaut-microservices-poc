package documents.service.web

import documents.service.api.DocumentsOperations
import documents.service.api.queries.finddocuments.FindDocumentsQuery
import documents.service.api.queries.finddocuments.FindDocumentsResult
import io.micronaut.http.annotation.Controller
import io.micronaut.validation.Validated
import lombok.RequiredArgsConstructor
import pl.altkom.asc.lab.micronaut.poc.command.bus.CommandBus

@RequiredArgsConstructor
@Validated
@Controller("/policies")
class DocumentsController(private val bus: CommandBus) : DocumentsOperations {

    override fun find(query: FindDocumentsQuery?): FindDocumentsResult {
        return bus.executeQuery(query)
    }


}
