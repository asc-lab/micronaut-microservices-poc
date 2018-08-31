package documents.service.api.queries.finddocuments.dto

import java.util.*

data class GeneratedDocument(val name: String, val content: ByteArray) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GeneratedDocument

        if (name != other.name) return false
        if (!Arrays.equals(content, other.content)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + Arrays.hashCode(content)
        return result
    }
}