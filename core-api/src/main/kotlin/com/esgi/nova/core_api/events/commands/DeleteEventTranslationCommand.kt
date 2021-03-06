package com.esgi.nova.core_api.events.commands

import com.esgi.nova.core_api.events.EventIdentifier
import com.esgi.nova.core_api.languages.LanguageIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeleteEventTranslationCommand(
    @TargetAggregateIdentifier val eventId: EventIdentifier,
    val translationId: LanguageIdentifier
)