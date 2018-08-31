package documents.service.domain

public interface PolicyDocumentRepository {
    fun add(document: PolicyDocument)
    fun findByPolicyNumber(policyNumber: String): List<PolicyDocument>
}