package com.esgi.nova.core_api.events.commands

import com.esgi.nova.core_api.choices.commands.ChoiceIdentifier
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class UpdateEventCommand(
    @TargetAggregateIdentifier val eventId: EventIdentifier,
    val isDaily: Boolean,
    val isActive: Boolean,
    val translations: List<EventTranslationEditionDto>,
    val choiceIds: List<ChoiceIdentifier>
)