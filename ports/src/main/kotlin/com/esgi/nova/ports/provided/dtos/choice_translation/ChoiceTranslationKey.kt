package com.esgi.nova.ports.provided.dtos.choice_translation

import com.esgi.nova.ports.provided.dtos.ITranslationEntityKey
import java.util.*

class ChoiceTranslationKey<T>(override val entityId: UUID, override var language: T): ITranslationEntityKey<UUID, T>{
}