package com.esgi.nova.ports.provided.dtos.resource_translation

import com.esgi.nova.ports.common.extensions.toAggregatedCode
import com.esgi.nova.ports.provided.dtos.IId
import com.esgi.nova.ports.provided.dtos.choice.TranslatedChoiceDto
import com.esgi.nova.ports.provided.dtos.language.LanguageDto
import com.esgi.nova.ports.provided.dtos.resource.ResourceDto
import com.esgi.nova.ports.provided.dtos.resource.TranslatedResourceDto
import java.util.*

class ResourceTranslationDto(
    override var id: UUID,
    override var name: String,
    var resource: ResourceDto,
    var language: LanguageDto
) : IResourceTranslation, IId<UUID> {
    fun toTranslatedResourceDto(translatedChoices: List<TranslatedChoiceDto>) =
        TranslatedResourceDto(resource.id, translatedChoices, name, language.toAggregatedCode())
}