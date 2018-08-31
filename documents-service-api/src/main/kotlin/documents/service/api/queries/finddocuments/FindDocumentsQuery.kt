package documents.service.api.queries.finddocuments

import pl.altkom.asc.lab.micronaut.poc.command.bus.api.Query

data class FindDocumentsQuery(val policyNumber: String) : Query<FindDocumentsResult>