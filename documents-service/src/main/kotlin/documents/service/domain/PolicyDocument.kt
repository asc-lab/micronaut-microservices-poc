package documents.service.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
data class PolicyDocument(
        @Id
        @GeneratedValue
        val id: Long?,
        val policyNumber: String,
        @Column(columnDefinition = "BINARY(20000)")
        val bytes: ByteArray


)