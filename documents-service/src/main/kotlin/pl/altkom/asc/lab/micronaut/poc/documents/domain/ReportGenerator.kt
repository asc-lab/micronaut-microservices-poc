package pl.altkom.asc.lab.micronaut.poc.documents.domain

import io.micronaut.http.HttpResponse
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.PolicyRegisteredEvent

interface ReportGenerator {
    fun generate(event: PolicyRegisteredEvent): HttpResponse<ByteArray>?
}