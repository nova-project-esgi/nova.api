package com.esgi.nova.core_api.choices.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier

class UpdateChoiceCommand(@TargetAggregateIdentifier val choiceId: ChoiceIdentifier,val translations: List<ChoiceTranslationEditionDto>, val resources: List<ChoiceResourceEditionDto>) {
}