package pl.altkom.asc.lab.micronaut.poc.documents.infrastructure.adapters.db

import pl.altkom.asc.lab.micronaut.poc.documents.infrastructure.annotations.RequiresJdbc
import pl.altkom.asc.lab.micronaut.poc.documents.domain.PolicyDocument
import pl.altkom.asc.lab.micronaut.poc.documents.domain.PolicyDocumentRepository
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession
import io.micronaut.spring.tx.annotation.Transactional
import javax.inject.Singleton
import javax.persistence.EntityManager
import kotlin.streams.toList

@Singleton
@RequiresJdbc
open class PolicyDocumentDb(
        @CurrentSession
        private val entityManager: EntityManager
) : PolicyDocumentRepository {


    @Transactional
    override fun add(document: PolicyDocument) {
        entityManager.persist(document)
    }

    @Transactional
    override fun findByPolicyNumber(policyNumber: String): List<PolicyDocument> {
        return entityManager
                .createQuery<PolicyDocument>("from PolicyDocument t where t.policyNumber= :policyNumber", PolicyDocument::class.java)
                .setParameter("policyNumber", policyNumber)
                .resultStream
                .toList()

    }

}