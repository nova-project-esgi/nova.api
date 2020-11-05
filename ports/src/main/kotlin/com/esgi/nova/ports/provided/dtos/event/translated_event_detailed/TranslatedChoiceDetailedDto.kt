package com.esgi.nova.ports.provided.dtos.event.translated_event_detailed

import com.esgi.nova.ports.provided.dtos.IId
import com.esgi.nova.ports.provided.dtos.choice.queries.TranslatedChoiceDto
import com.esgi.nova.ports.provided.dtos.choice.queries.translated_choice_detailed.TranslatedResourceDetailedDto
import com.esgi.nova.ports.provided.dtos.choice_translation.IChoiceTranslation
import com.esgi.nova.ports.provided.dtos.language.ILanguageCode
import java.util.*

open class TranslatedChoiceDetailedDto(translatedChoice: TranslatedChoiceDto) : IChoiceTranslation, ILanguageCode,
    IId<UUID> {
    override var id: UUID = translatedChoice.id
    override var title: String = translatedChoice.title
    override var description: String = translatedChoice.description
    val resources: List<TranslatedResourceDetailedDto>
    final override val languageCode: String

    init {
        resources = translatedChoice.resources.map { resource -> TranslatedResourceDetailedDto(resource) }
        languageCode = translatedChoice.languageCode
    }
}