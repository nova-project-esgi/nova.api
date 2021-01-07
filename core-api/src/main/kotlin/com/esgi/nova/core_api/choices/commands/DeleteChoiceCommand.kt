package com.esgi.nova.core_api.choices.commands

import com.esgi.nova.core_api.choices.ChoiceIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

class DeleteChoiceCommand(@TargetAggregateIdentifier val choiceId: ChoiceIdentifier) {
}


