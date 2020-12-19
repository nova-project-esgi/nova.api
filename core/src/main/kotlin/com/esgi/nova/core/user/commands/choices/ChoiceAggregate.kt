package com.esgi.nova.core.user.commands.choices

import com.esgi.nova.core_api.choices.commands.ChoiceIdentifier
import com.esgi.nova.core_api.choices.commands.CreateChoiceCommand
import com.esgi.nova.core_api.choice_translations.commands.CreateChoiceTranslationCommand
import com.esgi.nova.core_api.choices.events.CreatedChoiceEvent
import com.esgi.nova.core_api.choice_translations.events.CreatedChoiceTranslationEvent
import com.esgi.nova.core_api.choices.commands.DeleteChoiceCommand
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

    @CommandHandler
    constructor(cmd: CreateChoiceCommand) : this() {
        AggregateLifecycle.apply(CreatedChoiceEvent(id = cmd.id))
    }

    @CommandHandler
    fun handle(cmd: DeleteChoiceCommand) {
        AggregateLifecycle.apply(
            DeletedChoiceEvent(
                id = cmd.id
            )
        )
    }

    @EventSourcingHandler
    fun onChoiceCreatedEvent(event: CreatedChoiceEvent) {
        id = event.id
    }

    @EventSourcingHandler
    fun onChoiceDeletedEvent(event: DeletedChoiceEvent) {
        AggregateLifecycle.markDeleted()
    }

}