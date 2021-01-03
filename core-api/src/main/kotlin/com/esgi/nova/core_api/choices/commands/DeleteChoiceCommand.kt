package com.esgi.nova.core_api.choices.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

class DeleteChoiceCommand(@TargetAggregateIdentifier val choiceId: ChoiceIdentifier) {
}


