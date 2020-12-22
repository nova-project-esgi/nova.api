package com.esgi.nova.core.user.commands.choice_resource

import com.esgi.nova.core_api.choice_resource.commands.ChoiceResourceIdentifier
import com.esgi.nova.core_api.choice_resource.commands.CreateChoiceResourceCommand
import com.esgi.nova.core_api.choice_resource.commands.DeleteChoiceResourceCommand
import com.esgi.nova.core_api.choice_resource.events.CreatedChoiceResourceEvent
import com.esgi.nova.core_api.choice_resource.events.DeletedChoiceResourceEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class ChoiceResourceAggregate() {

    @AggregateIdentifier
    lateinit var id: ChoiceResourceIdentifier

    var changeValue: Int = 0

    @CommandHandler
    constructor(cmd: CreateChoiceResourceCommand) : this() {
        AggregateLifecycle.apply(CreatedChoiceResourceEvent(id = cmd.choiceResourceId, changeValue = cmd.changeValue))
    }

    @CommandHandler
    fun handle(cmd: DeleteChoiceResourceCommand) {
        AggregateLifecycle.apply(DeletedChoiceResourceEvent(id = cmd.choiceResourceId))
    }

    @EventSourcingHandler
    fun onCreatedChoiceResourceEvent(event: CreatedChoiceResourceEvent) {
        id = event.id
        changeValue = event.changeValue
    }
    @EventSourcingHandler
    fun onDeletedChoiceResourceEvent(event: DeletedChoiceResourceEvent) {
        AggregateLifecycle.markDeleted();
    }
}
