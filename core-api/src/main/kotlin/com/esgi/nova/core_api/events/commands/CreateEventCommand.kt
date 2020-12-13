package com.esgi.nova.core_api.events.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

class CreateEventCommand(@TargetAggregateIdentifier val id: EventIdentifier, val isDaily: Boolean, val isActive: Boolean) {
}

