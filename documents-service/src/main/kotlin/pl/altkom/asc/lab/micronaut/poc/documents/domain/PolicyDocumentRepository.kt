package pl.altkom.asc.lab.micronaut.poc.documents.domain

interface PolicyDocumentRepository {
    fun add(document: PolicyDocument)
    fun findByPolicyNumber(policyNumber: String): List<PolicyDocument>
}