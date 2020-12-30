package com.esgi.nova.core_api.event_translations.commands

import com.esgi.nova.core_api.events.commands.EventIdentifier
import com.esgi.nova.core_api.languages.LanguageIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeleteEventTranslationCommand(
    @TargetAggregateIdentifier val eventId: EventIdentifier,
    val translationId: LanguageIdentifier
)