package com.esgi.nova.query.event_translation

import org.hibernate.annotations.Type
import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class EventTranslationId(@Type(type = "uuid-char")
                         @Column(name = "event_id", columnDefinition = "uniqueidentifier")
                         var eventId: UUID,
                         @Type(type = "uuid-char")
                         @Column(name = "language_id", columnDefinition = "uniqueidentifier")
                         var languageId: UUID) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EventTranslationId

        if (eventId != other.eventId) return false
        if (languageId != other.languageId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = eventId.hashCode()
        result = 31 * result + languageId.hashCode()
        return result
    }
}