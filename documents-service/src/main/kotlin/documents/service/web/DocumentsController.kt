package documents.service.web

import documents.service.api.DocumentsOperations
import documents.service.api.queries.finddocuments.FindDocumentsResult
import documents.service.api.queries.finddocuments.GeneratedDocument
import documents.service.domain.PolicyDocumentRepository
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.validation.Validated
import org.assertj.core.util.Lists

@Validated
@Controller("/documents")
class DocumentsController(private val policyDocumentRepository: PolicyDocumentRepository) : DocumentsOperations {

    override fun find(policyNumber: String): FindDocumentsResult {
        val findByPolicyNumber = policyDocumentRepository.findByPolicyNumber(policyNumber)
        val list = findByPolicyNumber.map { policyDocument -> GeneratedDocument(policyNumber,policyDocument.bytes) }
        return FindDocumentsResult(list)
    }
}
