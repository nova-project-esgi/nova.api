package com.esgi.nova.application.uses_cases.events

import java.util.*

data class DetailedChoiceForEdition(
    val translations: List<ChoiceTranslationForEdition>,
    val resources: List<ChoiceResourceForEdition>
)
