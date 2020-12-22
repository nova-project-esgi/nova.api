package com.esgi.nova.core_api.event_translations.commands

import com.esgi.nova.core_api.events.commands.EventIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class UpdateEventTranslationCommand(
    @TargetAggregateIdentifier val eventId: EventIdentifier,
    val translationId: EventTranslationIdentifier,
    val title: String,
    val description: String
)