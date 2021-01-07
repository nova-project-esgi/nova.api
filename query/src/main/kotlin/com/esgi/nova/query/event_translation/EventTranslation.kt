package com.esgi.nova.query.event_translation

import com.esgi.nova.common.StringLength
import com.esgi.nova.core_api.events.views.EventTranslationTitleView
import com.esgi.nova.core_api.events.views.EventTranslationView
import com.esgi.nova.query.event.Event
import com.esgi.nova.query.language.Language
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "event_translation")
class EventTranslation(
    @EmbeddedId
    var id: EventTranslationId, @Column(length = StringLength.LONG_STRING)
    var title: String, @Column
    var description: String, @ManyToOne
    @Type(type = "uuid-char")
    @JoinColumn(name = "language_id", columnDefinition = "uniqueidentifier")
    @MapsId("languageId")
    var language: Language, @ManyToOne
    @Type(type = "uuid-char")
    @JoinColumn(name = "event_id", columnDefinition = "uniqueidentifier")
    @MapsId("eventId")
    var event: Event
) {


    fun toEventTranslationTitleView() = EventTranslationTitleView(
        eventId = id.eventId,
        languageId = id.languageId,
        title = title
    )

    fun toEventTranslationView() = EventTranslationView(
        eventId = event.id,
        title = title,
        description = description,
        language = language.toLanguageView()
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EventTranslation

        if (id != other.id) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (event != other.event) return false
        if (language != other.language) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + event.hashCode()
        result = 31 * result + language.hashCode()
        return result
    }


}