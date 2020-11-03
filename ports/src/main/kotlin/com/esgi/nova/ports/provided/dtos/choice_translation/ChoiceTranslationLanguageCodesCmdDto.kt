package com.esgi.nova.ports.provided.dtos.choice_translation

import java.util.*

class ChoiceTranslationLanguageCodesCmdDto(
    title: String,
    description: String,
    choiceId: UUID,
    val languageCodes: String
) : ChoiceTranslationCmdDto(
    title,
    description, choiceId,
) {
}