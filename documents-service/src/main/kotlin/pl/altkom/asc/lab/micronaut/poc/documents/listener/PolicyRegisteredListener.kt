package pl.altkom.asc.lab.micronaut.poc.documents.listener

import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset
import io.micronaut.configuration.kafka.annotation.Topic
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Header
import io.micronaut.http.client.HttpClient
import pl.altkom.asc.lab.micronaut.poc.documents.domain.PolicyDocument
import pl.altkom.asc.lab.micronaut.poc.documents.domain.PolicyDocumentRepository
import pl.altkom.asc.lab.micronaut.poc.documents.listener.jsreport.JsReportRequest
import pl.altkom.asc.lab.micronaut.poc.documents.listener.jsreport.Template
import pl.altkom.asc.lab.micronaut.poc.documents.listener.jsreport.TemplateOptions
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.PolicyRegisteredEvent
import java.net.URL

@KafkaListener(clientId = "policy-registered-listener", offsetReset = OffsetReset.EARLIEST)
@Header(name = "Content-Type", value = "application/json")
class PolicyRegisteredListener(private val policyDocumentRepository: PolicyDocumentRepository) {

    private val uri: String = "/api/report"

    @Topic("policy-registered")
    fun onPolicyRegistered(event: PolicyRegisteredEvent) {
        val generatedDocument = generate(JsReportRequest(
                Template("POLICY"),
                TemplateOptions(),
                event.policy
        ))

        val policyDocument = PolicyDocument(
                id = null,
                policyNumber = event.policy.number,
                bytes = generatedDocument?.body()!!
        )

        policyDocumentRepository.add(policyDocument)
    }

    private fun generate(jsReportRequest: JsReportRequest): HttpResponse<ByteArray>? {
        val create = HttpClient.create(URL("http", "localhost", 5488, ""))
        val request = HttpRequest.POST(uri, jsReportRequest).header(HttpHeaders.CONTENT_TYPE, "application/json")
        return create.toBlocking().exchange(request, ByteArray::class.java)
    }

}
