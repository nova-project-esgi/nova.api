package com.esgi.nova.query.event

import com.esgi.nova.core_api.events.views.DetailedEventView
import com.esgi.nova.query.choice.Choice
import com.esgi.nova.query.event_translation.EventTranslation
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "event")
class Event(
    @Id
    @Type(type = "uuid-char")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    var id: UUID, @Column(name = "is_daily") var isDaily: Boolean = false,
    @Column(name = "is_active") var isActive: Boolean = false
) {

    @OneToMany(mappedBy = "event", cascade = [CascadeType.ALL])
    var choices: MutableList<Choice> = mutableListOf()

    @OneToMany(mappedBy = "event", cascade = [CascadeType.ALL])
    var eventTranslations: MutableList<EventTranslation> = mutableListOf()

    fun toDetailedEvent() = DetailedEventView(
        id = id,
        translations = eventTranslations.map { it.toEventTranslationView() },
        choices = choices.map { it.toDetailedChoiceView() }
    )
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Event

        if (id != other.id) return false
        if (isDaily != other.isDaily) return false
        if (isActive != other.isActive) return false
        if (choices != other.choices) return false
        if (eventTranslations != other.eventTranslations) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + isDaily.hashCode()
        result = 31 * result + isActive.hashCode()
        result = 31 * result + choices.hashCode()
        result = 31 * result + eventTranslations.hashCode()
        return result
    }


}