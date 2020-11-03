package com.esgi.nova.ports.provided.dtos.resource

import com.esgi.nova.ports.provided.dtos.IId
import com.esgi.nova.ports.provided.dtos.choice.TranslatedChoiceDto
import com.esgi.nova.ports.provided.dtos.language.ILanguageCode
import com.esgi.nova.ports.provided.dtos.resource_translation.IResourceTranslation
import java.util.*

class TranslatedResourceDto(
    override var id: UUID,
    var choices: List<TranslatedChoiceDto>,
    override var name: String,
    override var languageCode: String
) : IResourceTranslation, ILanguageCode, IId<UUID> {
}
