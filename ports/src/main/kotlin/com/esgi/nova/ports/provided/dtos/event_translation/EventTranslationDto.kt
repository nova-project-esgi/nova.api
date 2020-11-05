package com.esgi.nova.ports.provided.dtos.event_translation

import com.esgi.nova.ports.common.extensions.toAggregatedCode
import com.esgi.nova.ports.provided.dtos.IId
import com.esgi.nova.ports.provided.dtos.choice.queries.TranslatedChoiceDto
import com.esgi.nova.ports.provided.dtos.event.EventDto
import com.esgi.nova.ports.provided.dtos.event.TranslatedEventDto
import com.esgi.nova.ports.provided.dtos.language.LanguageDto
import java.util.*

class EventTranslationDto(
    override var id: UUID,
    override var title: String,
    override var description: String,
    var language: LanguageDto,
    var event: EventDto
) : IEventTranslation, IId<UUID> {
    fun toTranslatedEventDto(translatedChoices: List<TranslatedChoiceDto>? = null) =
        TranslatedEventDto(
            id = event.id,
            isDaily = event.isDaily,
            isActive = event.isActive,
            title = title,
            description = description,
            games = event.games,
            languageCode = language.toAggregatedCode(),
            choices = translatedChoices ?: listOf()
        )
}