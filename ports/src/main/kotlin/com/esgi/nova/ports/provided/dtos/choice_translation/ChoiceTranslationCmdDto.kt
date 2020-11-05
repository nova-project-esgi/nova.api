package com.esgi.nova.ports.provided.dtos.choice_translation

import java.util.*

open class ChoiceTranslationCmdDto<T>(
    override var title: String,
    override var description: String,
    var choiceId: UUID,
    var language: T
) : IChoiceTranslation {

}