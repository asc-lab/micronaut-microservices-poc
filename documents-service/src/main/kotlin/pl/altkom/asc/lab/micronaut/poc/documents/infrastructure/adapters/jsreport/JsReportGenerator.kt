package pl.altkom.asc.lab.micronaut.poc.documents.infrastructure.adapters.jsreport

import io.micronaut.context.annotation.Value
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.HttpClient
import pl.altkom.asc.lab.micronaut.poc.documents.domain.ReportGenerator
import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.PolicyRegisteredEvent
import java.net.URL
import javax.inject.Singleton

@Singleton
class JsReportGenerator : ReportGenerator {

    private val uri: String = "/api/report"

    @Value("\${jsreport.host}")
    private lateinit var host: String

    @Value("\${jsreport.port}")
    private lateinit  var port: String

    override fun generate(event: PolicyRegisteredEvent): HttpResponse<ByteArray>? {
        val reportRequest = ReportRequest(
                Template("POLICY"),
                TemplateOptions(),
                event.policy
        )

        val create = HttpClient.create(URL("http", host, port.toInt(), ""))
        val request = HttpRequest.POST(uri, reportRequest).header(HttpHeaders.CONTENT_TYPE, "application/json")
        return create.toBlocking().exchange(request, ByteArray::class.java)
    }
}