package com.esgi.nova.ports.provided.dtos.choice_translation

import com.esgi.nova.ports.provided.dtos.ITranslation
import java.util.*

open class ChoiceTranslationCmdDto<T>(
    override var title: String,
    override var description: String,
    var choiceId: UUID,
    override var language: T
) : IChoiceTranslation, ITranslation<T> {

}