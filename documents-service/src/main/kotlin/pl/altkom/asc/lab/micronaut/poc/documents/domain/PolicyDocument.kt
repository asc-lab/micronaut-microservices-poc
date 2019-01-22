package pl.altkom.asc.lab.micronaut.poc.documents.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
data class PolicyDocument(
        @Id
        @GeneratedValue
        val id: Long? = -1,
        val policyNumber: String = "",
        @Column(columnDefinition = "BINARY(200000)")
        val bytes: ByteArray = ByteArray(1)
)