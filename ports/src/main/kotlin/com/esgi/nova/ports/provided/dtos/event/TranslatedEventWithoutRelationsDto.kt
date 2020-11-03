package com.esgi.nova.ports.provided.dtos.event

import com.esgi.nova.ports.provided.dtos.IId
import com.esgi.nova.ports.provided.dtos.event_translation.IEventTranslation
import com.esgi.nova.ports.provided.dtos.language.ILanguageCode
import java.util.*

class TranslatedEventWithoutRelationsDto(translatedEventDto: TranslatedEventDto) : IEventTranslation, ILanguageCode,
    IEvent, IId<UUID> {
    override lateinit var id: UUID
    override var isDaily: Boolean = false
    override var isActive: Boolean = false
    override val title: String
    override val description: String
    override val languageCode: String

    init {
        id = translatedEventDto.id
        isDaily = translatedEventDto.isDaily
        isActive = translatedEventDto.isActive
        title = translatedEventDto.title
        description = translatedEventDto.description
        languageCode = translatedEventDto.languageCode
    }
}