package com.esgi.nova.core_api.events.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class DeleteEventCommand(@TargetAggregateIdentifier val eventId: EventIdentifier) {
}