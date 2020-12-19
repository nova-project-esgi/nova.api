package com.esgi.nova.query.resource_translation

import org.hibernate.annotations.Type
import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class ResourceTranslationId(
    @Type(type = "uuid-char")
    @Column(name = "resource_id")
    var resourceId: UUID,
    @Type(type = "uuid-char")
    @Column(name = "language_id")
    var languageId: UUID
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResourceTranslationId

        if (resourceId != other.resourceId) return false
        if (languageId != other.languageId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = resourceId.hashCode()
        result = 31 * result + languageId.hashCode()
        return result
    }
}