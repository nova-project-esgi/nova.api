package com.esgi.nova.ports.provided.dtos.choice.translated_choice_detailed

import com.esgi.nova.ports.provided.dtos.IId
import com.esgi.nova.ports.provided.dtos.choice_resource.IChoiceResource
import com.esgi.nova.ports.provided.dtos.language.ILanguageCode
import com.esgi.nova.ports.provided.dtos.resource.TranslatedResourceDto
import com.esgi.nova.ports.provided.dtos.resource_translation.IResourceTranslation
import java.util.*

class TranslatedResourceDetailedDto(translatedResource: TranslatedResourceDto) : IResourceTranslation, ILanguageCode,
    IChoiceResource,
    IId<UUID> {
    override lateinit var id: UUID
    override lateinit var name: String
    override val languageCode: String
    override var changeValue: Int = 0

    init {
        id = translatedResource.id
        name = translatedResource.name
        languageCode = translatedResource.languageCode
    }
}