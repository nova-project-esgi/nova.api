package com.esgi.nova.core_api.choices.commands

import com.esgi.nova.core_api.events.commands.EventIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

class CreateChoiceCommand(@TargetAggregateIdentifier val id: ChoiceIdentifier) {
}

