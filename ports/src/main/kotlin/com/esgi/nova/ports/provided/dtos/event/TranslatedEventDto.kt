package com.esgi.nova.ports.provided.dtos.event

import com.esgi.nova.ports.provided.dtos.IId
import com.esgi.nova.ports.provided.dtos.choice.queries.TranslatedChoiceDto
import com.esgi.nova.ports.provided.dtos.event_translation.IEventTranslation
import com.esgi.nova.ports.provided.dtos.game.GameDto
import com.esgi.nova.ports.provided.dtos.language.ILanguageCode
import java.util.*

class TranslatedEventDto(
    override var id: UUID,
    override var isDaily: Boolean,
    override var isActive: Boolean,
    override var title: String,
    override var description: String,
    var games: List<GameDto>,
    override val languageCode: String,
    var choices: List<TranslatedChoiceDto>
) : IId<UUID>, IEvent, IEventTranslation, ILanguageCode {
}