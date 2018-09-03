package documents.service.infrastructure.db

import documents.service.annotations.RequiresJdbc
import documents.service.domain.PolicyDocument
import documents.service.domain.PolicyDocumentRepository
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession
import io.micronaut.spring.tx.annotation.Transactional
import javax.inject.Singleton
import javax.persistence.EntityManager
import kotlin.streams.toList

@Singleton
@RequiresJdbc
open class PolicyDocymentDb(
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