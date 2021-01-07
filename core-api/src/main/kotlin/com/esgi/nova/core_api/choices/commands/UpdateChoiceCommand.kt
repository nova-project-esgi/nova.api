package com.esgi.nova.core_api.choices.commands

import com.esgi.nova.core_api.choices.ChoiceIdentifier
import com.esgi.nova.core_api.choices.dtos.ChoiceResourceEditionDto
import com.esgi.nova.core_api.choices.dtos.ChoiceTranslationEditionDto
import org.axonframework.modelling.command.TargetAggregateIdentifier

class UpdateChoiceCommand(
    @TargetAggregateIdentifier val choiceId: ChoiceIdentifier,
    val translations: List<ChoiceTranslationEditionDto>,
    val resources: List<ChoiceResourceEditionDto>
) {
}

