package com.esgi.nova.application.uses_cases.events

data class DetailedEventForEdition(
    val choices: List<DetailedChoiceForEdition>,
    val translations: List<ChoiceTranslationForEdition>
) {
}