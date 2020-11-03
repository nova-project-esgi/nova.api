package com.esgi.nova.ports.provided.dtos.event.translated_event_detailed

import com.esgi.nova.ports.provided.dtos.IId
import com.esgi.nova.ports.provided.dtos.event.IEvent
import com.esgi.nova.ports.provided.dtos.event.TranslatedEventDto
import com.esgi.nova.ports.provided.dtos.event_translation.IEventTranslation
import com.esgi.nova.ports.provided.dtos.game.GameDto
import com.esgi.nova.ports.provided.dtos.language.ILanguageCode
import java.util.*

class TranslatedEventDetailedDto(translatedEvent: TranslatedEventDto) : IEventTranslation, ILanguageCode, IEvent,
    IId<UUID> {

    override var id: UUID = translatedEvent.id
    override var isDaily: Boolean = false
    override var isActive: Boolean = false
    override val title: String
    override val description: String
    override val languageCode: String
    var choices: List<TranslatedChoiceDetailedDto>

    init {
        isDaily = translatedEvent.isDaily
        isActive = translatedEvent.isActive
        title = translatedEvent.title
        description = translatedEvent.description
        languageCode = translatedEvent.languageCode
        choices = translatedEvent.choices.map { choice -> TranslatedChoiceDetailedDto(choice) }
    }
}