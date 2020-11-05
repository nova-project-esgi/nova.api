package com.esgi.nova.ports.provided.dtos.choice.queries

import com.esgi.nova.ports.provided.dtos.IId
import com.esgi.nova.ports.provided.dtos.choice_translation.IChoiceTranslation
import com.esgi.nova.ports.provided.dtos.event.TranslatedEventDto
import com.esgi.nova.ports.provided.dtos.language.ILanguageCode
import com.esgi.nova.ports.provided.dtos.resource.TranslatedResourceDto
import java.util.*

class TranslatedChoiceDto(
    override var id: UUID,
    override var title: String,
    override var description: String,
    var resources: List<TranslatedResourceDto>,
    var event: TranslatedEventDto?,
    override var languageCode: String,
) : IId<UUID>, IChoiceTranslation, ILanguageCode {
}