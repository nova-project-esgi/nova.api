package com.esgi.nova.ports.provided.dtos.choice.commands

import com.esgi.nova.ports.provided.dtos.choice_translation.IChoiceTranslation
import java.util.*

class TranslatedChoiceCmdDto(
    override var title: String,
    override var description: String,
    eventId: UUID
) : ChoiceCmdDto(eventId), IChoiceTranslation {
}