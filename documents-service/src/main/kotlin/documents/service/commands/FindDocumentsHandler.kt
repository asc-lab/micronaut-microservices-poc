package documents.service.commands

import documents.service.api.queries.finddocuments.FindDocumentsQuery
import documents.service.api.queries.finddocuments.FindDocumentsResult
import documents.service.api.queries.finddocuments.dto.GeneratedDocument
import documents.service.domain.PolicyDocumentRepository
import pl.altkom.asc.lab.micronaut.poc.command.bus.QueryHandler
import javax.inject.Singleton

@Singleton
class FindDocumentsHandler(private val policyDocumentRepository: PolicyDocumentRepository)
    : QueryHandler<FindDocumentsResult, FindDocumentsQuery> {

    override fun handle(command: FindDocumentsQuery): FindDocumentsResult {
        val findByPolicyNumber = policyDocumentRepository.findByPolicyNumber(command.policyNumber)
        val list = findByPolicyNumber.map { policyDocument -> GeneratedDocument("", policyDocument.bytes) }
        return FindDocumentsResult(list)
    }
}
