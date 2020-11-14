package com.esgi.nova.ports.provided.dtos.choice_translation

import com.esgi.nova.ports.provided.dtos.ITranslation
import java.util.*

class ChoiceTranslationKey<T>(val choiceId: UUID, override var language: T): ITranslation<T> {
}