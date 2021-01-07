package com.esgi.nova.core_api.choices.commands

import com.esgi.nova.core_api.choices.ChoiceIdentifier
import com.esgi.nova.core_api.events.EventIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

class CreateChoiceCommand(@TargetAggregateIdentifier val choiceId: ChoiceIdentifier, val eventId: EventIdentifier) {
}

