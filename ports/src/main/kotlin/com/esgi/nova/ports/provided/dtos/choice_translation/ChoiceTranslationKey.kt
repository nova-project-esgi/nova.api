package com.esgi.nova.ports.provided.dtos.choice_translation

import java.util.*

class ChoiceTranslationKey<T>(val choiceId: UUID, val language: T) {
}