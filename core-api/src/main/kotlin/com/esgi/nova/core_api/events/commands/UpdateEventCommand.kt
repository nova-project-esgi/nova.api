package com.esgi.nova.core_api.events.commands

import com.esgi.nova.core_api.event_translations.commands.EventTranslationIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class UpdateEventCommand(
    @TargetAggregateIdentifier val eventId: EventIdentifier,
    val isDaily: Boolean,
    val isActive: Boolean,
    val translations: List<EventTranslationEditionDto>
)