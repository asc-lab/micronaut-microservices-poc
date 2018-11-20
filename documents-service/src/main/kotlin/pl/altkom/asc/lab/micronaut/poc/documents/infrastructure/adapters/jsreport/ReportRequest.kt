package pl.altkom.asc.lab.micronaut.poc.documents.infrastructure.adapters.jsreport

import pl.altkom.asc.lab.micronaut.poc.policy.service.api.v1.events.dto.PolicyDto

data class ReportRequest(
        val template: Template,
        val options: TemplateOptions,
        val data: PolicyDto
)