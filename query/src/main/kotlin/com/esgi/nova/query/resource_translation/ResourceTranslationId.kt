package com.esgi.nova.query.resource_translation

import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class ResourceTranslationId : Serializable {
    @Column(name = "resource_id")
    lateinit var resourceId: UUID

    @Column(name = "language_id")
    lateinit var languageId: UUID
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