package pl.altkom.asc.lab.micronaut.poc.documents.web

import pl.altkom.asc.lab.micronaut.poc.documents.api.DocumentsOperations
import pl.altkom.asc.lab.micronaut.poc.documents.api.queries.finddocuments.FindDocumentsResult
import pl.altkom.asc.lab.micronaut.poc.documents.api.queries.finddocuments.GeneratedDocument
import pl.altkom.asc.lab.micronaut.poc.documents.domain.PolicyDocumentRepository
import io.micronaut.http.annotation.Controller
import io.micronaut.validation.Validated

@Validated
@Controller("/documents")
class DocumentsController(private val policyDocumentRepository: PolicyDocumentRepository) : DocumentsOperations {

    override fun find(policyNumber: String): FindDocumentsResult {
        val findByPolicyNumber = policyDocumentRepository.findByPolicyNumber(policyNumber)
        val list = findByPolicyNumber.map { policyDocument -> GeneratedDocument(policyNumber, policyDocument.bytes) }
        return FindDocumentsResult(list)
    }
}
