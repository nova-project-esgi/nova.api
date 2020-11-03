package com.esgi.nova.ports.provided.dtos.resource.translated_resource_detailed

import com.esgi.nova.ports.provided.dtos.IId
import com.esgi.nova.ports.provided.dtos.event.TranslatedEventWithoutRelationsDto
import com.esgi.nova.ports.provided.dtos.language.ILanguageCode
import com.esgi.nova.ports.provided.dtos.resource.TranslatedResourceDto
import com.esgi.nova.ports.provided.dtos.resource_translation.IResourceTranslation
import java.util.*

class TranslatedResourceDetailedDto(translatedResource: TranslatedResourceDto) : IResourceTranslation, ILanguageCode,
    IId<UUID> {
    override lateinit var id: UUID
    val choices: List<TranslatedChoiceDetailedDto>
    override lateinit var name: String
    override val languageCode: String

    init {
        id = translatedResource.id
        name = translatedResource.name
        languageCode = translatedResource.languageCode
        choices = translatedResource.choices.filter { choice -> choice.event != null }
            .map { choice -> TranslatedChoiceDetailedDto(choice, TranslatedEventWithoutRelationsDto(choice.event!!)) }

    }
}