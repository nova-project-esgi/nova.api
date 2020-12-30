package com.esgi.nova.core_api.events.commands

import com.esgi.nova.core_api.choices.commands.ChoiceIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class RemoveChoiceCommand(@TargetAggregateIdentifier val eventId: EventIdentifier, val choiceId: ChoiceIdentifier) {
}