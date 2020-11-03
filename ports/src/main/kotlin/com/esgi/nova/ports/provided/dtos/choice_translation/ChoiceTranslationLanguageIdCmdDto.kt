package com.esgi.nova.ports.provided.dtos.choice_translation

import java.util.*

class ChoiceTranslationLanguageIdCmdDto(title: String, description: String, choiceId: UUID, val languageId: UUID) :
    ChoiceTranslationCmdDto(
        title,
        description, choiceId
    ) {
}