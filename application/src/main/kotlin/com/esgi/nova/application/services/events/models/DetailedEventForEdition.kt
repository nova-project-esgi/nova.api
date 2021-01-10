package com.esgi.nova.application.services.events.models

data class DetailedEventForEdition<Choice : DetailedChoiceForEdition>(
    val isDaily: Boolean,
    val isActive: Boolean,
    val choices: List<Choice>,
    val translations: List<EventTranslationForEdition>
) {
}

