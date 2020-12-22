package com.esgi.nova.core_api.events.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateEventCommand(
    @TargetAggregateIdentifier val eventId: EventIdentifier,
    val isDaily: Boolean,
    val isActive: Boolean
) {
}


