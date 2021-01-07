package com.esgi.nova.query.choice

import com.esgi.nova.core_api.choices.views.ChoiceView
import com.esgi.nova.core_api.choices.views.DetailedChoiceView
import com.esgi.nova.core_api.choices.views.TranslatedChoiceView
import com.esgi.nova.query.choice_resource.ChoiceResource
import com.esgi.nova.query.choice_translation.ChoiceTranslation
import com.esgi.nova.query.event.Event
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "choice")
class Choice(
    @Id
    @Type(type = "uuid-char")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    var id: UUID,

    @JoinColumn(name = "event_id", columnDefinition = "uniqueidentifier", nullable = false)
    @ManyToOne
    var event: Event
) {

    @OneToMany(mappedBy = "choice", cascade = [CascadeType.ALL])
    var choiceResources: MutableList<ChoiceResource> = mutableListOf()

    @OneToMany(mappedBy = "choice", cascade = [CascadeType.ALL])
    var choiceTranslations: MutableList<ChoiceTranslation> = mutableListOf()

    fun toChoiceView() = ChoiceView(
        id = id,
        eventId = event.id,
        resourceIds = choiceResources.map { it.resource.id }
    )

    fun toDetailedChoiceView() = DetailedChoiceView(
        id = id,
        eventId = event.id,
        translations = choiceTranslations.map { it.toChoiceTranslationView() },
        resources = choiceResources.map { it.toChoiceResourceView() }
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Choice

        if (id != other.id) return false
        if (event != other.event) return false
        if (choiceResources != other.choiceResources) return false
        if (choiceTranslations != other.choiceTranslations) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + event.hashCode()
        result = 31 * result + choiceResources.hashCode()
        result = 31 * result + choiceTranslations.hashCode()
        return result
    }

    fun toTranslatedChoiceView(language: String): TranslatedChoiceView {
        val translation = choiceTranslations.firstOrNull { t -> t.language.concatenatedCodes == language }
            ?: choiceTranslations.first { t -> t.language.isDefault }
        return TranslatedChoiceView(
            id = id,
            language = translation.language.code,
            title = translation.title,
            description = translation.description,
            eventId = event.id,
            resources = choiceResources.map { r -> r.toTranslatedResourceView(language) }
        )
    }


}