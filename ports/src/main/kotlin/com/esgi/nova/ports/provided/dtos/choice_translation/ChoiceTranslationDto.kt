package com.esgi.nova.ports.provided.dtos.choice_translation

import com.esgi.nova.ports.common.extensions.toAggregatedCode
import com.esgi.nova.ports.provided.dtos.IId
import com.esgi.nova.ports.provided.dtos.ITranslation
import com.esgi.nova.ports.provided.dtos.choice.queries.ChoiceDto
import com.esgi.nova.ports.provided.dtos.choice.queries.TranslatedChoiceDto
import com.esgi.nova.ports.provided.dtos.event.EventDto
import com.esgi.nova.ports.provided.dtos.event.TranslatedEventDto
import com.esgi.nova.ports.provided.dtos.language.LanguageDto
import com.esgi.nova.ports.provided.dtos.resource.ResourceDto
import com.esgi.nova.ports.provided.dtos.resource.TranslatedResourceDto
import java.util.*

class ChoiceTranslationDto(
    override var id: UUID,
    override var title: String,
    override var description: String,
    var choice: ChoiceDto,
    override var language: LanguageDto
) : IChoiceTranslation, IId<UUID>, ITranslation<LanguageDto> {
    fun toTranslatedChoiceDto(
        translatedEvent: TranslatedEventDto?,
        translatedResources: List<TranslatedResourceDto>
    ) =
        TranslatedChoiceDto(
            choice.id,
            title,
            description,
            translatedResources,
            translatedEvent,
            language.toAggregatedCode()
        )


}