package documents.service.api.queries.finddocuments

import documents.service.api.queries.finddocuments.dto.GeneratedDocument

data class FindDocumentsResult(val documents: List<GeneratedDocument>)