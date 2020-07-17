package pl.altkom.asc.lab.micronaut.poc.documents.infrastructure.adapters.web

import io.micronaut.http.annotation.Controller
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import io.micronaut.validation.Validated
import pl.altkom.asc.lab.micronaut.poc.documents.api.DocumentsOperations
import pl.altkom.asc.lab.micronaut.poc.documents.api.queries.finddocuments.FindDocumentsResult
import pl.altkom.asc.lab.micronaut.poc.documents.api.queries.finddocuments.GeneratedDocument
import pl.altkom.asc.lab.micronaut.poc.documents.domain.PolicyDocumentRepository

@ExecuteOn(TaskExecutors.IO)
@Validated
@Controller("/documents")
class DocumentsController(private val policyDocumentRepository: PolicyDocumentRepository) : DocumentsOperations {

    override fun find(policyNumber: String): FindDocumentsResult {
        val findByPolicyNumber = policyDocumentRepository.findByPolicyNumber(policyNumber)
        val list = findByPolicyNumber.map { policyDocument -> GeneratedDocument(policyNumber, policyDocument.bytes) }
        return FindDocumentsResult(list)
    }
}
