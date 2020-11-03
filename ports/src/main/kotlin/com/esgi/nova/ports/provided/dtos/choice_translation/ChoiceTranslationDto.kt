package com.esgi.nova.ports.provided.dtos.choice_translation

import com.esgi.nova.ports.common.extensions.toAggregatedCode
import com.esgi.nova.ports.provided.dtos.IId
import com.esgi.nova.ports.provided.dtos.choice.ChoiceDto
import com.esgi.nova.ports.provided.dtos.choice.TranslatedChoiceDto
import com.esgi.nova.ports.provided.dtos.event.TranslatedEventDto
import com.esgi.nova.ports.provided.dtos.language.LanguageDto
import com.esgi.nova.ports.provided.dtos.resource.TranslatedResourceDto
import java.util.*

class ChoiceTranslationDto(
    override var id: UUID,
    override var title: String,
    override var description: String,
    var choice: ChoiceDto,
    var language: LanguageDto
) : IChoiceTranslation, IId<UUID> {
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