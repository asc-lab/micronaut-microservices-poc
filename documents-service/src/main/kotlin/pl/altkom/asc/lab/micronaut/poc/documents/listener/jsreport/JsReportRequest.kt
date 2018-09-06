package pl.altkom.asc.lab.micronaut.poc.documents.listener.jsreport

import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.dto.PolicyDto

data class JsReportRequest(
        val template: Template,
        val options: TemplateOptions,
        val data: PolicyDto
)