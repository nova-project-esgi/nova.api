package com.esgi.nova.ports.provided.dtos.resource.translated_resource_detailed

import com.esgi.nova.ports.provided.dtos.IId
import com.esgi.nova.ports.provided.dtos.choice.TranslatedChoiceDto
import com.esgi.nova.ports.provided.dtos.choice_resource.IChoiceResource
import com.esgi.nova.ports.provided.dtos.choice_translation.IChoiceTranslation
import com.esgi.nova.ports.provided.dtos.event.TranslatedEventWithoutRelationsDto
import com.esgi.nova.ports.provided.dtos.language.ILanguageCode
import java.util.*

class TranslatedChoiceDetailedDto(translatedChoiceDto: TranslatedChoiceDto, val event: TranslatedEventWithoutRelationsDto) : IChoiceTranslation, ILanguageCode,
    IChoiceResource, IId<UUID> {
    override lateinit var id: UUID
    override lateinit var title: String
    override lateinit var description: String
    override val languageCode: String
    override var changeValue: Int = 0

    init {
        id = translatedChoiceDto.id
        title = translatedChoiceDto.title
        description = translatedChoiceDto.description
        languageCode = translatedChoiceDto.languageCode
    }
}