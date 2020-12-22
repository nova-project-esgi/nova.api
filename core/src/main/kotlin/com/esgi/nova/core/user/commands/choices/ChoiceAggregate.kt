package com.esgi.nova.core.user.commands.choices

import com.esgi.nova.core.user.commands.choice_translation.ChoiceTranslationEntity
import com.esgi.nova.core_api.choice_translations.commands.DeleteChoiceTranslationCommand
import com.esgi.nova.core_api.choice_translations.events.DeletedChoiceTranslationEvent
import com.esgi.nova.core_api.choices.commands.ChoiceIdentifier
import com.esgi.nova.core_api.choices.commands.CreateChoiceCommand
import com.esgi.nova.core_api.choices.commands.DeleteChoiceCommand
import com.esgi.nova.core_api.choices.events.CreatedChoiceEvent
import com.esgi.nova.core_api.choices.events.DeletedChoiceEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.AggregateMember
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class ChoiceAggregate() {
    @AggregateIdentifier
    private lateinit var id: ChoiceIdentifier

    @AggregateMember
    private var translations: MutableList<ChoiceTranslationEntity> = mutableListOf()


    @CommandHandler
    constructor(cmd: CreateChoiceCommand) : this() {
        AggregateLifecycle.apply(CreatedChoiceEvent(choiceId = cmd.choiceId, eventId = cmd.eventId))
    }

    @EventSourcingHandler
    fun on(event: CreatedChoiceEvent) {
        id = event.choiceId
    }


    @CommandHandler
    fun handle(cmd: DeleteChoiceCommand) {
        AggregateLifecycle.apply(
            DeletedChoiceEvent(
                choiceId = cmd.choiceId
            )
        )
    }

    @EventSourcingHandler
    fun on(event: DeletedChoiceEvent) {
        AggregateLifecycle.markDeleted()
    }


    @CommandHandler
    fun handle(cmd: DeleteChoiceTranslationCommand) {
        if (translations.any { it.id == cmd.translationId } && translations.size == 1) {
            throw ChoiceTranslationMinimalSizeException()
        }
        AggregateLifecycle.apply(
            DeletedChoiceTranslationEvent(
                choiceId = cmd.choiceId,
                translationId = cmd.translationId
            )
        )
    }

    @EventSourcingHandler
    fun on(event: DeletedChoiceTranslationEvent) {
        translations.removeIf { it.id == event.translationId }
    }


}