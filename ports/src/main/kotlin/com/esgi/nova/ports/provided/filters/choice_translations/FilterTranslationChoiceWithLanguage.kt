package com.esgi.nova.ports.provided.filters.choice_translations

import com.esgi.nova.ports.provided.dtos.ITranslation
import java.util.*

data class FilterTranslationChoiceWithLanguage(override var language: UUID, val choiceId: UUID): ITranslation<UUID> {
}