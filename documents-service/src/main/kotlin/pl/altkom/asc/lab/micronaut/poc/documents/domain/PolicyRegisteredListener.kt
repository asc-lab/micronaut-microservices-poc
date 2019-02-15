package pl.altkom.asc.lab.micronaut.poc.documents.domain

import io.micronaut.configuration.rabbitmq.annotation.Queue
import io.micronaut.configuration.rabbitmq.annotation.RabbitListener
import io.micronaut.http.annotation.Header
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.PolicyRegisteredEvent

@RabbitListener
@Header(name = "Content-Type", value = "application/json")
class PolicyRegisteredListener(private val policyDocumentRepository: PolicyDocumentRepository,
                               private val reportGenerator: ReportGenerator) {

    @Queue("policy-registered")
    fun onPolicyRegistered(event: PolicyRegisteredEvent) {
        val generatedDocument = reportGenerator.generate(event)

        val policyDocument = PolicyDocument(
                id = null,
                policyNumber = event.policy.number,
                bytes = generatedDocument?.body()!!
        )

        policyDocumentRepository.add(policyDocument)
    }
}
